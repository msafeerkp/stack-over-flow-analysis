package org.stackoverflow.analysis.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackoverflow.analysis.dao.AnswerDao;
import org.stackoverflow.analysis.dao.CommentDao;
import org.stackoverflow.analysis.dao.QuestionDao;
import org.stackoverflow.analysis.dao.TagDao;
import org.stackoverflow.analysis.dao.UserDao;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.PostModel;
import org.stackoverflow.analysis.model.QuestionModel;
import org.stackoverflow.analysis.model.TagModel;
import org.stackoverflow.analysis.model.UserModel;
import org.stackoverflow.analysis.model.AnswerModel;
import org.stackoverflow.analysis.model.CommentModel;
import org.stackoverflow.analysis.model.Message;
import org.stackoverflow.analysis.utils.AnalyticsConfigUtil;
import org.stackoverflow.analysis.utils.KafkaConfigUtil;
import org.stackoverflow.analysis.utils.SparkUtil;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import scala.Tuple4;


public class StorageService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9040667482875600121L;

	public static final StorageService STORAGE_SERVICE = new StorageService();
	
	Logger log = LoggerFactory.getLogger(StorageService.class);
	
	public static StorageService getInstance() {
		return STORAGE_SERVICE; 
	}

	private JavaInputDStream<ConsumerRecord<String, String>> createStream(Collection<String> topics, 
			Map<String, Object> kafkaParams, JavaStreamingContext javaStreamingContext) throws Exception{
		JavaInputDStream<ConsumerRecord<String, String>> stream = null;
		
		try {
			stream = KafkaUtils.createDirectStream(
					javaStreamingContext, 
					LocationStrategies.PreferConsistent(), 
					ConsumerStrategies.Subscribe(topics, kafkaParams)
			);
			log.info("Stream object is created.");
		}
		catch (Exception e) {
			log.error("Exception occured while creating the Stream object.");
			throw e;
		}
		
		return stream;
	}
	
	private void provisionForSavingStream(JavaInputDStream<ConsumerRecord<String, String>> stream) throws Exception{
		
		try {
			
			JavaDStream<Message> postStream = stream.map( record -> {
				Logger log = LoggerFactory.getLogger(this.getClass());
				log.info("=============== Executor Map operation log starts from here =================");
				Message message = null;
				try {
					String value = record.value();
					ObjectMapper mapper = new ObjectMapper();
					message = mapper.readValue(value, Message.class);
					Object items = message.getItems();
					if(message.getMetaType().equals("ANSWER")) {
						log.info("since message is of type 'ANSWER' converting the message to answer model");
						List<AnswerModel> answerModels = mapper.convertValue(items, new TypeReference<List<AnswerModel>>() {});
						message.setItems(answerModels);
					}
					else if(message.getMetaType().equals("QUESTION")) {
						log.info("since message is of type 'QUESTION' converting the message to answer model");
						List<QuestionModel> questions = mapper.convertValue(items, new TypeReference<List<QuestionModel>>() {});
						message.setItems(questions);
					}
					else if(message.getMetaType().equals("COMMENT")) {
						log.info("since message is of type 'COMMENT' converting the message to answer model");
						List<CommentModel> comments = mapper.convertValue(items, new TypeReference<List<CommentModel>>() {});
						message.setItems(comments);
					}
					else if(message.getMetaType().equals("TAG")) {
						log.info("since message is of type 'TAG' converting the message to answer model");
						List<TagModel> tags = mapper.convertValue(items, new TypeReference<List<TagModel>>() {});
						message.setItems(tags);
					}
					else if(message.getMetaType().equals("USER")) {
						log.info("since message is of type 'USER' converting the message to answer model");
						List<UserModel> users = mapper.convertValue(items, new TypeReference<List<UserModel>>() {});
						message.setItems(users);
					}
				}
				catch (Exception e) {
					log.error("Exception occured while performing the map operation.",e);
				}
				
				log.info("=============== Executor Map operation log starts from here =================");
				
				return message;
			});
			postStream.foreachRDD( postRDD -> {
				
				postRDD.foreachPartition(wrapperModels -> {
					Logger log = LoggerFactory.getLogger(this.getClass());
					log.info("=============== Executor for each partition job log starts from here =================");
					try  {
						
						while(wrapperModels.hasNext()) {
							Message wrapperModel = wrapperModels.next();
							if(wrapperModel.getMetaType().equals("ANSWER")) {
								log.info("Model is of type 'Answer'. preparing to insert the model.");
								@SuppressWarnings("unchecked")
								List<AnswerModel> answerModels = (List<AnswerModel>) wrapperModel.getItems();
								AnswerDao.getInstance().add(answerModels);
								log.info("answer model insertion completed.");
							}
							else if(wrapperModel.getMetaType().equals("QUESTION")) {
								log.info("Model is of type 'Answer'. preparing to insert the model.");
								@SuppressWarnings("unchecked")
								List<QuestionModel> questions = (List<QuestionModel>) wrapperModel.getItems();
								QuestionDao.getInstance().add(questions);
								log.info("answer model insertion completed.");
							}
							else if(wrapperModel.getMetaType().equals("COMMENT")) {
								log.info("Model is of type 'Answer'. preparing to insert the model.");
								List<CommentModel> comments = (List<CommentModel>) wrapperModel.getItems();
								CommentDao.getInstance().add(comments);
								log.info("answer model insertion completed.");
							}
							else if(wrapperModel.getMetaType().equals("TAG")) {
								log.info("Model is of type 'Answer'. preparing to insert the model.");
								@SuppressWarnings("unchecked")
								List<TagModel> tags = (List<TagModel>) wrapperModel.getItems();
								TagDao.getInstance().add(tags);
								log.info("answer model insertion completed.");
							}
							else if(wrapperModel.getMetaType().equals("USER")) {
								log.info("Model is of type 'Answer'. preparing to insert the model.");
								@SuppressWarnings("unchecked")
								List<UserModel> users = (List<UserModel>) wrapperModel.getItems();
								UserDao.getInstance().add(users);
								log.info("answer model insertion completed.");
							}
							
							
							
						}
					}
					catch (Exception e) {
						log.error("Exception occured while perfroming for each partition job [inserting data to cassandra]",e);
					}
					
					log.info("=============== Executor for each partition job log ends here =================");
					
				});
				
				
				
			});
			
			
			/*log.info("converted stream of raw data to stream of Post Wrapper Model. ");
			
			JavaDStream<Tuple4<Long, Long, Integer, String>> meta = postStream.map( wrappper ->{
				Logger log = LoggerFactory.getLogger(this.getClass());
				log.info("Processing Wrapper Object. Elements are Page :: {}, time :: {}, Size of PostModels :: {}",
						wrappper.getPage(),wrappper.getTime(),wrappper.getItems().size());
				return new Tuple4<>(wrappper.getPage(), wrappper.getTime(), wrappper.getItems().size(), "POST");
			});
			log.info("Post meta information extracted from stream. created a new meta stream object.");
			
			log.info("Provisioning each RDD from meta stream for save operation.");
			meta.foreachRDD( postMetaRDD -> {
				log.info("Number of elements in RDD :: {}",postMetaRDD.count());
				CassandraJavaUtil.javaFunctions(postMetaRDD).writerBuilder("sof", "sof_meta", CassandraJavaUtil.mapTupleToRow(
						Long.class,
						Long.class,
						Integer.class,
						String.class
						)).withColumnSelector(CassandraJavaUtil.someColumns("page_number","time","batch_size","meta_type")).saveToCassandra();
			});
			log.info("save operation completed for current stream of data.");
			
			JavaDStream<PostModel> postDstream = postStream.flatMap( wrapper -> {
				return wrapper.getItems().iterator();
			});
			log.info("Extracted PostModel data from Post Wrapper Model. ");
			log.info("Provisioning each RDD from stream for save operation.");
			postDstream.foreachRDD( postRDD -> {
				log.info("Number of elements in RDD :: {}",postRDD.count());
				if(postRDD.count() > 0) {
					postRDD.take(1).forEach(samplePost -> {
						Logger log = LoggerFactory.getLogger(this.getClass());
						log.info("Sample Element. Post ID :: " + samplePost.getPost_id());
					});
				}
				CassandraJavaUtil.javaFunctions(postRDD).writerBuilder("sof", "post", CassandraJavaUtil.mapToRow(PostModel.class)).saveToCassandra();
			});
			log.info("save operation completed for current stream of data.");*/
			
		}
		catch (Exception e) {
			log.error("Exception occured while operating the stream.");
			throw e;
		}
		
	}
	
	
	public void start() {
		
		try {
			log.info("===== Post Storage Service started. =====");
			JavaStreamingContext javaStreamingContext = SparkUtil.getStreamContext(Durations.seconds(4));
			Map<String, Object> kafkaParams = KafkaConfigUtil.getConsumerConfig();
			Collection<String> topics = Arrays.asList(AnalyticsConfigUtil.getProperties().getProperty("analytics.post.topic.name"));
			JavaInputDStream<ConsumerRecord<String, String>> stream = createStream(topics, kafkaParams, javaStreamingContext);
			provisionForSavingStream(stream);
			javaStreamingContext.start();
			javaStreamingContext.awaitTermination();
			log.info("===== Post Storage Service ends here. =====");
			
		}
		catch (Exception e) {
			log.error("===== Exception occured while saving the post data . exception :: ",e);
		}

		
	}
	
	
}
