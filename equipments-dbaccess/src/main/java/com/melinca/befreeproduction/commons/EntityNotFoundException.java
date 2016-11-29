package com.melinca.befreeproduction.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 8693856803054760585L;

}
