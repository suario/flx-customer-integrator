/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.usecase;

import com.suario.flx_integrator.api.enums.ApplicationResponseEnum;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import com.suario.flx_integrator.exceptions.BusinessException;
import com.suario.flx_integrator.mappers.CustomerBusinessConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Slf4j
public class UpdateCustomerUseCase {

	private static final CustomerBusinessConverter converter = CustomerBusinessConverter.MAPPER;

	private final CustomerRepository repository;
	private final CustomerCrmClient client;

	public Customer save(Long id, Customer customer) {
		log.info("Updating customer information for id {}", id);
		CustomerCrm customerCrm = converter.toCRMCustomer(customer);
		Customer foundCustomer = repository.findById(id);

		if (ObjectUtils.isEmpty(foundCustomer)) {
			log.error("Customer with id {}, was not found", id);
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND);
		}

		customerCrm = client.update(foundCustomer.getCustomerId(), customerCrm);
		log.info("CRM response -> Updated customer: {}", customerCrm);

		customer.setCustomerId(foundCustomer.getCustomerId());
		customer.setId(foundCustomer.getId());
		customer.getAddress().setId(foundCustomer.getAddress().getId());
		return repository.save(customer);
	}
}
