package com.melinca.befreeproduction.commons;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.melinca.befreeproduction.commons.converters.ExtendedPropertiesDBObjectConverter;
import com.mongodb.DBObject;

public class ExtendedPropertyDBObjectConverterTests {

	final ExtendedPropertiesDBObjectConverter converter = new ExtendedPropertiesDBObjectConverter();

	@Test
	public void convert() {
		List<ExtendedProperty> extendedProperties = new ArrayList<>();
		extendedProperties.add(new ExtendedProperty("prop1", "class1", "val1", "description1"));
		extendedProperties.add(new ExtendedProperty("prop2", "class1", 44, "descriptionInt"));
		DBObject dbObj = converter.convert(extendedProperties);
		Assert.assertTrue(dbObj.containsField("class1_prop1"));
		Assert.assertTrue(dbObj.containsField("class1_prop1_description"));
		Assert.assertTrue(dbObj.containsField("class1_prop2"));
		Assert.assertTrue(dbObj.containsField("class1_prop2_description"));
		Assert.assertEquals("description1", dbObj.get("class1_prop1_description"));
		Assert.assertEquals("descriptionInt", dbObj.get("class1_prop2_description"));
		Assert.assertEquals("val1", dbObj.get("class1_prop1"));
		Assert.assertEquals(44, dbObj.get("class1_prop2"));

	}

}
