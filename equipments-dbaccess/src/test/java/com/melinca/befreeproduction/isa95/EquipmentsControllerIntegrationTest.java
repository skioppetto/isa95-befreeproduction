package com.melinca.befreeproduction.isa95;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentsControllerIntegrationTest {

	@Autowired
	private EquipmentsController service;

	@Test
	public void getEquipment() throws EquipmentNotFoundException {
		Equipment equipment = service.getEquipment("0001");
		Assert.assertNotNull(equipment);
		Assert.assertEquals("0001", equipment.getID());
		Assert.assertEquals("Water Pump machine 01", equipment.getDescription());
		Assert.assertEquals("2", equipment.getEquipmentLevel());
		Assert.assertEquals(HierarchyScopeEnum.WorkCell, equipment.getHierarchyScope());
		Assert.assertEquals("Area 1", equipment.getLocation());
		Assert.assertNotNull(equipment.getEquipmentClassIDs());
		Assert.assertEquals(1, equipment.getEquipmentClassIDs().size());
		Assert.assertEquals("Pump", equipment.getEquipmentClassIDs().get(0));
		Assert.assertNotNull(equipment.getExtendedProperties());
		Assert.assertEquals(2, equipment.getExtendedProperties().size());
		Assert.assertNotNull(equipment.getExtendedProperty("speed"));
		Assert.assertEquals(14.50, equipment.getExtendedProperty("speed").getValue());
		Assert.assertEquals("Pump speed in m3/h", equipment.getExtendedProperty("speed").getDescription());
		Assert.assertEquals("Pump", equipment.getExtendedProperty("speed").getParentClass());
		Assert.assertNotNull(equipment.getExtendedProperty("serial"));
		Assert.assertEquals("SN00110004454", equipment.getExtendedProperty("serial").getValue());
		Assert.assertEquals("Serial Number", equipment.getExtendedProperty("serial").getDescription());
		Assert.assertEquals("Pump", equipment.getExtendedProperty("serial").getParentClass());
	}

	@Test(expected = EquipmentNotFoundException.class)
	public void getEquipmentNotFound() throws EquipmentNotFoundException {
		service.getEquipment("DDDD");
	}

	@Test
	public void findEquipments() {
		List<Equipment> eqs = service.retrieveEquipments();
		Assert.assertNotNull(eqs);
		Assert.assertEquals(2, eqs.size());
	}

	@Test
	public void saveEquipment() throws EquipmentNotFoundException {
		Equipment equipment = service.getEquipment("0002");
		equipment.setLocation("New Location");
		service.saveEquipment(equipment);
		Equipment updated = service.getEquipment("0002");
		Assert.assertEquals("New Location", updated.getLocation());
	}

}
