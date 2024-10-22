/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCustomerUseCase {

	private final CustomerRepository repository;

	private final CustomerCrmClient client;

	public void deleteById(Long id) {
		client.deleteById(id);
		repository.deleteById(id);
	}
}
