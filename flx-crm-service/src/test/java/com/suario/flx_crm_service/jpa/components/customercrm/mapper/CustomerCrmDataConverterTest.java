/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.jpa.components.customercrm.entity.CustomerCrmData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivecommons.utils.ObjectMapperImp;

public class CustomerCrmDataConverterTest {

	private CustomerCrmDataConverter converter;

	@BeforeEach
	void setUp() {
		converter = new CustomerCrmDataConverter(new ObjectMapperImp());
	}

	@Test
	void toDataWhenInputIsNullTest() {
		assertNull(converter.toData(null));
	}

	@Test
	void toDataTest() {
		CustomerCrm object = new CustomerCrm();
		object.setId(123L);
		object.setFullName("string_test1");
		object.setContactEmail("string_test2");
		object.setPrimaryPhone("string_tes");
		object.setLocation("string_test4");

		CustomerCrmData entity = converter.toData(object);

		assertAll(() -> assertNotNull(entity.getId()), () -> assertEquals(object.getId(), entity.getId()),
				() -> assertNotNull(entity.getFullName()),
				() -> assertEquals(object.getFullName(), entity.getFullName()),
				() -> assertNotNull(entity.getContactEmail()),
				() -> assertEquals(object.getContactEmail(), entity.getContactEmail()),
				() -> assertNotNull(entity.getPrimaryPhone()),
				() -> assertEquals(object.getPrimaryPhone(), entity.getPrimaryPhone()),
				() -> assertNotNull(entity.getLocation()),
				() -> assertEquals(object.getLocation(), entity.getLocation()), () -> assertNotNull(object));
	}

	@Test
	void toEntityWhenInputIsNullTest() {
		assertNull(converter.toEntity(null));
	}

	@Test
	void toEntityTest() {
		CustomerCrmData entity = new CustomerCrmData();
		entity.setId(123L);
		entity.setFullName("string_test1");
		entity.setContactEmail("string_test2");
		entity.setPrimaryPhone("string_tes");
		entity.setLocation("string_test4");

		CustomerCrm object = converter.toEntity(entity);

		assertAll(() -> assertNotNull(object.getId()), () -> assertEquals(entity.getId(), object.getId()),
				() -> assertNotNull(object.getFullName()),
				() -> assertEquals(entity.getFullName(), object.getFullName()),
				() -> assertNotNull(object.getContactEmail()),
				() -> assertEquals(entity.getContactEmail(), object.getContactEmail()),
				() -> assertNotNull(object.getPrimaryPhone()),
				() -> assertEquals(entity.getPrimaryPhone(), object.getPrimaryPhone()),
				() -> assertNotNull(object.getLocation()),
				() -> assertEquals(entity.getLocation(), object.getLocation()), () -> assertNotNull(object));
	}
}
