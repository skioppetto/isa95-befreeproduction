package com.melinca.befreeproduction.isa95;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.melinca.befreeproduction.commons.ExtendedProperty;
import com.melinca.befreeproduction.commons.converters.ExtendedPropertiesDBObjectConverter;
import com.melinca.befreeproduction.isa95.converters.EquipmentDBObjectConverter;
import com.mongodb.DBObject;

public class EquipmentDBObjectConverterTest {

	private EquipmentDBObjectConverter converter = new EquipmentDBObjectConverter(
			new ExtendedPropertiesDBObjectConverter());

	@Test
	public void convertEquipmentDBObject() {
		Equipment equip = new Equipment();
		equip.setID("myID");
		equip.setDescription("myDescription");
		equip.setEquipmentClassIDs(Arrays.asList("class1", "class2"));
		equip.setEquipmentLevel("myLevel");
		equip.setHierarchyScope(HierarchyScopeEnum.Area);
		equip.setLocation("myLocation");
		List<ExtendedProperty> properties = new ArrayList<>();
		properties.add(new ExtendedProperty("prop1", "class1", "myValue", "description1"));
		properties.add(new ExtendedProperty("prop2", "class1", 56.67, "description2"));
		equip.setExtendedProperties(properties);
		DBObject dbObj = converter.convert(equip);
		Assert.assertEquals("myID", dbObj.get("_id"));
		Assert.assertEquals("myDescription", dbObj.get("description"));
		Assert.assertTrue(dbObj.get("equipmentClassIDs") instanceof List);
		List<String> classList = (List<String>) dbObj.get("equipmentClassIDs");
		Assert.assertEquals(2, classList.size());
		Assert.assertTrue(classList.contains("class1"));
		Assert.assertTrue(classList.contains("class2"));
		Assert.assertEquals("myLocation", dbObj.get("location"));
		Assert.assertEquals(HierarchyScopeEnum.Area.toString(), dbObj.get("hierarchyScope"));
		Assert.assertEquals("myValue", dbObj.get("class1_prop1"));
		Assert.assertEquals("description1", dbObj.get("class1_prop1_description"));
		Assert.assertEquals(56.67, dbObj.get("class1_prop2"));
		Assert.assertEquals("description2", dbObj.get("class1_prop2_description"));
	}

}
