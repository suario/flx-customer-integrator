/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.mapper;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.jpa.components.customer.entity.AddressData;
import com.suario.flx_integrator.jpa.components.customer.entity.CustomerData;
import com.suario.flx_integrator.jpa.helper.DataConverter;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDataConverter implements DataConverter<Customer, CustomerData> {

	private final ObjectMapper mapper;

	@Override
	public CustomerData toData(Customer entity) {
		if (entity != null) {
			CustomerData data = mapper.map(entity, CustomerData.class);
			data.setAddress(entity.getAddress() != null ? mapper.map(entity.getAddress(), AddressData.class) : null);
			return data;
		}

		return null;
	}

	@Override
	public Customer toEntity(CustomerData data) {
		if (data != null) {
			return mapper.mapBuilder(data, Customer.CustomerBuilder.class)
					.address(data.getAddress() != null ? mapper.map(data.getAddress(), Address.class) : null).build();
		}

		return null;
	}
}
