/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.repository;

import com.suario.flx_crm_service.jpa.components.customercrm.entity.CustomerCrmData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCrmJPARepository
		extends
			CrudRepository<CustomerCrmData, Long>,
			QueryByExampleExecutor<CustomerCrmData> {
}
