/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.adapter;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.jpa.components.customer.entity.CustomerData;
import com.suario.flx_integrator.jpa.components.customer.mapper.CustomerDataConverter;
import com.suario.flx_integrator.jpa.components.customer.repository.CustomerJPARepository;
import com.suario.flx_integrator.jpa.components.customer.repository.CustomerTransactionalRepository;
import com.suario.flx_integrator.jpa.helper.AdapterOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomerJPARepositoryAdapter extends AdapterOperations<Customer, CustomerData, Long, CustomerJPARepository>
		implements
			CustomerTransactionalRepository {

	public CustomerJPARepositoryAdapter(CustomerJPARepository repository, CustomerDataConverter converter) {
		super(repository, converter, converter::toEntity);
	}
}
