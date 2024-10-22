/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UpdateCustomerUseCase {

	private final CustomerRepository repository;
	private final CustomerCrmClient client;

	public Customer save(Long id, Customer customer) {
		CustomerCrm customerCrm = CustomerUtils.convertToCustomerCrm(customer);
		client.update(id, customerCrm);
		customer.setCustomerId(id);
		return repository.save(customer);
	}
}
