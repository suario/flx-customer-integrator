/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateCustomerCrmUseCaseTest {

	private UpdateCustomerCrmUseCase useCase;

	@Mock
	private CustomerCrmRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new UpdateCustomerCrmUseCase(repository);
	}

	@Test
	void saveTest() {
		CustomerCrm object = CustomerCrm.builder().build();
		when(repository.existsById(1L)).thenReturn(true);
		when(repository.save(object)).thenReturn(object);
		assertNotNull(useCase.save(1L, object));
	}
}
