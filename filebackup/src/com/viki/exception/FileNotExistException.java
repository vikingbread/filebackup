package com.viki.exception;
/** 
 * @author  Viking
 * @version ����ʱ�䣺2013-10-29 ����09:24:16 
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
