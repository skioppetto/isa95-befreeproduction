package com.melinca.befreeproduction.commons;

import java.util.List;

public class ControllerResponse {

	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAILURE = "failure";

	private String responseResult = RESULT_SUCCESS;
	private List<ResponseError> errors;

	public List<ResponseError> getErrors() {
		return errors;
	}

	public void setErrors(List<ResponseError> errors) {
		this.errors = errors;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

}
