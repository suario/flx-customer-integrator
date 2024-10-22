/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.adapter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.jpa.components.customercrm.entity.CustomerCrmData;
import com.suario.flx_crm_service.jpa.components.customercrm.mapper.CustomerCrmDataConverter;
import com.suario.flx_crm_service.jpa.components.customercrm.repository.CustomerCrmJPARepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

public class CustomerCrmJPARepositoryAdapterTest {

	private CustomerCrmJPARepositoryAdapter repositoryAdapter;

	@Mock
	private CustomerCrmJPARepository repository;

	@Mock
	private CustomerCrmDataConverter converter;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		repositoryAdapter = new CustomerCrmJPARepositoryAdapter(repository, converter);
	}

	@Test
	void saveTest() {
		CustomerCrmData object = new CustomerCrmData();
		CustomerCrm entity = CustomerCrm.builder().build();
		when(repository.save(object)).thenReturn(object);
		when(converter.toData(entity)).thenReturn(object);
		when(converter.toEntity(object)).thenReturn(entity);
		assertNotNull(repositoryAdapter.save(entity));
	}

	@Test
	void saveWhenTheInputIsNullTest() {
		when(repository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> repositoryAdapter.save(null));
	}

	@Test
	void findByIdTest() {
		CustomerCrmData object = new CustomerCrmData();
		CustomerCrm entity = CustomerCrm.builder().build();
		when(repository.findById(1L)).thenReturn(Optional.of(object));
		when(converter.toData(entity)).thenReturn(object);
		when(converter.toEntity(object)).thenReturn(entity);
		assertNotNull(repositoryAdapter.findById(1L));
	}

	@Test
	void findByIdWhenTheInputIsNotFoundTest() {
		when(repository.findById(2L)).thenReturn(Optional.empty());
		assertNull(repositoryAdapter.findById(2L));
	}

	@Test
	void findByIdWhenTheInputIsNullTest() {
		when(repository.findById(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> repositoryAdapter.findById(null));
	}

	@Test
	void findByExampleTest() {
		CustomerCrmData object = new CustomerCrmData();
		CustomerCrm entity = CustomerCrm.builder().build();
		when(repository.findAll(Example.of(object))).thenReturn(List.of(object));
		when(converter.toData(entity)).thenReturn(object);
		when(converter.toEntity(object)).thenReturn(entity);
		List<CustomerCrm> objectList = repositoryAdapter.findByExample(entity);
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void findByExampleWhenTheInputIsNullTest() {
		assertThrows(IllegalArgumentException.class, () -> repositoryAdapter.findByExample(null));
	}

	@Test
	void findAllTest() {
		when(repository.findAll()).thenReturn(List.of(new CustomerCrmData()));
		List<CustomerCrm> objectList = repositoryAdapter.findAll();
		assertAll(() -> assertNotNull(objectList), () -> assertEquals(1, objectList.size()));
	}

	@Test
	void deleteByIdTest() {
		assertDoesNotThrow(() -> repositoryAdapter.deleteById(1L));
	}

	@Test
	void deleteByIdWhenTheInputIsNullTest() {
		doThrow(IllegalArgumentException.class).when(repository).deleteById(null);
		assertThrows(IllegalArgumentException.class, () -> repositoryAdapter.deleteById(null));
	}
}
