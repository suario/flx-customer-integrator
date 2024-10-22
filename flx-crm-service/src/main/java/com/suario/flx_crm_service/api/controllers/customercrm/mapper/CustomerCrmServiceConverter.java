/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.mapper;

import com.suario.flx_crm_service.api.commons.ServiceConverter;
import com.suario.flx_crm_service.api.controllers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_crm_service.api.controllers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {})
public interface CustomerCrmServiceConverter
		extends
			ServiceConverter<CustomerCrm, CustomerCrmRequest, CustomerCrmResponse> {

	CustomerCrmServiceConverter MAPPER = Mappers.getMapper(CustomerCrmServiceConverter.class);

	@Override
	CustomerCrmResponse toResponse(CustomerCrm model);

	@Mapping(target = "id", ignore = true)
	@Override
	CustomerCrm toModel(CustomerCrmRequest request);
}
