package com.melinca.befreeproduction.isa95.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.melinca.befreeproduction.commons.converters.ExtendedPropertiesDBObjectConverter;
import com.melinca.befreeproduction.isa95.Equipment;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EquipmentDBObjectConverter implements Converter<Equipment, DBObject> {

	@Autowired
	private ExtendedPropertiesDBObjectConverter extendedPropertiesDBObjectConverter;

	public EquipmentDBObjectConverter(ExtendedPropertiesDBObjectConverter extendedPropertiesDBObjectConverter) {
		this.extendedPropertiesDBObjectConverter = extendedPropertiesDBObjectConverter;
	}

	@Override
	public DBObject convert(Equipment equip) {
		DBObject obj = new BasicDBObject();
		obj.put("_id", equip.getID());
		obj.put("description", equip.getDescription());
		obj.put("equipmentLevel", equip.getEquipmentLevel());
		obj.put("hierarchyScope", equip.getHierarchyScope().toString());
		obj.put("location", equip.getLocation());
		obj.put("equipmentClassIDs", equip.getEquipmentClassIDs());
		if (null != equip.getExtendedProperties() && !equip.getExtendedProperties().isEmpty()) {
			DBObject extendedProperties = extendedPropertiesDBObjectConverter.convert(equip.getExtendedProperties());
			obj.putAll(extendedProperties);
		}
		return obj;
	}

}
