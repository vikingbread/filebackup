package com.viki.exception;
/** 
 * @author  Viking
 * @version 创建时间：2013-10-29 上午09:24:16 
 * 
 */
@SuppressWarnings("serial")
public class FileNotExistException extends RuntimeException {
	
	public FileNotExistException(){
		
	}
	
	public FileNotExistException(String info){
		super(info);
	}

}
