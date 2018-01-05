/*package com.test;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinovoice.hcicloud.common.GlobalFinalVal;
import com.sinovoice.hcicloud.common.RedisOpr;
import com.sinovoice.hcicloud.common.RedisUtil;
import com.sinovoice.hcicloud.common.SystemParameter;
import com.sinovoice.hcicloud.model.interfaces.ResultMessage;

@Service
public class TestRedisThread {

	*//**
	 * 日志类
	 *//*
	private static Logger log = Logger.getLogger(TestRedisThread.class);
	*//**
	 * 执行定时任务
	 *//*
	public void doTimeWork() {
		dowork();
	}
	

	*//**
	 * 
	 * @Title: delCallEndkey
	 * @Description: 取出所有键值，判断2000个通话是否结束
	 *//*
	private void  dowork(){
		
		Jedis jedis = RedisUtil.getJedis();
		jedis.rpush(Math.round((Math.random()*10))+"", "redis 主从复制");
		System.out.println("ok");
		RedisUtil.returnResource(jedis);
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(Math.round((Math.random()*10)));
	}
}
*/