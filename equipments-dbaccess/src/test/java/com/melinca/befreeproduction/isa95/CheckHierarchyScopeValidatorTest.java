package com.melinca.befreeproduction.isa95;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CheckHierarchyScopeValidatorTest {

	@Autowired
	CheckHierarchyScopeValidator validator;

	@Test
	public void testEnterpriseScope() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.Enterprise);
		eq.setParent(null);
		Assert.assertTrue(validator.isValid(eq, null));
	}

	@Test
	public void testCorrectParentScope() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.ProcessCell);
		eq.setParent("A001");
		Assert.assertTrue(validator.isValid(eq, null));
	}

	@Test
	public void testWrongParentScope() {
		Equipment eq = EquipmentUtil.buildEquipment();
		eq.setHierarchyScope(HierarchyScopeEnum.WorkCell);
		eq.setParent("E001");
		Assert.assertFalse(validator.isValid(eq, null));
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
