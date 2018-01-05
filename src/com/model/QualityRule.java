package com.model;

public class QualityRule {
	
	
	private String uuid;
	/**
	 * 质检规则名称
	 */

	private String ruleName;
	/**
	 * 简要说明概要
	 */

	private String ruleOutline;
	
	/**
	 * 质检规则类型
	 * 1疑似投诉rule_complain
	 * 2、服务禁语rule_taboo
	 * 3、重复来电rule_repetCall
	 * 4、客户满意度rule_satisfac
	 * 5、违规点rule_point
	 */
	
	private String ruleType;
	/**
	 * 质检规则状态
	 * 1启用   rule_enable
	 * 2停用   rule_disable
	 * 3待审核   rule_pendAudit
	 * 4已驳回   rule_rejected
	 * 
	 */
	
	private String ruleState;
	/**
	 * 创建人姓名
	 */
	
	private String ruleCreatorName;
	
	
	public QualityRule(String uuid,String ruleName ,String ruleOutline, String ruleType ,String ruleState,String ruleCreatorName){
		this.uuid =uuid;
		this.ruleCreatorName =  ruleCreatorName;
		this.ruleName = ruleName;
		this.ruleOutline = ruleOutline;
		this.ruleState = ruleState;
		
	}
	public QualityRule(){
		
	}
	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleOutline() {
		return ruleOutline;
	}
	public void setRuleOutline(String ruleOutline) {
		this.ruleOutline = ruleOutline;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleState() {
		return ruleState;
	}
	public void setRuleState(String ruleState) {
		this.ruleState = ruleState;
	}
	public String getRuleCreatorName() {
		return ruleCreatorName;
	}
	public void setRuleCreatorName(String ruleCreatorName) {
		this.ruleCreatorName = ruleCreatorName;
	}
	
	/**
	 * 创建日期
	 */


}
