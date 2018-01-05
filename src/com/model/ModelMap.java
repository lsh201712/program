package com.model;

import java.util.HashMap;
import java.util.Map;

public class ModelMap {
	public static final Map<String,String> transField=new HashMap<String,String>(){
		{
	   //字段设置类型
		put("exact","精确匹配");
		put("fuzzy","模糊匹配");
		put("full","全文匹配");
		put("number","数值类型");
		put("enum","枚举类型");
		put("time","时间类型");
		//参数设置类型
		put("asr","asr引擎参数");
		put("cti","cti接口参数");
		put("engine","质检引擎参数");
		put("search","搜索引擎参数");
		put("other","其他参数");
		//数据状态
		put("enable","启用");
		put("disable","禁用");
		put("reject","已驳回");
		put("approval","待审核");
		put("passed","已通过");
		//质检计划是否终止
		put("yes","是");
		put("no","否");
		//质检对象
		put("agent","坐席");
		put("customer","客户");
		put("both","通话双方");
		//质检指标
		put("speed","语速");
		put("silencePercent","静音比");
		put("silence","静默音");
		put("rule","质检规则");
		put("standSpeech","可选话术");
		//质检计划类型
		put("plan_common","日常质检");
		put("plan_special","专项质检");
		//评分规则
		put("manual","人工评分规则库");
		put("machine","智能评分规则库");
		//质检规则
		put("rule_enable", "启用");
		put("rule_disable", "禁用");
		put("rule_pendAudit", "待审核");
		put("rule_rejected", "已驳回");
		
		put("rule_complain", "疑似投诉");
		put("rule_taboo", "服务禁语");
		put("rule_repetCall", "重复来电");
		put("rule_satisfac", "客户满意度");
		put("rule_point", "违规点");
		put("analysis_rule_competitor", "竞品分析");
		put("analysis_rule_product", "产品分析");
		//质检模型
		put("model_enable", "启用");
		put("model_disable", "禁用");
		

		}
	};
}
