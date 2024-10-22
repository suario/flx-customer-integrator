/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customercrm.model.gateways;

import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;

public interface CustomerCrmClient {
	CustomerCrm save(CustomerCrm entity);

	CustomerCrm update(Long id, CustomerCrm entity);

	void deleteById(Long id);
}
