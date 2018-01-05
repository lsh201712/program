package com.test;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class TestRedisRemote {

	public static void main(String[] args) {
		putDataToSap();
	
	}

	//构建实时sap页面对话记录数据
		public static void putDataToSap(){
		for (int i = 0; i < 100; i++) {
			
		
			String history = "history_1_101_1510054920-801815";
			String record ="recorddata_1_101";
			
			Jedis jedis = null;
			try {

				long starTime = System.currentTimeMillis();
				jedis = new Jedis("10.0.1.74", 6390, 10000);
				long currentTimeMillis = System.currentTimeMillis();
				
				//String content1="{\"timestamp\":"+currentTimeMillis+",\"content\":\"嗯 好 的 钱;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"关注点\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"话术建议\",\"answerType\":\"5\",\"emotion\":\"0.3\"}";
				//String content2 = "{\"timestamp\":"+(currentTimeMillis+1000)+",\"content\":\"嗯 好 的 那 行 也是 非常感谢 您 的 接听 了 那 您 这边 先 忙 好吧;time=1235440 1239570\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"测试违规显示\",\"answerType\":\"8\",\"emotion\":\"0.1\"}";
				//String content3 ="{\"timestamp\":"+(currentTimeMillis+1000)+",\"content\":\"您 再次 吧 如果说 您 这边 的 话 今天 的 话 也是 跟 您 讲 的 比较 清楚 的 呢 反正 的 话 也 这 两笔 钱 给 您 带 出 如果说 您说 的 话 有 顾虑 的 地方 您 这边 的 话 做 一个 考量 做 一个 衡量 那 明天 的 话 我们 这边 的 话 再 给 您 回 个 电话 了 好吧;time=1219230 1232910\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\",\"answerType\":\"3\",\"emotion\":\"0.8\"}";
				//String content4 = "{\"timestamp\":"+(currentTimeMillis+1000)+",\"content\":\"a;time=1180990 1181020\",\"talkertype\":\"2\",\"id\":\"013733315198\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"951ea5bec3ae11e7\",\"answer\":\"\",\"answerType\":\"3\",\"emotion\":\"0.5\"}";
				String recordContent ="{\"timestamp\":1510053784554,\"endtime\":1510053784554,\"userphone\":\"013929203916\",\"starttime\":1510053784554,\"callid\":\"1510054920-801815\",\"subccno\":\"vdn\",\"agentid\":\"101\"}";
				if (!jedis.exists(record)) {
					jedis.lpush(record, recordContent);
				}
				jedis.lpush(record, recordContent);
				
				
				String content1="{\"timestamp\":"+currentTimeMillis+",\"content\":\"嗯 您好 王先生是吗 ;time=1242020 1243040\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				String content2="{\"timestamp\":"+(currentTimeMillis+1000)+",\"content\":\"嗯 ;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				String content3="{\"timestamp\":"+(currentTimeMillis+2000)+",\"content\":\"您好 王先生过我是平安的业务经理李有一项新业务向您简单介绍一下;time=1242020 1243040\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"关注点\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				String content4="{\"timestamp\":"+(currentTimeMillis+3000)+",\"content\":\"买过保险了;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"X先生，您有非常好的保险意识，而且足额的保险保障也体现了您对亲人的爱心和对家庭的责任\",\"answerType\":\"5\"}";
				String content5="{\"timestamp\":"+(currentTimeMillis+4000)+",\"content\":\"X先生，您有非常好的保险意识，而且足额的保险保障也体现了您对亲人的爱心和对家庭的责任;time=1242020 1243040\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"forbiddenWord\":\"随取随用\"}";
				String content6="{\"timestamp\":"+(currentTimeMillis+5000)+",\"content\":\"嗯 得和我老婆商量;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				String content7="{\"timestamp\":"+(currentTimeMillis+6000)+",\"content\":\"做不了主吗;time=1242020 1243040\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"态度欠佳（非主动攻击)\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"态度欠佳(非主动攻击)\",\"answerType\":\"5\"}";
				String content8="{\"timestamp\":"+(currentTimeMillis+7000)+",\"content\":\"哎 我准备开会了暂时不需要了;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"骚扰\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"客户告知不需要\",\"answerType\":\"5\"}";
				String content9="{\"timestamp\":"+(currentTimeMillis+8000)+",\"content\":\"嗯 好的王先生不打扰您工作了;time=1242020 1243040\",\"talkertype\":\"1\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				String content10="{\"timestamp\":"+(currentTimeMillis+9000)+",\"content\":\"嗯;time=1242020 1243040\",\"talkertype\":\"2\",\"id\":\"7510\",\"userphone\":\"013733315198\",\"cmd\":\"\",\"channelId\":\"954509cac3ae11e7\",\"answer\":\"\"}";
				
				
				List<String> conteList = new ArrayList<>();
				conteList.add(content1);
				conteList.add(content2);
				conteList.add(content3);
				conteList.add(content4);
				conteList.add(content5);
				
				conteList.add(content6);
				conteList.add(content7);
				conteList.add(content8);
				conteList.add(content9);
				conteList.add(content10);
				
				for (String string : conteList) {
					jedis.lpush(history, string);
					Thread.sleep(2000);
				}
				
				
				
				long endTime = System.currentTimeMillis();
				System.out.println("redis写入耗时:" + (endTime - starTime));

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jedis.close();
			}
		}
		

	}

}
