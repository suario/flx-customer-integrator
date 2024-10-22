/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReadCustomerCrmUseCaseTest {

	private ReadCustomerCrmUseCase useCase;

	@Mock
	private CustomerCrmRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new ReadCustomerCrmUseCase(repository);
	}

	@Test
	void findByIdTest() {
		when(repository.findById(1L)).thenReturn(CustomerCrm.builder().build());
		assertNotNull(useCase.findById(1L));
	}

	@Test
	void findByIdWhenTheInputIsNotFoundTest() {
		when(repository.findById(2L)).thenReturn(null);
		assertNull(useCase.findById(2L));
	}

	@Test
	void findByIdWhenTheInputIsNullTest() {
		when(repository.findById(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> useCase.findById(null));
	}

	@Test
	void findByExampleTest() {
		CustomerCrm object = CustomerCrm.builder().build();
		when(repository.findByExample(object)).thenReturn(List.of(object));
		List<CustomerCrm> objectList = useCase.findByExample(object);
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void findByExampleWhenTheInputIsNullTest() {
		when(repository.findByExample(null)).thenReturn(new ArrayList<>());
		List<CustomerCrm> objectList = useCase.findByExample(null);
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(0, objectList.size()));
	}

	@Test
	void findAllTest() {
		when(repository.findAll()).thenReturn(List.of(CustomerCrm.builder().build()));
		List<CustomerCrm> objectList = useCase.findAll();
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}
}
