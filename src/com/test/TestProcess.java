package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * Copyright © 2017捷通. All rights reserved.
 * 
 * @Title: TestProcess.java
 * @ClassName: TestProcess
 * @Description: 测试process类
 * @author: lishihuan
 * @date: 2017年9月22日 下午4:01:42
 */
public class TestProcess {

	public static void main(String[] args) {

		Process p = null;
		try {
//			p = Runtime.getRuntime().exec("notepad.exe");
			p= Runtime.getRuntime().exec("cmd.exe /c start notepad.exe");
			// 获取进程的标准输入流
			final InputStream is1 = p.getInputStream();

			// 获取进城的错误流
			final InputStream is2 = p.getErrorStream();

			// 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
			/*new Thread() {
				public void run() {
					BufferedReader br1 = new BufferedReader(
							new InputStreamReader(is1));
					try {
						String line1 = null;
						while ((line1 = br1.readLine()) != null) {
							if (line1 != null) {
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							is1.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

			new Thread() {
				public void run() {
					BufferedReader br2 = new BufferedReader(
							new InputStreamReader(is2));
					try {
						String line2 = null;
						while ((line2 = br2.readLine()) != null) {
							if (line2 != null) {
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							is2.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
*/
			// 先打开exe再执行下面
			p.waitFor();
			/*p.destroy();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("我想被打印...");

	}

}
