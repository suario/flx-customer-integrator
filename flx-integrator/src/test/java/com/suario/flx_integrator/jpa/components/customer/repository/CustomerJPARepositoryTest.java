/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.suario.flx_integrator.jpa.components.customer.entity.CustomerData;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

public class CustomerJPARepositoryTest {

	@Mock
	private CustomerJPARepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTest() {
		CustomerData object = new CustomerData();
		when(repository.save(object)).thenReturn(object);
		assertNotNull(repository.save(object));
	}

	@Test
	void saveWhenTheInputIsNullTest() {
		when(repository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> repository.save(null));
	}

	@Test
	void findByIdTest() {
		when(repository.findById(1L)).thenReturn(Optional.of(new CustomerData()));
		assertNotNull(repository.findById(1L));
	}

	@Test
	void findByIdWhenTheInputIsNotFoundTest() {
		when(repository.findById(2L)).thenReturn(Optional.empty());
		assertNull(repository.findById(2L).orElse(null));
	}

	@Test
	void findByIdWhenTheInputIsNullTest() {
		when(repository.findById(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> repository.findById(null));
	}

	@Test
	void findByExampleTest() {
		CustomerData object = new CustomerData();
		when(repository.findAll(Example.of(object))).thenReturn(List.of(object));
		List<CustomerData> objectList = (List<CustomerData>) repository.findAll(Example.of(object));
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void findByExampleWhenTheInputIsNullTest() {
		when(repository.findAll(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> repository.findAll(null));
	}

	@Test
	void findAllTest() {
		when(repository.findAll()).thenReturn(List.of(new CustomerData()));
		List<CustomerData> objectList = (List<CustomerData>) repository.findAll();
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void deleteByIdTest() {
		assertDoesNotThrow(() -> repository.deleteById(1L));
	}

	@Test
	void deleteByIdWhenTheInputIsNullTest() {
		doThrow(IllegalArgumentException.class).when(repository).deleteById(null);
		assertThrows(IllegalArgumentException.class, () -> repository.deleteById(null));
	}
}
