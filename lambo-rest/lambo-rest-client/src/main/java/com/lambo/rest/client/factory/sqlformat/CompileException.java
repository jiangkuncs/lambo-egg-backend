package com.lambo.rest.client.factory.sqlformat;

/**
 * 编译错误
 */
public class CompileException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompileException(String msg){
		this(null, msg);
	}
	
	public CompileException(Exception source, String msg){
		super(msg, source);
	}
}
