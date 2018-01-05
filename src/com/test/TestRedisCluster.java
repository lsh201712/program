package com.test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedisCluster {
		
	private static 	JedisPoolConfig poolConfig =null;
	private static JedisCluster cluster =null;
	private  static Set<HostAndPort> nodes =null;
	
	public static void main(String[] args) {
		while(true){
		if (poolConfig==null) {
			
		    poolConfig = new JedisPoolConfig();  
		    //最大连接数
		    poolConfig.setMaxTotal(300); 
		    //最小空闲连接数
		    poolConfig.setMaxIdle(10);
		    //每次释放最大连接的最大数目
		    poolConfig.setNumTestsPerEvictionRun(100);
		    //释放连接的
		    poolConfig.setMaxWaitMillis(100);  
		    
		    nodes = new LinkedHashSet<HostAndPort>();  
		    nodes.add(new HostAndPort("10.10.10.216", 6381)); 
		    nodes.add(new HostAndPort("10.10.10.228", 6381));
		    nodes.add(new HostAndPort("10.0.1.74", 6381));
		    
		    nodes.add(new HostAndPort("10.10.10.216", 6382));  
		    nodes.add(new HostAndPort("10.10.10.228", 6382)); 
		    nodes.add(new HostAndPort("10.0.1.74", 6382));  
		     
		    
		   /* cluster.set("age", "18");  
		    System.out.println(cluster.get("age")); */
		   
		}
		 cluster = new JedisCluster(nodes, poolConfig); 
		 String name = cluster.get("biergaici");  
		    System.out.println(name);
		    
		    try {  
		        cluster.close();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
		
		}
		
		  
	}

}
