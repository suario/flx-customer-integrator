/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.suario.flx_crm_service.api.controllers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_crm_service.api.controllers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_crm_service.api.dtos.ListWrapperResponse;
import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.usecase.CreateCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.DeleteCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.ReadCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.UpdateCustomerCrmUseCase;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerCrmRestTest {

	private CustomerCrmRest rest;

	@Mock
	private CreateCustomerCrmUseCase createCustomerCrmUseCase;

	@Mock
	private ReadCustomerCrmUseCase readCustomerCrmUseCase;

	@Mock
	private UpdateCustomerCrmUseCase updateCustomerCrmUseCase;

	@Mock
	private DeleteCustomerCrmUseCase deleteCustomerCrmUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		rest = new CustomerCrmRest(createCustomerCrmUseCase, readCustomerCrmUseCase, updateCustomerCrmUseCase,
				deleteCustomerCrmUseCase);
	}

	@Test
	void listTest() {
		List<CustomerCrm> objectList = List.of(new CustomerCrm());
		when(readCustomerCrmUseCase.findAll()).thenReturn(objectList);
		ListWrapperResponse<CustomerCrmResponse> result = rest.list();

		assertAll(() -> assertNotNull(result), () -> assertEquals(1, result.records().size()));
	}

	@Test
	void getOneTest() {
		Long id = 2L;
		CustomerCrm object = new CustomerCrm();
		when(readCustomerCrmUseCase.findById(id)).thenReturn(object);

		assertNotNull(rest.getOne(id));
	}

	@Test
	void getOneIsNullWhenTheIdIsNotFoundTest() {
		Long id = 2L;
		when(readCustomerCrmUseCase.findById(id)).thenReturn(null);

		assertNull(rest.getOne(id));
	}

	@Test
	void createTest() {
		CustomerCrmRequest request = new CustomerCrmRequest();
		CustomerCrm object = new CustomerCrm();
		when(createCustomerCrmUseCase.save(any())).thenReturn(object);

		assertNotNull(rest.create(request));
	}

	@Test
	void createIsNullWhenTheRequestIsNullTest() {
		when(createCustomerCrmUseCase.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			rest.create(null);
		});
	}

	@Test
	void updateTest() {
		CustomerCrmRequest request = new CustomerCrmRequest();
		CustomerCrm object = new CustomerCrm();
		assertDoesNotThrow(() -> {
			rest.update(2L, request);
		});
	}

	@Test
	void updateThrowsExceptionWhenRequestIsNullTest() {
		when(updateCustomerCrmUseCase.save(2L, null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			rest.update(2L, null);
		});
	}

	@Test
	void deleteTest() {
		assertDoesNotThrow(() -> rest.delete(1L));
	}
}
