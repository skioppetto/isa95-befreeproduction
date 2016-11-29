package com.melinca.befreeproduction.commons;

public class ResponseError {

	private String message;
	private String field;
	private String code;

	
	public ResponseError(String code, String field, String message) {
		super();
		this.message = message;
		this.field = field;
		this.code = code;
	}
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
