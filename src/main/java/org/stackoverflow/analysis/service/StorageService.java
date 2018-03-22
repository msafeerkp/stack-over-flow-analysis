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
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.model.PostModel;
import org.stackoverflow.analysis.model.AnswerModel;
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
				String value = record.value();
				ObjectMapper mapper = new ObjectMapper();
				Message message = mapper.readValue(value, Message.class);
				Object items = message.getItems();
				if(message.getMetaType().equals("ANSWER")) {
					List<AnswerModel> answerModels = mapper.convertValue(items, new TypeReference<List<AnswerModel>>() {});
					message.setItems(answerModels);
				}
				
				return message;
			});
			postStream.foreachRDD( postRDD -> {
				
				postRDD.foreachPartition(wrapperModels -> {
					Logger log = LoggerFactory.getLogger(this.getClass());
					log.info("=============== Executor log starts from here =================");
					Session session = null;
					try  {
						
						while(wrapperModels.hasNext()) {
							Message wrapperModel = wrapperModels.next();
							if(wrapperModel.getMetaType().equals("ANSWER")) {
								
								@SuppressWarnings("unchecked")
								List<AnswerModel> answerModels = (List<AnswerModel>) wrapperModel.getItems();
								AnswerDao.getInstance().add(answerModels);
								
							}
							
							
							
						}
					}
					catch (Exception e) {
						log.error("Exception occured while inserting post and meta.",e);
					}
					finally {
						if(session != null) {
							log.error("Closing cassandra session.");
							session.close();
						}
						
					}
					
					log.info("=============== Executor log ends from here =================");
					
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
