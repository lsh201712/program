package com.abstractCheck;

public class AbstractJobSun extends AbstractJob {
	
	
	public static void main(String[] args) {
		AbstractJobSun jobSun =  new AbstractJobSun();
		jobSun.testRun();
	}
	public void testRun(){
		
		run();
		
	}
	@Override
	void test() {
		System.out.println("继承抽象类必须实现抽象方法");
		
	}
}
