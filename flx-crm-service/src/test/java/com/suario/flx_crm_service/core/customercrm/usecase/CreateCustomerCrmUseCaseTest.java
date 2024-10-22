/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateCustomerCrmUseCaseTest {

	private CreateCustomerCrmUseCase useCase;

	@Mock
	private CustomerCrmRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new CreateCustomerCrmUseCase(repository);
	}

	@Test
	void saveTest() {
		CustomerCrm object = CustomerCrm.builder().build();
		when(repository.save(object)).thenReturn(object);
		assertNotNull(useCase.save(object));
	}

	@Test
	void saveWhenTheInputIsNullTest() {
		when(repository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> useCase.save(null));
	}
}
