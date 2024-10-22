/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.response;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CustomerResponseTest {

	@Test
	void validationTest() {
		CustomerResponse object = new CustomerResponse();

		object.setCustomerId(123L);
		object.setFirstName("string_test1");
		object.setLastName("string_test2");
		object.setEmail("string_test3");
		object.setPhoneNumber("string_tes");
		object.setAddress(new AddressResponse());

		assertAll(() -> assertNotNull(object), () -> assertNotNull(object.getCustomerId()),
				() -> assertEquals(123L, object.getCustomerId()), () -> assertNotNull(object.getFirstName()),
				() -> assertEquals("string_test1", object.getFirstName()), () -> assertNotNull(object.getLastName()),
				() -> assertEquals("string_test2", object.getLastName()), () -> assertNotNull(object.getEmail()),
				() -> assertEquals("string_test3", object.getEmail()), () -> assertNotNull(object.getPhoneNumber()),
				() -> assertEquals("string_tes", object.getPhoneNumber()), () -> assertNotNull(object.getAddress()),
				() -> assertNotNull(object.toString()));
	}
}
