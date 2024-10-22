/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.usecase;

import com.suario.flx_crm_service.api.enums.ApplicationResponseEnum;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import com.suario.flx_crm_service.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DeleteCustomerCrmUseCase {

	private final CustomerCrmRepository repository;

	public void deleteById(Long id) {
		if (!repository.existsById(id))
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND);
		repository.deleteById(id);
	}
}
