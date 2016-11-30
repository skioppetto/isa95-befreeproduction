package com.melinca.befreeproduction.isa95;

import org.junit.Assert;
import org.junit.Test;

public class CheckHierarchyScopeValidatorTest {

	CheckHierarchyScopeValidator validator = new CheckHierarchyScopeValidator();

	@Test
	public void testEnterpriseScope() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.Enterprise);
		eq.setParent(null);
		Assert.assertTrue(validator.isValid(eq, null));
	}

	@Test
	public void testOthterScopes() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.WorkCell);
		eq.setParent("ParentEq");
		Assert.assertTrue(validator.isValid(eq, null));
	}

	@Test
	public void testEnterpriseScopeParentNotNull() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.Enterprise);
		eq.setParent("ParentEq");
		Assert.assertFalse(validator.isValid(eq, null));
	}

	@Test
	public void testOthterScopesParentNull() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.WorkCell);
		eq.setParent(null);
		Assert.assertFalse(validator.isValid(eq, null));
	}

	@Test
	public void testHierarchyScopeNull() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(null);
		eq.setParent("ParentEq");
		Assert.assertFalse(validator.isValid(eq, null));
	}

}
