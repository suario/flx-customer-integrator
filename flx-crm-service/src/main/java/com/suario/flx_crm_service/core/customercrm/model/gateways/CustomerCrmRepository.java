/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.core.customercrm.model.gateways;

import com.suario.flx_crm_service.core.customercrm.model.CustomerCrm;
import java.util.List;

public interface CustomerCrmRepository {

	CustomerCrm save(CustomerCrm entity);

	CustomerCrm findById(Long id);

	List<CustomerCrm> findByExample(CustomerCrm entity);

	List<CustomerCrm> findAll();

	boolean existsById(Long id);

	void deleteById(Long id);
}
