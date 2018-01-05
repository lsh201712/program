package com.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.ModelMap;
import com.model.QualityRule;



public class ModelTransUtil <T>{
	   /*public  List<Object> trans(List<Object> list,String... value) throws Exception{
		   List <Object>list1=new ArrayList<Object>();
	       Class<?> demo = null;
	       for(Object t:list){
	    	   demo = t.getClass();
	           for(String str:value){
	        	   Field field = demo.getDeclaredField(str);
	        	  
	               field.setAccessible(true);
	               if(null!=ModelMap.transField.get(field.get(t))){
	            	   field.set(t, ModelMap.transField.get(field.get(t)));
	               }
	              
	           }
	           
	           list1.add(t);
	          
	       }    
			return  list1;
		}*/
	
	public static void main(String[] args) {
		
		QualityRule rule = new QualityRule();
		rule.setRuleCreatorName("name");
		rule.setRuleName("rulename");
		rule.setRuleOutline("line");
		rule.setRuleState("model_enable");
		rule.setRuleType("ruletype");
		rule.setUuid("uuid");
		
		List<Object> list = new ArrayList<>();
		list.add(rule);
		try {
			trans(list, "ruleState");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: 
	 * @Description:
	 * @param list
	 * @param value
	 * @return
	 * @throws Exception List<Object>
	 */
	
	public static List<Object>  trans(List<Object> list,String... value)throws Exception{
		List<Object> list1 = new ArrayList<>();
		Class<?> demo =null;
		for (Object t : list) {
			demo = t.getClass();
			for (String str : value) {
				Field field = demo.getDeclaredField(str);
				field.setAccessible(true);
				if (null != ModelMap.transField.get(field.get(t))) {
					field.set(t, ModelMap.transField.get(field.get(t)));
				}
			}
			list1.add(t);
		}
		
		
		return list1;
	}
	
	
}
