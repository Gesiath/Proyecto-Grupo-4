package com.WorkMerge.exceptions;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 7883636384872015753L;
	
	public ServiceException(String msn) {
		super(msn);
	}

}
