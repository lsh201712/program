package com.abstractCheck;

public  abstract class AbstractJob {
	
	protected void run(){
		
		System.out.println("调用抽象类方法");
		
	}
	
	protected void run1(){
		System.out.println("");
	}

   abstract void test();
}
