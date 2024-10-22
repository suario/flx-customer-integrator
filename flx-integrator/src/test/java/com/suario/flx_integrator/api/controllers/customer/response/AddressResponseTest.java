/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.response;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AddressResponseTest {

	@Test
	void validationTest() {
		AddressResponse object = new AddressResponse();

		object.setId(123L);
		object.setStreet("string_test1");
		object.setCity("string_test2");
		object.setState("string_test3");
		object.setZipCode("string_t");

		assertAll(() -> assertNotNull(object), () -> assertNotNull(object.getId()),
				() -> assertEquals(123L, object.getId()), () -> assertNotNull(object.getStreet()),
				() -> assertEquals("string_test1", object.getStreet()), () -> assertNotNull(object.getCity()),
				() -> assertEquals("string_test2", object.getCity()), () -> assertNotNull(object.getState()),
				() -> assertEquals("string_test3", object.getState()), () -> assertNotNull(object.getZipCode()),
				() -> assertEquals("string_t", object.getZipCode()), () -> assertNotNull(object.toString()));
	}
}
