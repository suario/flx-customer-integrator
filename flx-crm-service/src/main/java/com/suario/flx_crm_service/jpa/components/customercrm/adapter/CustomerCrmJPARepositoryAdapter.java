/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.adapter;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.jpa.components.customercrm.entity.CustomerCrmData;
import com.suario.flx_crm_service.jpa.components.customercrm.mapper.CustomerCrmDataConverter;
import com.suario.flx_crm_service.jpa.components.customercrm.repository.CustomerCrmJPARepository;
import com.suario.flx_crm_service.jpa.components.customercrm.repository.CustomerCrmTransactionalRepository;
import com.suario.flx_crm_service.jpa.helper.AdapterOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomerCrmJPARepositoryAdapter
		extends
			AdapterOperations<CustomerCrm, CustomerCrmData, Long, CustomerCrmJPARepository>
		implements
			CustomerCrmTransactionalRepository {

	public CustomerCrmJPARepositoryAdapter(CustomerCrmJPARepository repository, CustomerCrmDataConverter converter) {
		super(repository, converter, converter::toEntity);
	}
}
