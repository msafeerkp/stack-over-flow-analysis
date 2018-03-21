package org.stackoverflow.analysis.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyticsConfigUtil {
	
	static Logger log = LoggerFactory.getLogger(AnalyticsConfigUtil.class);
	static Properties properties;
	
	static {
		try {
			log.info("===== Loading the properties file data into memmory =====");
			InputStream inputStream = AnalyticsConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
			properties = new Properties();
			properties.load(inputStream);
			log.info("===== Loading the properties file data into memmory ======");
		}
		catch (Exception e) {
			log.error("Exception Occured while loading the porperties file into memmory.");
		}

	}
	
	public static Properties getProperties() {
		return properties;
	}

}
