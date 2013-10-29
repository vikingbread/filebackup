package com.viki.test;

import java.io.File;

/** 
 * @author  Viking
 * @version 创建时间：2013-10-29 下午12:49:24 
 * 
 */
public class Test {
	
	public static void main(String[] args) {
		File file = new File("E:\\");
		File[] files = file.listFiles();
		for(File f: files){
			System.out.println(f.getPath());
			System.out.println(f.getParent());
			System.out.println(f.getAbsolutePath());
		}
	}

}
