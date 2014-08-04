package com.cbandi.msgbroker.exception;

public class BrokerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BrokerException(String message, Throwable e) {
		super(message, e);
	}

}
