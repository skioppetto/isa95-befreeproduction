package com.melinca.befreeproduction.isa95;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class HierarchyScopeRules {

	public final static Map<HierarchyScopeEnum, Set<HierarchyScopeEnum>> childrenHierarchies = new HashMap<HierarchyScopeEnum, Set<HierarchyScopeEnum>>() {

		private static final long serialVersionUID = 1L;

		{
			put(HierarchyScopeEnum.Enterprise,
					new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.Site, HierarchyScopeEnum.Area)));
			put(HierarchyScopeEnum.Site, new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.Area)));
			put(HierarchyScopeEnum.Area,
					new HashSet<HierarchyScopeEnum>(
							Arrays.asList(HierarchyScopeEnum.ProcessCell, HierarchyScopeEnum.ProductionUnit,
									HierarchyScopeEnum.ProductionLine, HierarchyScopeEnum.StorageZone)));
			put(HierarchyScopeEnum.ProcessCell,
					new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.Unit)));
			put(HierarchyScopeEnum.ProductionUnit,
					new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.Unit)));
			put(HierarchyScopeEnum.ProductionLine,
					new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.WorkCell)));
			put(HierarchyScopeEnum.StorageZone,
					new HashSet<HierarchyScopeEnum>(Arrays.asList(HierarchyScopeEnum.StorageUnit)));
		}
	};

}
