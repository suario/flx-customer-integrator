/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import com.suario.flx_crm_service.api.enums.ApplicationResponseEnum;
import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import com.suario.flx_crm_service.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCustomerCrmUseCase {

	private final CustomerCrmRepository repository;

	public CustomerCrm save(Long id, CustomerCrm customer) {
		if (!repository.existsById(id))
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND);
		customer.setId(id);
		return repository.save(customer);
	}
}
