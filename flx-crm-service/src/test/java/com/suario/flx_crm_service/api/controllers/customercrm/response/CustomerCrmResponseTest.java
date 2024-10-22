/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.response;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CustomerCrmResponseTest {

	@Test
	void validationTest() {
		CustomerCrmResponse object = new CustomerCrmResponse();

		object.setId(123L);
		object.setFullName("string_test1");
		object.setContactEmail("string_test2");
		object.setPrimaryPhone("string_tes");
		object.setLocation("string_test4");

		assertAll(() -> assertNotNull(object), () -> assertNotNull(object.getId()),
				() -> assertEquals(123L, object.getId()), () -> assertNotNull(object.getFullName()),
				() -> assertEquals("string_test1", object.getFullName()), () -> assertNotNull(object.getContactEmail()),
				() -> assertEquals("string_test2", object.getContactEmail()),
				() -> assertNotNull(object.getPrimaryPhone()),
				() -> assertEquals("string_tes", object.getPrimaryPhone()), () -> assertNotNull(object.getLocation()),
				() -> assertEquals("string_test4", object.getLocation()), () -> assertNotNull(object.toString()));
	}
}
