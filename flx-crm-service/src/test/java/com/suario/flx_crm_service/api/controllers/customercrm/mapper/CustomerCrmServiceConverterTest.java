/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_crm_service.api.controllers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_crm_service.api.controllers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerCrmServiceConverterTest {

	private CustomerCrmServiceConverter converter;

	@BeforeEach
	void setUp() {
		converter = CustomerCrmServiceConverter.MAPPER;
	}

	@Test
	void toResponseWhenInputIsNullTest() {
		assertNull(converter.toResponse(null));
	}

	@Test
	void toResponseTest() {
		CustomerCrm object = new CustomerCrm();
		object.setId(123L);
		object.setFullName("string_test1");
		object.setContactEmail("string_test2");
		object.setPrimaryPhone("string_tes");
		object.setLocation("string_test4");

		CustomerCrmResponse entity = converter.toResponse(object);

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
		assertNull(converter.toModel(null));
	}

	@Test
	void toEntityTest() {
		CustomerCrmRequest request = new CustomerCrmRequest();
		request.setFullName("string_test1");
		request.setContactEmail("string_test2");
		request.setPrimaryPhone("string_tes");
		request.setLocation("string_test4");

		CustomerCrm object = converter.toModel(request);

		assertAll(() -> assertNull(object.getId()), () -> assertNotNull(object.getFullName()),
				() -> assertEquals(request.getFullName(), object.getFullName()),
				() -> assertNotNull(object.getContactEmail()),
				() -> assertEquals(request.getContactEmail(), object.getContactEmail()),
				() -> assertNotNull(object.getPrimaryPhone()),
				() -> assertEquals(request.getPrimaryPhone(), object.getPrimaryPhone()),
				() -> assertNotNull(object.getLocation()),
				() -> assertEquals(request.getLocation(), object.getLocation()), () -> assertNotNull(object));
	}
}
