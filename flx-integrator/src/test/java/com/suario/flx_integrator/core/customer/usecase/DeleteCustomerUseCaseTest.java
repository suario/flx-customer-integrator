/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeleteCustomerUseCaseTest {

	private DeleteCustomerUseCase useCase;

	@Mock
	private CustomerRepository repository;

	@Mock
	private CustomerCrmClient client;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new DeleteCustomerUseCase(repository, client);
	}

	@Test
	void deleteByIdTest() {
		assertDoesNotThrow(() -> useCase.deleteById(1L));
	}

	@Test
	void deleteByIdWhenTheInputIsNullTest() {
		doThrow(IllegalArgumentException.class).when(repository).deleteById(null);
		assertThrows(IllegalArgumentException.class, () -> useCase.deleteById(null));
	}
}
