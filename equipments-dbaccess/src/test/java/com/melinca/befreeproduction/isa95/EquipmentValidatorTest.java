package com.melinca.befreeproduction.isa95;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EquipmentValidatorTest {

	@Autowired
	EquipmentValidator eqValidator;

	@Test
	public void validateWithParent() {
		Equipment eq = new Equipment();
		eq.setID("testID");
		eq.setHierarchyScope(HierarchyScopeEnum.ProcessCell);
		eq.setParent("parentID");
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertFalse(er.hasErrors());
	}

	@Test
	public void validateEnterpriseNode() {
		Equipment eq = new Equipment();
		eq.setID("testID");
		eq.setHierarchyScope(HierarchyScopeEnum.Enterprise);
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertFalse(er.hasErrors());
	}

	@Test
	public void notValidateEmptyID() {
		Equipment eq = new Equipment();
		eq.setHierarchyScope(HierarchyScopeEnum.Enterprise);
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertTrue(er.hasErrors());
		Assert.assertEquals(1, er.getErrorCount());
		Assert.assertNotNull(er.getFieldError("ID"));
		Assert.assertEquals("empty", er.getFieldError("ID").getCode());
		Assert.assertEquals("il campo ID non può essere null", er.getFieldError("ID").getDefaultMessage());
	}

	@Test
	public void notValidateEmptyParent() {
		Equipment eq = new Equipment();
		eq.setID("testID");
		eq.setHierarchyScope(HierarchyScopeEnum.WorkCell);
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertTrue(er.hasErrors());
		Assert.assertEquals(1, er.getErrorCount());
		Assert.assertNotNull(er.getFieldError("Parent"));
		Assert.assertEquals("empty", er.getFieldError("Parent").getCode());
	}

	@Test
	public void notValidateEmptyHierarchyScope() {
		Equipment eq = new Equipment();
		eq.setID("testID");
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertTrue(er.hasErrors());
		Assert.assertEquals(1, er.getErrorCount());
		Assert.assertNotNull(er.getFieldError("HierarchyScope"));
		Assert.assertEquals("empty", er.getFieldError("HierarchyScope").getCode());
	}

	@Test
	public void notValidateEmptyHierarchyScopeAndID() {
		Equipment eq = new Equipment();
		Errors er = new BeanPropertyBindingResult(eq, "equipment");
		eqValidator.validate(eq, er);
		Assert.assertTrue(er.hasErrors());
		Assert.assertEquals(2, er.getErrorCount());
		Assert.assertNotNull(er.getFieldError("HierarchyScope"));
		Assert.assertEquals("empty", er.getFieldError("HierarchyScope").getCode());
		Assert.assertNotNull(er.getFieldError("ID"));
		Assert.assertEquals("empty", er.getFieldError("ID").getCode());
	}

}
