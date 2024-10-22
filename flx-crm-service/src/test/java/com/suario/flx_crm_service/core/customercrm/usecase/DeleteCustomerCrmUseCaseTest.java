/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import com.suario.flx_crm_service.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeleteCustomerCrmUseCaseTest {

	private DeleteCustomerCrmUseCase useCase;

	@Mock
	private CustomerCrmRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new DeleteCustomerCrmUseCase(repository);
	}

	@Test
	void deleteByIdTest() {
		when(repository.existsById(1L)).thenReturn(true);
		assertDoesNotThrow(() -> useCase.deleteById(1L));
	}

	@Test
	void deleteByIdWhenTheInputIsNullTest() {
		when(repository.existsById(null)).thenReturn(false);
		assertThrows(BusinessException.class, () -> useCase.deleteById(2L));
	}

	@Test
	void deleteByIdWhenTheInputIsNotFound() {
		when(repository.existsById(1L)).thenReturn(false);
		assertThrows(BusinessException.class, () -> useCase.deleteById(1L));
	}
}
