/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.repository;

import com.suario.flx_integrator.jpa.components.customer.entity.CustomerData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository
		extends
			CrudRepository<CustomerData, Long>,
			QueryByExampleExecutor<CustomerData> {
}
