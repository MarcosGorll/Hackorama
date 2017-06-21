package com.careem.kmsandmore.api;

public class DefaultResponse {

	private String message;

	public DefaultResponse() {
	}

	public DefaultResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
