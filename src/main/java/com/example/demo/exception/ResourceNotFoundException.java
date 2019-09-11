package com.example.demo.exception;


public class ResourceNotFoundException extends RuntimeException{
	private String userName;
	
	public ResourceNotFoundException(String fieldName,String fieldValue) {
		super(String.format("user not found with %s : '%s'", fieldName, fieldValue));
		this.userName = fieldValue;
	}
	public String getUserName() {
		return userName;
	}
}
