package com.melinca.befreeproduction.isa95;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.melinca.befreeproduction.commons.ExtendedProperty;


public class Equipment {

	@NotEmpty
	private String ID;
	private String Description;
	private String Location;
	private HierarchyScopeEnum HierarchyScope;
	private String EquipmentLevel;
	private List<ExtendedProperty> ExtendedProperties;
	private List<Equipment> Equipments;
	private List<String> EquipmentClassIDs;
	private String Parent;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public HierarchyScopeEnum getHierarchyScope() {
		return HierarchyScope;
	}

	public void setHierarchyScope(HierarchyScopeEnum hierarchyScope) {
		HierarchyScope = hierarchyScope;
	}

	public String getEquipmentLevel() {
		return EquipmentLevel;
	}

	public void setEquipmentLevel(String equipmentLevel) {
		EquipmentLevel = equipmentLevel;
	}

	public List<ExtendedProperty> getExtendedProperties() {
		return ExtendedProperties;
	}

	public void setExtendedProperties(List<ExtendedProperty> extendedProperties) {
		ExtendedProperties = extendedProperties;
	}

	public List<Equipment> getEquipments() {
		return Equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		Equipments = equipments;
	}

	public List<String> getEquipmentClassIDs() {
		return EquipmentClassIDs;
	}

	public void setEquipmentClassIDs(List<String> equipmentClassIDs) {
		EquipmentClassIDs = equipmentClassIDs;
	}
	
	public String getParent() {
		return Parent;
	}

	public void setParent(String parent) {
		Parent = parent;
	}

	public ExtendedProperty getExtendedProperty(String ID) {
		if (null != ExtendedProperties) {
			return ExtendedProperties.stream().filter(item -> item.getID().equals(ID)).findFirst().orElse(null);
		} else
			return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
}
