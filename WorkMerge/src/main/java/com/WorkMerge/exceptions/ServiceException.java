package com.WorkMerge.exceptions;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 5052622848488765351L;

	public ServiceException(String msn) {
		super(msn);
	}

}
