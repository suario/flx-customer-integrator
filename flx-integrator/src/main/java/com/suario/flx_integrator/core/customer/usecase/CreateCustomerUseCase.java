/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

	private final CustomerRepository repository;
	private final CustomerCrmClient client;

	public Customer save(Customer customer) {
		CustomerCrm customerCrm = CustomerUtils.convertToCustomerCrm(customer);
		client.save(customerCrm);
		return repository.save(customer);
	}
}
