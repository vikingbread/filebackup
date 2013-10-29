package com.viki.entity;
/** 
 * @author  Viking
 * @version 创建时间：2013-10-29 下午04:31:34 
 * 
 */
public class FileInfo {
	
	private int totalFiles;
	private long totalFileSize;
	public int getTotalFiles() {
		return totalFiles;
	}
	public void setTotalFiles(int totalFiles) {
		this.totalFiles = totalFiles;
	}
	public long getTotalFileSize() {
		return totalFileSize;
	}
	public void setTotalFileSize(long totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	@Override
	public String toString() {
		return "totalFiles=" + totalFiles + ", totalFileSize="
				+ totalFileSize;
	}

}
