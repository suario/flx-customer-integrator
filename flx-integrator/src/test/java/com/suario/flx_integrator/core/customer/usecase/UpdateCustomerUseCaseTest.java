/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateCustomerUseCaseTest {

	private UpdateCustomerUseCase useCase;

	@Mock
	private CustomerRepository repository;

	@Mock
	private CustomerCrmClient client;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new UpdateCustomerUseCase(repository, client);
	}

	@Test
	void saveTest() {
		Customer object = createCustomer();
		when(repository.save(object)).thenReturn(object);
		assertNotNull(useCase.save(1L, object));
	}

	private Customer createCustomer() {
		return Customer.builder().customerId(1L).email("text").firstName("first").lastName("last")
				.phoneNumber("12313564")
				.address(Address.builder().zipCode("17000").street("street").city("city").state("IL").build()).build();
	}
}
