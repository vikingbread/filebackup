package com.viki.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import com.viki.entity.FileInfo;
import com.viki.exception.CopyFileErrorException;
import com.viki.exception.FileNotExistException;

/**
 * @author Viking
 * @version 创建时间：2013-10-21 下午03:35:33
 * 
 */
public class FileUtil {
	private static byte[] buf = new byte[1024*1024];

	public static FileInfo totalFileInfo(File directory) {
		
		FileInfo info = new FileInfo();
		
		int totalFiles = 0;
		long totalFileSize = 0;
		if (!directory.exists()) {
			throw new FileNotExistException("文件不存在!");
		}
		if (directory.isFile()) {
			info.setTotalFiles(1);
			info.setTotalFileSize(directory.length());
			return info;
		}

		LinkedList<File> stack = new LinkedList<File>();
		stack.push(directory);
		File[] files;
		while (!stack.isEmpty()) {
			files = stack.pop().listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					stack.push(file);
				} else {
					totalFiles++;
					totalFileSize += file.length();
				}
			}
		}
		
		info.setTotalFiles(totalFiles);
		info.setTotalFileSize(totalFileSize);
		return info;
	}

	public static void copyFile(File sourceFile, File toFile) {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			int len;
			inputStream = new FileInputStream(sourceFile);
			File tempFile = new File(toFile.getParent());
			if(!tempFile.exists()){
				tempFile.mkdirs();
			}
			if(!toFile.exists()){ 
				toFile.createNewFile();
			}
			outputStream = new FileOutputStream(toFile);
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotExistException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CopyFileErrorException("在拷贝文件:"+sourceFile.getAbsolutePath()+" 时失败");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new CopyFileErrorException("在关闭文件时失败");
			}
		}
	}

}
