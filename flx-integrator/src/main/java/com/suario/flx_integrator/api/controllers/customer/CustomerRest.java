/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer;

import com.suario.flx_integrator.api.controllers.customer.mapper.CustomerServiceConverter;
import com.suario.flx_integrator.api.controllers.customer.request.CustomerRequest;
import com.suario.flx_integrator.api.controllers.customer.response.CustomerResponse;
import com.suario.flx_integrator.api.dtos.ErrorResponse;
import com.suario.flx_integrator.api.dtos.ListWrapperResponse;
import com.suario.flx_integrator.api.enums.ApplicationResponseEnum;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.usecase.CreateCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.DeleteCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.ReadCustomerUseCase;
import com.suario.flx_integrator.core.customer.usecase.UpdateCustomerUseCase;
import com.suario.flx_integrator.enums.StatesEnum;
import com.suario.flx_integrator.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST APIs for Customer records", description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE Customer records")
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class CustomerRest {

	private final CreateCustomerUseCase createCustomerUseCase;
	private final ReadCustomerUseCase readCustomerUseCase;
	private final UpdateCustomerUseCase updateCustomerUseCase;
	private final DeleteCustomerUseCase deleteCustomerUseCase;

	private static final CustomerServiceConverter converter = CustomerServiceConverter.MAPPER;

	@Operation(summary = "Fetch Customer list REST API", description = "REST API to fetch Customer list")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ListWrapperResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public ListWrapperResponse<CustomerResponse> list() {
		log.info("Fetching Customer records.");
		List<CustomerResponse> responseList = converter.toResponseList(readCustomerUseCase.findAll());
		return new ListWrapperResponse<>(responseList);
	}

	@Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch Customer details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerResponse getOne(@PathVariable Long id) {
		log.info("Fetching one Customer record  with the id {}", id);
		Customer object = readCustomerUseCase.findById(id);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Create Customer REST API", description = "REST API to create new Customer")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerResponse create(@RequestBody @Valid CustomerRequest request) {
		validateRequest(request);

		log.info("Creating one Customer record");
		Customer object = converter.toModel(request);
		object = createCustomerUseCase.save(object);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Update Customer Details REST API", description = "REST API to update Customer details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerResponse update(@PathVariable Long id, @RequestBody @Valid CustomerRequest request) {
		validateRequest(request);

		log.info("Updating one record of the class Customer with id {}", id);
		Customer object = converter.toModel(request);
		object = updateCustomerUseCase.save(id, object);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Delete Customer Details REST API", description = "REST API to delete Customer details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		log.info("Deleting one Customer record with id {}", id);
		deleteCustomerUseCase.deleteById(id);
	}

	private static void validateRequest(CustomerRequest request) {
		if (!ObjectUtils.isEmpty(request) && !StatesEnum.isValid(request.getAddress().getState()))
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_BAD_REQUEST);
	}
}
