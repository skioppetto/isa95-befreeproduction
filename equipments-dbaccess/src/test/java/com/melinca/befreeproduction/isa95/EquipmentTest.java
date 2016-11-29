package com.melinca.befreeproduction.isa95;

import org.junit.Assert;
import org.junit.Test;

import com.melinca.befreeproduction.commons.ExtendedProperty;

public class EquipmentTest {

	@Test
	public void testGetExtendedProperty() {
		Equipment equipment = EquipmentUtil.buildEquipment();
		ExtendedProperty ep = equipment.getExtendedProperty("prop1");
		Assert.assertNotNull(ep);
		Assert.assertEquals("prop1", ep.getID());
	}

	@Test
	public void testGetExtendedPropertyAfterAdd() {
		Equipment equipment = EquipmentUtil.buildEquipment();
		equipment.getExtendedProperties().add(EquipmentUtil.buildExtendedProperty("prop3"));
		ExtendedProperty ep = equipment.getExtendedProperty("prop3");
		Assert.assertNotNull(ep);
		Assert.assertEquals("prop3", ep.getID());
	}

	@Test
	public void testGetExtendedPropertyAfterDelete() {
		Equipment equipment = EquipmentUtil.buildEquipment();
		equipment.getExtendedProperties().removeIf(item -> "prop1".equals(item.getID()));
		ExtendedProperty ep = equipment.getExtendedProperty("prop1");
		Assert.assertNull(ep);
	}

}
