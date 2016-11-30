package com.melinca.befreeproduction.isa95;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EquipmentsRestClientIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void findEquipments() {
		ResponseEntity<String> json = restTemplate.getForEntity("/equipments/", String.class);
		Assert.assertEquals(HttpStatus.OK, json.getStatusCode());

		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json.getBody());

		List<String> ids = JsonPath.read(document, "$.[*].id");
		Assert.assertEquals(2, ids.size());

	}

	@Test
	public void getEquipment() {
		ResponseEntity<String> json = restTemplate.getForEntity("/equipments/0001", String.class);
		Assert.assertEquals(HttpStatus.OK, json.getStatusCode());

		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json.getBody());

		Assert.assertTrue(((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].id")).contains("0001"));
		Assert.assertTrue(((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].description"))
				.contains("Water Pump machine 01"));
		Assert.assertTrue(((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].location")).contains("Area 1"));
		Assert.assertTrue(((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].equipmentLevel")).contains("2"));
		Assert.assertTrue(
				((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].hierarchyScope")).contains("WorkCell"));
		Assert.assertEquals(2,
				((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].extendedProperties.[*].id")).size());
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='serial')].id")).contains("serial"));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='serial')].value")).contains("SN00110004454"));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='serial')].description")).contains("Serial Number"));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='serial')].parentClass")).contains("Pump"));
		Assert.assertTrue(
				((List<String>) JsonPath.read(document, "$.[?(@.id=='0001')].extendedProperties.[?(@.id=='speed')].id"))
						.contains("speed"));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='speed')].value")).contains(14.5));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='speed')].description"))
						.contains("Pump speed in m3/h"));
		Assert.assertTrue(((List<String>) JsonPath.read(document,
				"$.[?(@.id=='0001')].extendedProperties.[?(@.id=='speed')].parentClass")).contains("Pump"));

	}

	@Test
	public void getEquipmentNotFound() {
		ResponseEntity<String> json = restTemplate.getForEntity("/equipments/xxxx", String.class);
		Assert.assertEquals(HttpStatus.NOT_FOUND, json.getStatusCode());
	}

	@Test
	public void createEquipmentIDEmptyFailure() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("{}", headers);
		ResponseEntity<String> json = restTemplate.postForEntity("/equipments/", entity, String.class);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, json.getStatusCode());
		String error = json.getBody();
		Assert.assertNotNull(error);

	}
}
