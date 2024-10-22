/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.mapper;

import com.suario.flx_integrator.api.commons.ServiceConverter;
import com.suario.flx_integrator.api.controllers.customer.request.CustomerRequest;
import com.suario.flx_integrator.api.controllers.customer.response.CustomerResponse;
import com.suario.flx_integrator.core.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AddressServiceConverter.class})
public interface CustomerServiceConverter extends ServiceConverter<Customer, CustomerRequest, CustomerResponse> {

	CustomerServiceConverter MAPPER = Mappers.getMapper(CustomerServiceConverter.class);

	@Override
	CustomerResponse toResponse(Customer model);

	@Mapping(target = "customerId", ignore = true)
	@Override
	Customer toModel(CustomerRequest request);
}
