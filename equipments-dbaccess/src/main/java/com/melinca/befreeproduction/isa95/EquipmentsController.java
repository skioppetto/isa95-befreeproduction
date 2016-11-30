package com.melinca.befreeproduction.isa95;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.melinca.befreeproduction.commons.ControllerResponse;
import com.melinca.befreeproduction.commons.ResponseError;

@RestController
@RequestMapping("/equipments")
public class EquipmentsController {

	@Autowired
	private MongoTemplate mTemplate;


	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public Equipment createEquipment(@RequestBody @Valid Equipment equipment) throws MethodArgumentNotValidException {
		return equipment;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void saveEquipment(Equipment equipment) {
		mTemplate.save(equipment);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Equipment> retrieveEquipments() {
		return mTemplate.findAll(Equipment.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Equipment getEquipment(@PathVariable(name = "id") String id) throws EquipmentNotFoundException {
		Equipment eq = mTemplate.findById(id, Equipment.class);
		if (null == eq)
			throw new EquipmentNotFoundException();
		return eq;
	}

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
