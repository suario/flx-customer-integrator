/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.mapper;

import com.suario.flx_integrator.api.commons.ServiceConverter;
import com.suario.flx_integrator.api.controllers.customer.request.AddressRequest;
import com.suario.flx_integrator.api.controllers.customer.response.AddressResponse;
import com.suario.flx_integrator.core.customer.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressServiceConverter extends ServiceConverter<Address, AddressRequest, AddressResponse> {

	AddressServiceConverter MAPPER = Mappers.getMapper(AddressServiceConverter.class);

	@Override
	AddressResponse toResponse(Address model);

	@Mapping(target = "id", ignore = true)
	@Override
	Address toModel(AddressRequest request);
}
