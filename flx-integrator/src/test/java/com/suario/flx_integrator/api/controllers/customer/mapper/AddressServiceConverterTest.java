/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_integrator.api.controllers.customer.request.AddressRequest;
import com.suario.flx_integrator.api.controllers.customer.response.AddressResponse;
import com.suario.flx_integrator.core.customer.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressServiceConverterTest {

	private AddressServiceConverter converter;

	@BeforeEach
	void setUp() {
		converter = AddressServiceConverter.MAPPER;
	}

	@Test
	void toResponseWhenInputIsNullTest() {
		assertNull(converter.toResponse(null));
	}

	@Test
	void toResponseTest() {
		Address object = new Address();
		object.setId(123L);
		object.setStreet("string_test1");
		object.setCity("string_test2");
		object.setState("string_test3");
		object.setZipCode("string_t");

		AddressResponse entity = converter.toResponse(object);

		assertAll(() -> assertNotNull(entity.getId()), () -> assertEquals(object.getId(), entity.getId()),
				() -> assertNotNull(entity.getStreet()), () -> assertEquals(object.getStreet(), entity.getStreet()),
				() -> assertNotNull(entity.getCity()), () -> assertEquals(object.getCity(), entity.getCity()),
				() -> assertNotNull(entity.getState()), () -> assertEquals(object.getState(), entity.getState()),
				() -> assertNotNull(entity.getZipCode()), () -> assertEquals(object.getZipCode(), entity.getZipCode()),
				() -> assertNotNull(object));
	}

	@Test
	void toEntityWhenInputIsNullTest() {
		assertNull(converter.toModel(null));
	}

	@Test
	void toEntityTest() {
		AddressRequest request = new AddressRequest();
		request.setStreet("string_test1");
		request.setCity("string_test2");
		request.setState("string_test3");
		request.setZipCode("string_t");

		Address object = converter.toModel(request);

		assertAll(() -> assertNull(object.getId()), () -> assertNotNull(object.getStreet()),
				() -> assertEquals(request.getStreet(), object.getStreet()), () -> assertNotNull(object.getCity()),
				() -> assertEquals(request.getCity(), object.getCity()), () -> assertNotNull(object.getState()),
				() -> assertEquals(request.getState(), object.getState()), () -> assertNotNull(object.getZipCode()),
				() -> assertEquals(request.getZipCode(), object.getZipCode()), () -> assertNotNull(object));
	}
}
