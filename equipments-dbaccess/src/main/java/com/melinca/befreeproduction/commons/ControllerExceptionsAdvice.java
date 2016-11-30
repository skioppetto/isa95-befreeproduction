package com.melinca.befreeproduction.commons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionsAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ControllerResponse processValidationError(MethodArgumentNotValidException mex) {
		BindingResult result = mex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ControllerResponse response = new ControllerResponse();
		response.setResponseResult(ControllerResponse.RESULT_FAILURE);
		response.setErrors(new ArrayList<>());
		for (FieldError fe : fieldErrors)
			response.getErrors().add(new ResponseError(fe.getCode(), fe.getField(), fe.getDefaultMessage()));
		return response;
	}

}
