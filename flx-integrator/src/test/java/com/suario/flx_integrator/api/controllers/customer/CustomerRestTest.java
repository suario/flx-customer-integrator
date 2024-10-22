/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.api.controllers.customer.request.AddressRequest;
import com.suario.flx_integrator.api.controllers.customer.request.CustomerRequest;
import com.suario.flx_integrator.api.controllers.customer.response.CustomerResponse;
import com.suario.flx_integrator.api.dtos.ListWrapperResponse;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.usecase.CreateCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.DeleteCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.ReadCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.UpdateCustomerUseCase;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerRestTest {

	private CustomerRest rest;

	@Mock
	private CreateCustomerUseCase createCustomerUseCase;

	@Mock
	private ReadCustomerUseCase readCustomerUseCase;

	@Mock
	private UpdateCustomerUseCase updateCustomerUseCase;

	@Mock
	private DeleteCustomerUseCase deleteCustomerUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		rest = new CustomerRest(createCustomerUseCase, readCustomerUseCase, updateCustomerUseCase,
				deleteCustomerUseCase);
	}

	@Test
	void listTest() {
		List<Customer> objectList = List.of(new Customer());
		when(readCustomerUseCase.findAll()).thenReturn(objectList);
		ListWrapperResponse<CustomerResponse> result = rest.list();

		assertAll(() -> assertNotNull(result), () -> assertEquals(1, result.records().size()));
	}

	@Test
	void getOneTest() {
		Long id = 2L;
		Customer object = new Customer();
		when(readCustomerUseCase.findById(id)).thenReturn(object);

		assertNotNull(rest.getOne(id));
	}

	@Test
	void getOneIsNullWhenTheIdIsNotFoundTest() {
		Long id = 2L;
		when(readCustomerUseCase.findById(id)).thenReturn(null);

		assertNull(rest.getOne(id));
	}

	@Test
	void createTest() {
		CustomerRequest request = createCustomerRequest();
		Customer object = new Customer();
		when(createCustomerUseCase.save(any())).thenReturn(object);

		assertNotNull(rest.create(request));
	}

	@Test
	void createIsNullWhenTheRequestIsNullTest() {
		when(createCustomerUseCase.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			rest.create(null);
		});
	}

	@Test
	void updateTest() {
		CustomerRequest request = createCustomerRequest();
		assertDoesNotThrow(() -> {
			rest.update(2L, request);
		});
	}

	@Test
	void updateThrowsExceptionWhenRequestIsNullTest() {
		when(updateCustomerUseCase.save(2L, null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			rest.update(2L, null);
		});
	}

	@Test
	void deleteTest() {
		assertDoesNotThrow(() -> rest.delete(1L));
	}

	private CustomerRequest createCustomerRequest() {
		CustomerRequest request = new CustomerRequest();
		request.setAddress(new AddressRequest());
		request.getAddress().setState("IL");
		return request;
	}
}
