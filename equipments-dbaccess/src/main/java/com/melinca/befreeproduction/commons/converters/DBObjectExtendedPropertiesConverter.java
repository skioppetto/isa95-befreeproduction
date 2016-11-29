package com.melinca.befreeproduction.commons.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.melinca.befreeproduction.commons.ExtendedProperty;
import com.mongodb.DBObject;

@Component
public class DBObjectExtendedPropertiesConverter implements Converter<DBObject, List<ExtendedProperty>> {

	@Override
	public List<ExtendedProperty> convert(DBObject extendedObjects) {
		List<String> properties = new ArrayList<>(extendedObjects.keySet());
		List<ExtendedProperty> extProperties = new ArrayList<>();
		Collections.sort(properties);
		for (String property : properties) {
			if (property.endsWith("_description"))
				continue;
			String[] splitted = property.split("_");
			String className = splitted[0];
			String propertyName = splitted[1];
			ExtendedProperty extProperty = new ExtendedProperty(propertyName, className, extendedObjects.get(property),
					(String) extendedObjects.get(property + "_description"));
			extProperties.add(extProperty);
		}
		return extProperties;
	}

}
