package com.melinca.befreeproduction.isa95;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.melinca.befreeproduction.commons.converters.DBObjectExtendedPropertiesConverter;
import com.melinca.befreeproduction.commons.converters.ExtendedPropertiesDBObjectConverter;
import com.melinca.befreeproduction.isa95.converters.DBObjectEquipmentConverter;
import com.melinca.befreeproduction.isa95.converters.EquipmentDBObjectConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.melinca.befreeproduction")
public class EquipmentsDbAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentsDbAccessApplication.class, args);
	}

	@Bean
	public MongoClient mongoClient() throws UnknownHostException {
		return new MongoClient(
				// new
				// MongoClientURI("mongodb://bfp:bfp@ds145365.mlab.com:45365/be-free-production-isa95")
				new MongoClientURI("mongodb://bfp:bfp@localhost:27017/be-free-production-isa95")

		);
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		return new SimpleMongoDbFactory(mongoClient(), "be-free-production-isa95");
	}

	@Bean
	public ExtendedPropertiesDBObjectConverter extendedPropertiesDBObjectConverter() {
		return new ExtendedPropertiesDBObjectConverter();
	}

	@Bean
	public EquipmentDBObjectConverter equipmentDBObjectConverter() {
		return new EquipmentDBObjectConverter(extendedPropertiesDBObjectConverter());
	}

	@Bean
	public DBObjectExtendedPropertiesConverter dBObjectExtendedPropertiesConverter() {
		return new DBObjectExtendedPropertiesConverter();
	}

	@Bean
	public DBObjectEquipmentConverter dBObjectEquipmentConverter() {
		return new DBObjectEquipmentConverter(dBObjectExtendedPropertiesConverter());
	}

	@Bean
	public EquipmentsController equipmentsController() {
		return new EquipmentsController();
	}

	@Bean
	public ResourceBundleMessageSource validatorMessageSource() {
		ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		bundle.addBasenames("classpath:i18n/validation");
		return bundle;
	}

	@Bean
	public CustomConversions customConversions() {
		List<Converter> customConverters = new ArrayList<>();
		customConverters.add(equipmentDBObjectConverter());
		customConverters.add(dBObjectEquipmentConverter());
		return new CustomConversions(customConverters);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		MappingMongoConverter converter = (MappingMongoConverter) mongoTemplate.getConverter();
		converter.setCustomConversions(customConversions());
		converter.afterPropertiesSet();
		return mongoTemplate;
	}
}
