package com.melinca.befreeproduction.commons;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.melinca.befreeproduction.commons.converters.DBObjectExtendedPropertiesConverter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBObjectExtendedPropertyConverterTests {

	final DBObjectExtendedPropertiesConverter converter = new DBObjectExtendedPropertiesConverter();

	@Test
	public void convert() {
		DBObject dbObj = new BasicDBObject();
		dbObj.put("class1_prop1", "val1");
		dbObj.put("class1_prop1_description", "description1");
		dbObj.put("class1_prop2", 14.45);
		dbObj.put("class1_prop2_description", "description2");
		List<ExtendedProperty> list = converter.convert(dbObj);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		for (ExtendedProperty e : list) {
			Assert.assertEquals("class1", e.getParentClass());
			Assert.assertTrue(Arrays.asList("prop1", "prop2").contains(e.getID()));
			if ("class1_prop1".equals(e.getID())) {
				Assert.assertEquals("description1", e.getDescription());
				Assert.assertEquals("val1", e.getValue());
			} else if ("class1_prop2".equals(e.getID())) {
				Assert.assertEquals("description2", e.getDescription());
				Assert.assertEquals(14.45, e.getValue());
			}
		}

	}

}
