package com.acme.cursomc.services.exception;

public class ObjectNofFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNofFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNofFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
