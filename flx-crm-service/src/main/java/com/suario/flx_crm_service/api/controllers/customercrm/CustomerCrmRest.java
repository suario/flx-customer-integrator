/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm;

import com.suario.flx_crm_service.api.controllers.customercrm.mapper.CustomerCrmServiceConverter;
import com.suario.flx_crm_service.api.controllers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_crm_service.api.controllers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_crm_service.api.dtos.ErrorResponse;
import com.suario.flx_crm_service.api.dtos.ListWrapperResponse;
import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.usecase.CreateCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.DeleteCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.ReadCustomerCrmUseCase;
import com.suario.flx_crm_service.core.customercrm.usecase.UpdateCustomerCrmUseCase;
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

@Tag(name = "CRUD REST APIs for CustomerCrm records", description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE CustomerCrm records")
@RestController
@RequestMapping(value = "/customercrm", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class CustomerCrmRest {

	private final CreateCustomerCrmUseCase createCustomerCrmUseCase;
	private final ReadCustomerCrmUseCase readCustomerCrmUseCase;
	private final UpdateCustomerCrmUseCase updateCustomerCrmUseCase;
	private final DeleteCustomerCrmUseCase deleteCustomerCrmUseCase;

	private static final CustomerCrmServiceConverter converter = CustomerCrmServiceConverter.MAPPER;

	@Operation(summary = "Fetch CustomerCrm list REST API", description = "REST API to fetch CustomerCrm list")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = ListWrapperResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public ListWrapperResponse<CustomerCrmResponse> list() {
		log.info("Fetching CustomerCrm records.");
		List<CustomerCrmResponse> responseList = converter.toResponseList(readCustomerCrmUseCase.findAll());
		return new ListWrapperResponse<>(responseList);
	}

	@Operation(summary = "Fetch CustomerCrm Details REST API", description = "REST API to fetch CustomerCrm details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerCrmResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerCrmResponse getOne(@PathVariable Long id) {
		log.info("Fetching one CustomerCrm record  with the id {}", id);
		CustomerCrm object = readCustomerCrmUseCase.findById(id);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Create CustomerCrm REST API", description = "REST API to create new CustomerCrm")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = CustomerCrmResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@PostMapping(path = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerCrmResponse create(@RequestBody @Valid CustomerCrmRequest request) {
		validateRequest(request);

		log.info("Creating one CustomerCrm record");
		CustomerCrm object = converter.toModel(request);
		object = createCustomerCrmUseCase.save(object);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Update CustomerCrm Details REST API", description = "REST API to update CustomerCrm details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerCrmResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerCrmResponse update(@PathVariable Long id, @RequestBody @Valid CustomerCrmRequest request) {
		validateRequest(request);

		log.info("Updating one record of the class CustomerCrm with id {}", id);
		CustomerCrm object = converter.toModel(request);
		object = updateCustomerCrmUseCase.save(id, object);
		return object != null ? converter.toResponse(object) : null;
	}

	@Operation(summary = "Delete CustomerCrm Details REST API", description = "REST API to delete CustomerCrm details based on an ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerCrmResponse.class))),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		log.info("Deleting one CustomerCrm record with id {}", id);
		deleteCustomerCrmUseCase.deleteById(id);
	}

	private static void validateRequest(CustomerCrmRequest request) {
	}
}
