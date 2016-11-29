package com.melinca.befreeproduction.isa95;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.melinca.befreeproduction.commons.ExtendedProperty;

public class EquipmentUtil {

	public static Equipment buildEquipment() {
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
		return equip;
	}

	public static ExtendedProperty buildExtendedProperty(String epId) {
		ExtendedProperty ep = new ExtendedProperty();
		ep.setID(epId);
		ep.setDescription(epId + "_description");
		ep.setParentClass(epId + "_parentClass");
		ep.setValue(epId + "_value");
		return ep;
	}

}
