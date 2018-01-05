/**  
 * Copyright © 2016捷通. All rights reserved.
 * @Title: SendAnnoyResultToOpenApi.java
 * @Prject: pingan_openAPI
 * @Package: com.sinovoice.hcicloud.service.openApi
 * @Description: 防骚扰判断
 * @author: wudong  
 * @date: 2016年10月25日 下午3:39:59
 * @version: V1.0  
 */
package com.test;


/**
 * Copyright © 2016捷通. All rights reserved.
 * 
 * @Title: SendAnnoyResultToOpenApi.java
 * @ClassName: CheckAnnoyResultThread
 * @Description: 检查防骚扰结果
 * @author: wudong
 * @date: 2016年10月25日 下午3:39:59
 */
public class testStaticThread {

	// 临时策略数字3:1 检出3通上报1通;比例可在此更改
	private int  realAnnoyNum = 3;

	private  long reportNum = 0;
	
	/*public static int  realAnnoyNum = 3;

	public static long reportNum = 0;*/

	public   void checkNluResult() {
		
		while(reportNum<100){
			
			reportNum++; 
			System.out.println("reportNum"+reportNum);
//			if (reportNum!=realAnnoyNum) {
//				System.out.println(Thread.currentThread().getName()+"reportNum"+reportNum);
//				System.out.println("不上报");
//				
//			}else{
//				System.out.println("上报");
//				System.out.println(Thread.currentThread().getName()+"reportNum"+reportNum);
//				reportNum=0;
//			}
			if(reportNum % realAnnoyNum == 0) {
				System.out.println("上报");
				System.out.println(Thread.currentThread().getName()+"reportNum"+reportNum);
			}else {
				System.out.println(Thread.currentThread().getName()+"reportNum"+reportNum);
				System.out.println("不上报");
			}
			if(reportNum >10000 ) {
				reportNum = 0;
			}
		}
	}
	
	/*public static void main(String[] args) {
		checkNluResult();
	}*/
}
