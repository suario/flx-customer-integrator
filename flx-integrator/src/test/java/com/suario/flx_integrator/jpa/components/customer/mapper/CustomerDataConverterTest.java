/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.jpa.components.customer.entity.AddressData;
import com.suario.flx_integrator.jpa.components.customer.entity.CustomerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivecommons.utils.ObjectMapperImp;

public class CustomerDataConverterTest {

	private CustomerDataConverter converter;

	@BeforeEach
	void setUp() {
		converter = new CustomerDataConverter(new ObjectMapperImp());
	}

	@Test
	void toDataWhenInputIsNullTest() {
		assertNull(converter.toData(null));
	}

	@Test
	void toDataTest() {
		Customer object = new Customer();
		object.setCustomerId(123L);
		object.setFirstName("string_test1");
		object.setLastName("string_test2");
		object.setEmail("string_test3");
		object.setPhoneNumber("string_tes");
		object.setAddress(Address.builder().build());

		CustomerData entity = converter.toData(object);

		assertAll(() -> assertNotNull(entity.getCustomerId()),
				() -> assertEquals(object.getCustomerId(), entity.getCustomerId()),
				() -> assertNotNull(entity.getFirstName()),
				() -> assertEquals(object.getFirstName(), entity.getFirstName()),
				() -> assertNotNull(entity.getLastName()),
				() -> assertEquals(object.getLastName(), entity.getLastName()), () -> assertNotNull(entity.getEmail()),
				() -> assertEquals(object.getEmail(), entity.getEmail()), () -> assertNotNull(entity.getPhoneNumber()),
				() -> assertEquals(object.getPhoneNumber(), entity.getPhoneNumber()),
				() -> assertNotNull(entity.getAddress()), () -> assertNotNull(object));
	}

	@Test
	void toEntityWhenInputIsNullTest() {
		assertNull(converter.toEntity(null));
	}

	@Test
	void toEntityTest() {
		CustomerData entity = new CustomerData();
		entity.setCustomerId(123L);
		entity.setFirstName("string_test1");
		entity.setLastName("string_test2");
		entity.setEmail("string_test3");
		entity.setPhoneNumber("string_tes");
		entity.setAddress(new AddressData());

		Customer object = converter.toEntity(entity);

		assertAll(() -> assertNotNull(object.getCustomerId()),
				() -> assertEquals(entity.getCustomerId(), object.getCustomerId()),
				() -> assertNotNull(object.getFirstName()),
				() -> assertEquals(entity.getFirstName(), object.getFirstName()),
				() -> assertNotNull(object.getLastName()),
				() -> assertEquals(entity.getLastName(), object.getLastName()), () -> assertNotNull(object.getEmail()),
				() -> assertEquals(entity.getEmail(), object.getEmail()), () -> assertNotNull(object.getPhoneNumber()),
				() -> assertEquals(entity.getPhoneNumber(), object.getPhoneNumber()),
				() -> assertNotNull(object.getAddress()), () -> assertNotNull(object));
	}
}
