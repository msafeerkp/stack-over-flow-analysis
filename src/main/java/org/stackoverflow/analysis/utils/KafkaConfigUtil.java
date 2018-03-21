package org.stackoverflow.analysis.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConfigUtil {
	
private static Map<String, Object> getCommonConfig() {
		
		Map<String, Object> config = new HashMap<>();
		Properties AnalyticalProps = AnalyticsConfigUtil.getProperties();
		config.put("bootstrap.servers", AnalyticalProps.getProperty("analytics.kafka.bootstrap.servers"));
		return config;
		
	}
	
	public static Map<String, Object> getConsumerConfig() {
		
		 Properties AnalyticalProps = AnalyticsConfigUtil.getProperties();
		 Map<String, Object> config = getCommonConfig();
		 config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, AnalyticalProps.getProperty("analytics.kafka.enable.auto.commit"));
		 config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, AnalyticalProps.getProperty("analytics.kafka.key.deserializer"));
		 config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AnalyticalProps.getProperty("analytics.kafka.value.deserializer"));
		 config.put(ConsumerConfig.GROUP_ID_CONFIG, AnalyticalProps.getProperty("analytics.kafka.groupid"));
		 //config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AnalyticalProps.getProperty("analytics.kafka.auto.offset.reset"));
		 return config;
	     
	}
	
	/*public static Map<String, Object> getKafkaConfig() {
		
		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "localhost:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "post-storage-service");
		kafkaParams.put("auto.offset.reset", "latest");
		kafkaParams.put("enable.auto.commit", false);
		
		return kafkaParams;
		
	}*/

}
