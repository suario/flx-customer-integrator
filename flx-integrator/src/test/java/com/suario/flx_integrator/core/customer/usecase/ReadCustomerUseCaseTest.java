/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.exceptions.BusinessException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReadCustomerUseCaseTest {

	private ReadCustomerUseCase useCase;

	@Mock
	private CustomerRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		useCase = new ReadCustomerUseCase(repository);
	}

	@Test
	void findByIdTest() {
		when(repository.findById(1L)).thenReturn(Customer.builder().build());
		assertNotNull(useCase.findById(1L));
	}

	@Test
	void findByIdWhenTheInputIsNotFoundTest() {
		when(repository.findById(2L)).thenReturn(null);
		assertThrows(BusinessException.class, () -> useCase.findById(2L));
	}

	@Test
	void findByIdWhenTheInputIsNullTest() {
		when(repository.findById(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> useCase.findById(null));
	}

	@Test
	void findByExampleTest() {
		Customer object = Customer.builder().build();
		when(repository.findByExample(object)).thenReturn(List.of(object));
		List<Customer> objectList = useCase.findByExample(object);
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void findByExampleWhenTheInputIsNullTest() {
		when(repository.findByExample(null)).thenReturn(new ArrayList<>());
		List<Customer> objectList = useCase.findByExample(null);
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(0, objectList.size()));
	}

	@Test
	void findAllTest() {
		when(repository.findAll()).thenReturn(List.of(Customer.builder().build()));
		List<Customer> objectList = useCase.findAll();
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}
}
