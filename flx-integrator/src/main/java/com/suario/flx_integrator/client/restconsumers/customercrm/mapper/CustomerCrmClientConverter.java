/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.customercrm.mapper;

import com.suario.flx_integrator.client.restconsumers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_integrator.client.restconsumers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_integrator.client.restconsumers.helper.ClientConverter;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCrmClientConverter
		implements
			ClientConverter<CustomerCrm, CustomerCrmRequest, CustomerCrmResponse> {

	private final ObjectMapper mapper;

	@Override
	public CustomerCrmResponse toResponse(CustomerCrm entity) {
		if (entity != null) {
			return mapper.map(entity, CustomerCrmResponse.class);
		}

		return null;
	}

	@Override
	public CustomerCrm toModel(CustomerCrmResponse data) {
		if (data != null) {
			return mapper.mapBuilder(data, CustomerCrm.CustomerCrmBuilder.class).build();
		}

		return null;
	}

	@Override
	public CustomerCrmRequest toRequest(CustomerCrm data) {
		if (data != null) {
			return mapper.mapBuilder(data, CustomerCrmRequest.CustomerCrmRequestBuilder.class).build();
		}

		return null;
	}
}
