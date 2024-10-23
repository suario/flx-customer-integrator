/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.exceptions.BusinessException;
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
		when(repository.findById(1L)).thenReturn(new Customer());
		assertDoesNotThrow(() -> useCase.deleteById(1L));
	}

	@Test
	void throwsExceptionWhenIdIsNotFound() {
		when(repository.findById(1L)).thenReturn(null);
		assertThrows(BusinessException.class, () -> useCase.deleteById(null));
	}
}
