/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.api.enums.ApplicationResponseEnum;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Slf4j
public class DeleteCustomerUseCase {

	private final CustomerRepository repository;

	private final CustomerCrmClient client;

	public void deleteById(Long id) {
		log.info("Deleting customer with id {}", id);
		Customer foundCustomer = repository.findById(id);

		if (ObjectUtils.isEmpty(foundCustomer)) {
			log.error("Customer with id {}, was not found", id);
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND);
		}

		client.deleteById(foundCustomer.getCustomerId());
		log.info("CRM response -> Deleted customer with id: {}", foundCustomer.getCustomerId());

		repository.deleteById(id);
	}
}
