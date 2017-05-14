package org.unico.sample.exception;

public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4359365429178631242L;
	
	public DataNotFoundException(String message){
		super(message);
	}

}
