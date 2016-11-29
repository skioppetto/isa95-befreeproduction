package com.melinca.befreeproduction.isa95.converters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.melinca.befreeproduction.commons.converters.DBObjectExtendedPropertiesConverter;
import com.melinca.befreeproduction.isa95.Equipment;
import com.melinca.befreeproduction.isa95.HierarchyScopeEnum;
import com.mongodb.DBObject;

public class DBObjectEquipmentConverter implements Converter<DBObject, Equipment> {

	private DBObjectExtendedPropertiesConverter extendedPropertiesConverter;
	private static final Set<String> fields = new HashSet<String>(
			Arrays.asList("_id", "description", "equipmentClassIDs", "equipmentLevel", "hierarchyScope", "location"));

	public DBObjectEquipmentConverter(DBObjectExtendedPropertiesConverter extendedPropertiesConverter) {
		super();
		this.extendedPropertiesConverter = extendedPropertiesConverter;
	}

	@Override
	public Equipment convert(DBObject dbObject) {
		Equipment eq = new Equipment();
		eq.setID((String) dbObject.get("_id"));
		eq.setDescription((String) dbObject.get("description"));
		eq.setEquipmentClassIDs((List<String>) dbObject.get("equipmentClassIDs"));
		eq.setEquipmentLevel((String) dbObject.get("equipmentLevel"));
		eq.setHierarchyScope(HierarchyScopeEnum.valueOf((String) dbObject.get("hierarchyScope")));
		eq.setLocation((String) dbObject.get("location"));
		fields.forEach(item -> dbObject.removeField(item));
		eq.setExtendedProperties(extendedPropertiesConverter.convert(dbObject));
		return eq;
	}

}
