package com.test;

import java.io.File;
import java.text.SimpleDateFormat;

public class GetFileCreateTime {
	public static void main(String[] args) {
		
		File file = new File("d:/1.txt");
		
		long lastModified = file.lastModified();
	 System.out.println(lastModified);
	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String format = simpleDateFormat.format(lastModified);
		System.out.println(format);
	}
}
