/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerCrmUseCase {

	private final CustomerCrmRepository repository;

	public CustomerCrm save(CustomerCrm entity) {
		return repository.save(entity);
	}
}
