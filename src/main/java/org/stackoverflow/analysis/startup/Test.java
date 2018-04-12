package org.stackoverflow.analysis.startup;

import java.time.LocalDateTime;

import org.apache.spark.api.java.JavaSparkContext;
import org.stackoverflow.analysis.datasource.CassandraDataSource;
import org.stackoverflow.analysis.utils.SparkUtil;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.japi.CassandraJavaUtil;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Start Time :: "+LocalDateTime.now());
		JavaSparkContext sc = SparkUtil.getSparkContext();
		long count = CassandraJavaUtil.javaFunctions(sc).cassandraTable("sof", "user").cassandraCount();
		System.out.println(count);
		System.out.println("End Time :: "+LocalDateTime.now());
		
	}

}
