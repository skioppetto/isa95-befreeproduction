package com.melinca.befreeproduction.isa95.converters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.melinca.befreeproduction.commons.converters.DBObjectExtendedPropertiesConverter;
import com.melinca.befreeproduction.isa95.Equipment;
import com.melinca.befreeproduction.isa95.HierarchyScopeEnum;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBObjectEquipmentConverterTest {

	private DBObjectEquipmentConverter converter = new DBObjectEquipmentConverter(
			new DBObjectExtendedPropertiesConverter());

	@Test
	public void testConversionNoExtededProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put("_id", "id");
		map.put("description", "description");
		map.put("location", "location");
		map.put("hierarchyScope", "Enterprise");
		map.put("equipmentLevel", "0");
		// map.put("parent", "0");

		// private List<ExtendedProperty> ExtendedProperties;
		// private List<Equipment> Equipments;
		// private List<String> EquipmentClassIDs;
		// private String Parent;

		DBObject obj = new BasicDBObject(map);
		Equipment eq = converter.convert(obj);
		Assert.assertNotNull(eq);
		Assert.assertEquals("id", eq.getID());
		Assert.assertEquals("description", eq.getDescription());
		Assert.assertEquals("location", eq.getLocation());
		Assert.assertEquals(HierarchyScopeEnum.Enterprise, eq.getHierarchyScope());
		Assert.assertEquals("0", eq.getEquipmentLevel());
		Assert.assertNull(eq.getEquipments());
		Assert.assertNull(eq.getEquipmentClassIDs());
		Assert.assertNull(eq.getParent());
	}

	@Test
	public void testConversionWithExtededProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put("_id", "id");
		map.put("description", "description");
		map.put("location", "location");
		map.put("hierarchyScope", "Enterprise");
		map.put("equipmentLevel", "0");
		// map.put("parent", "0");
		map.put("Class1_Property1", "Value1");
		map.put("Class1_Property1_description", "Value1 description");
		map.put("equipmentClassIDs", Arrays.asList("Class1"));
		// private List<Equipment> Equipments;
		// private String Parent;

		DBObject obj = new BasicDBObject(map);
		Equipment eq = converter.convert(obj);
		Assert.assertNotNull(eq);
		Assert.assertEquals("id", eq.getID());
		Assert.assertEquals("description", eq.getDescription());
		Assert.assertEquals("location", eq.getLocation());
		Assert.assertEquals(HierarchyScopeEnum.Enterprise, eq.getHierarchyScope());
		Assert.assertEquals("0", eq.getEquipmentLevel());
		Assert.assertNull(eq.getEquipments());
		Assert.assertNotNull(eq.getEquipmentClassIDs());
		Assert.assertEquals(1, eq.getEquipmentClassIDs().size());
		Assert.assertEquals("Class1", eq.getEquipmentClassIDs().get(0));
		Assert.assertNotNull(eq.getExtendedProperties());
		Assert.assertEquals(1, eq.getExtendedProperties().size());
		Assert.assertNotNull(eq.getExtendedProperty("Property1"));
		Assert.assertEquals("Value1", eq.getExtendedProperty("Property1").getValue());
		Assert.assertEquals("Class1", eq.getExtendedProperty("Property1").getParentClass());
		Assert.assertNull(eq.getParent());

	}

}
