/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import org.junit.jupiter.api.Test;

public class CustomerBusinessConverterTest {

	private final CustomerBusinessConverter converter = CustomerBusinessConverter.MAPPER;;

	@Test
	void toResponseWhenInputIsNullTest() {
		assertNull(converter.toCRMCustomer(null));
	}

	@Test
	void toCRMCustomerTest() {
		Customer object = new Customer();
		object.setId(456L);
		object.setCustomerId(123L);
		object.setFirstName("Juan");
		object.setLastName("Smith");
		object.setEmail("email@email.com");
		object.setPhoneNumber("1234567890");
		object.setAddress(
				Address.builder().id(123L).street("Street").city("City").state("State").zipCode("12356").build());

		CustomerCrm entity = converter.toCRMCustomer(object);

		assertAll(() -> assertNotNull(entity.getId()), () -> assertEquals(object.getId(), entity.getId()),
				() -> assertNotNull(entity.getContactEmail()),
				() -> assertEquals(object.getEmail(), entity.getContactEmail()),
				() -> assertNotNull(entity.getPrimaryPhone()),
				() -> assertEquals(object.getPhoneNumber(), entity.getPrimaryPhone()),
				() -> assertNotNull(entity.getFullName()), () -> assertEquals("Smith, Juan", entity.getFullName()),
				() -> assertNotNull(entity.getLocation()),
				() -> assertEquals("Street, City, State, 12356", entity.getLocation()));
	}
}
