package com.melinca.befreeproduction.commons.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.melinca.befreeproduction.commons.ExtendedProperty;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class ExtendedPropertiesDBObjectConverter implements Converter<List<ExtendedProperty>, DBObject> {

	@Override
	public DBObject convert(List<ExtendedProperty> extendedObjects) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		if (null != extendedObjects && !extendedObjects.isEmpty()) {
			for (ExtendedProperty property : extendedObjects) {
				mapObj.put(property.getParentClass() + "_" + property.getID(), property.getValue());
				mapObj.put(property.getParentClass() + "_" + property.getID() + "_description",
						property.getDescription());
			}
		}
		return new BasicDBObject(mapObj);
	}

}
