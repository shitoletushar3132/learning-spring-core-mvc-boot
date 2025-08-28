package com.blog.exception;

public class DataAccessException extends RuntimeException {
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause);
	}
}
