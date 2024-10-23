/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_integrator.api.controllers.customer.request.AddressRequest;
import com.suario.flx_integrator.api.controllers.customer.request.CustomerRequest;
import com.suario.flx_integrator.api.controllers.customer.response.CustomerResponse;
import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerServiceConverterTest {

	private CustomerServiceConverter converter;

	@BeforeEach
	void setUp() {
		converter = CustomerServiceConverter.MAPPER;
	}

	@Test
	void toResponseWhenInputIsNullTest() {
		assertNull(converter.toResponse(null));
	}

	@Test
	void toResponseTest() {
		Customer object = new Customer();
		object.setId(456L);
		object.setCustomerId(123L);
		object.setFirstName("Juan");
		object.setLastName("Smith");
		object.setEmail("email@email.com");
		object.setPhoneNumber("1234567890");
		object.setAddress(Address.builder().id(123L).build());

		CustomerResponse entity = converter.toResponse(object);

		assertAll(() -> assertNotNull(entity.getCustomerId()),
				() -> assertEquals(object.getId(), entity.getCustomerId()), () -> assertNotNull(entity.getFirstName()),
				() -> assertEquals(object.getFirstName(), entity.getFirstName()),
				() -> assertNotNull(entity.getLastName()),
				() -> assertEquals(object.getLastName(), entity.getLastName()), () -> assertNotNull(entity.getEmail()),
				() -> assertEquals(object.getEmail(), entity.getEmail()), () -> assertNotNull(entity.getPhoneNumber()),
				() -> assertEquals(object.getPhoneNumber(), entity.getPhoneNumber()),
				() -> assertNotNull(entity.getAddress()), () -> assertNotNull(object));
	}

	@Test
	void toEntityWhenInputIsNullTest() {
		assertNull(converter.toModel(null));
	}

	@Test
	void toEntityTest() {
		CustomerRequest request = new CustomerRequest();
		request.setFirstName("Juan");
		request.setLastName("Smith");
		request.setEmail("email@email.com");
		request.setPhoneNumber("1234567890");
		request.setAddress(new AddressRequest());

		Customer object = converter.toModel(request);

		assertAll(() -> assertNull(object.getCustomerId()), () -> assertNotNull(object.getFirstName()),
				() -> assertEquals(request.getFirstName(), object.getFirstName()),
				() -> assertNotNull(object.getLastName()),
				() -> assertEquals(request.getLastName(), object.getLastName()), () -> assertNotNull(object.getEmail()),
				() -> assertEquals(request.getEmail(), object.getEmail()), () -> assertNotNull(object.getPhoneNumber()),
				() -> assertEquals(request.getPhoneNumber(), object.getPhoneNumber()),
				() -> assertNotNull(object.getAddress()), () -> assertNotNull(object));
	}
}
