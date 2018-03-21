package org.stackoverflow.analysis.datasource;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.RoundRobinPolicy;

public class CassandraDataSource {
	
	transient private static Cluster cluster;
	
	static {
		cluster = Cluster.builder()
		        .addContactPoints("10.22.26.52","10.22.26.64","10.22.26.74")
		        .withLoadBalancingPolicy(new RoundRobinPolicy())
		        .build();
	}
	
	public static Session getConnection() {
		Session session = cluster.connect("sof");
		return session;
	}
	
	public static Cluster getCluster() {
		return cluster;
	}
	
}
