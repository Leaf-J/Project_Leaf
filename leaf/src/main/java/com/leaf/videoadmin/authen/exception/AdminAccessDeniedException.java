package com.leaf.videoadmin.authen.exception;

import org.springframework.security.access.AccessDeniedException;

public class AdminAccessDeniedException extends AccessDeniedException{

	public AdminAccessDeniedException(String msg) {
		super(msg);
	}

	
}
