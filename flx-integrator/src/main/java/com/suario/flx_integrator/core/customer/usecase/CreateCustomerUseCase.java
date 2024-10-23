/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.mappers.CustomerBusinessConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CreateCustomerUseCase {

	private static final CustomerBusinessConverter converter = CustomerBusinessConverter.MAPPER;

	private final CustomerRepository repository;
	private final CustomerCrmClient client;

	public Customer save(Customer customer) {
		log.info("Creating new customer");
		CustomerCrm customerCrm = converter.toCRMCustomer(customer);

		CustomerCrm newCustomer = client.save(customerCrm);
		log.info("CRM response -> Created customer: {}", newCustomer);

		customer.setCustomerId(newCustomer.getId());

		return repository.save(customer);
	}
}
