/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateCustomerUseCaseTest {

	private CreateCustomerUseCase useCase;

	@Mock
	private CustomerRepository repository;

	@Mock
	private CustomerCrmClient client;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new CreateCustomerUseCase(repository, client);
	}

	@Test
	void saveTest() {
		Customer object = createCustomer();
		when(repository.save(object)).thenReturn(object);
		when(client.save(any())).thenReturn(CustomerCrm.builder().id(5L).build());
		assertNotNull(useCase.save(object));
	}

	private Customer createCustomer() {
		return Customer.builder().id(1L).customerId(2L).email("text").firstName("first").lastName("last")
				.phoneNumber("12313564")
				.address(Address.builder().zipCode("17000").street("street").city("city").state("IL").build()).build();
	}
}
