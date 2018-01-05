package com.util;

import java.util.LinkedHashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClusterUtil {
	
	private static int MAX_ACTIVE = 300;//最大连接数
	private static int MAX_IDLE = 10;//最大空闲数
	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000;
	private static boolean TEST_ON_BORROW = true;
	private static JedisCluster jedisCluster = null;

	static {
		try {
		/*	JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);*/
			/*jedisPool = new  JedisPool(config, ADDR, PORT, TIMEOUT, "jietong");*/
			JedisPoolConfig poolConfig = new JedisPoolConfig();  
		    // 最大连接数  
		    poolConfig.setMaxTotal(MAX_ACTIVE);  
		    // 最大空闲数  
		    poolConfig.setMaxIdle(MAX_IDLE);  
		    // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：  
		    // Could not get a resource from the pool  
		    poolConfig.setMaxWaitMillis(MAX_WAIT);
		    
		    Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    nodes.add(new HostAndPort("192.168.83.128", 6379));  
		    jedisCluster = new JedisCluster(nodes,TIMEOUT,poolConfig);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static JedisCluster getJedisCluster() {
		try {
			if (jedisCluster != null) {
				return jedisCluster;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
