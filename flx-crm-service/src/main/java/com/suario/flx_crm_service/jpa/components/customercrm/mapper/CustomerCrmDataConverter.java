/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.mapper;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.jpa.components.customercrm.entity.CustomerCrmData;
import com.suario.flx_crm_service.jpa.helper.DataConverter;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCrmDataConverter implements DataConverter<CustomerCrm, CustomerCrmData> {

	private final ObjectMapper mapper;

	@Override
	public CustomerCrmData toData(CustomerCrm entity) {
		if (entity != null) {
			CustomerCrmData data = mapper.map(entity, CustomerCrmData.class);
			return data;
		}

		return null;
	}

	@Override
	public CustomerCrm toEntity(CustomerCrmData data) {
		if (data != null) {
			return mapper.mapBuilder(data, CustomerCrm.CustomerCrmBuilder.class).build();
		}

		return null;
	}
}
