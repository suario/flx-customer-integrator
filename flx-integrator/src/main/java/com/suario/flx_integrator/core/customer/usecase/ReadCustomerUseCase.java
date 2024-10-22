/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.api.enums.ApplicationResponseEnum;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.exceptions.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
public class ReadCustomerUseCase {

	private final CustomerRepository repository;

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public List<Customer> findByExample(Customer entity) {
		return repository.findByExample(entity);
	}

	public Customer findById(Long id) {
		Customer customer = repository.findById(id);
		if (ObjectUtils.isEmpty(customer)) {
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND);
		}
		return customer;
	}
}
