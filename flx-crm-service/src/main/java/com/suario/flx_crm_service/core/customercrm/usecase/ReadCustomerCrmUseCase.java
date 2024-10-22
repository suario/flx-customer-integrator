/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadCustomerCrmUseCase {

	private final CustomerCrmRepository repository;

	public List<CustomerCrm> findAll() {
		return repository.findAll();
	}

	public List<CustomerCrm> findByExample(CustomerCrm entity) {
		return repository.findByExample(entity);
	}

	public CustomerCrm findById(Long id) {
		return repository.findById(id);
	}
}
