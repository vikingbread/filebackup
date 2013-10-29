package com.viki.exception;
/** 
 * @author  Viking
 * @version 创建时间：2013-10-29 下午04:16:31 
 * 
 */
@SuppressWarnings("serial")
public class CopyFileErrorException extends RuntimeException {
	
	public CopyFileErrorException() {
	}
	
	public CopyFileErrorException(String msg) {
		super(msg);
	}
	

}
