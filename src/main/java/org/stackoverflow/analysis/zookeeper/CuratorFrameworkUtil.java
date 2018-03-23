package org.stackoverflow.analysis.zookeeper;

import java.util.Properties;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.stackoverflow.analysis.utils.AnalyticsConfigUtil;

public class CuratorFrameworkUtil {
	
	public static CuratorFramework getClient() {
		return CuratorFrameworkFactory.newClient(AnalyticsConfigUtil.getProperties().getProperty("analytics.zookeeper.host"), new ExponentialBackoffRetry(1000, 3));
	}

}
