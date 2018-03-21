package org.stackoverflow.analysis.utils;

import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkUtil {
	
	static Logger log = LoggerFactory.getLogger(SparkUtil.class);
	
	private static SparkConf getSparkConf() {
		
		
		Properties properties = AnalyticsConfigUtil.getProperties();
		SparkConf sparkConf = new SparkConf()
				.setMaster(properties.getProperty("analytics.spark.master.url"))
				.set("spark.executor.uri",properties.getProperty("analytics.spark.executor.uri"))
				.set("spark.executor.extraClassPath", properties.getProperty("analytics.spark.executor.extraClassPath"))
				.setJars(new String[]{"/home/administrator/Installed/spark/lib/analysis-0.0.1-SNAPSHOT-jar-with-dependencies.jar"})
				.setAppName( properties.getProperty("analytics.spark.app.name"));
		log.info("Spark common config constructed.");
		return sparkConf;
	}
	
	public static JavaStreamingContext getStreamContext(Duration duration) throws Exception {
		
		JavaStreamingContext streamingContext = null;
		try {
			Properties properties = AnalyticsConfigUtil.getProperties();
			SparkConf sparkConf = getSparkConf()/*.set("spark.cassandra.connection.host", properties.getProperty("analytics.spark.cassandra.connection.host"))*/;
			streamingContext = new JavaStreamingContext(sparkConf, duration);
			log.info("Spark common config constructed.");
		}
		catch (Exception e) {
			log.info("Exception occured while constructing the spark streaming context.");
			throw e;
		}
		return streamingContext;
	
	}

}
