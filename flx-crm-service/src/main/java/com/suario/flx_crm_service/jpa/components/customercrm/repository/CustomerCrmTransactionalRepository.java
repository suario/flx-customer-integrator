/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.components.customercrm.repository;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import com.suario.flx_crm_service.core.customercrm.model.gateways.CustomerCrmRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerCrmTransactionalRepository extends CustomerCrmRepository {

	@Transactional
	@Modifying
	CustomerCrm save(CustomerCrm entity);

	@Transactional(readOnly = true)
	CustomerCrm findById(Long id);

	@Transactional(readOnly = true)
	List<CustomerCrm> findByExample(CustomerCrm entity);

	@Transactional(readOnly = true)
	List<CustomerCrm> findAll();

	@Transactional
	@Modifying
	void deleteById(Long id);
}
