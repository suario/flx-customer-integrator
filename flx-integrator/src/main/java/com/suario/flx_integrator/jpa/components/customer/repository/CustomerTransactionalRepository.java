/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.components.customer.repository;

import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customer.model.gateways.CustomerRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerTransactionalRepository extends CustomerRepository {

	@Transactional
	@Modifying
	Customer save(Customer entity);

	@Transactional(readOnly = true)
	Customer findById(Long id);

	@Transactional(readOnly = true)
	List<Customer> findByExample(Customer entity);

	@Transactional(readOnly = true)
	List<Customer> findAll();

	@Transactional
	@Modifying
	void deleteById(Long id);
}
