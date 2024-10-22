/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.model.gateways;

import com.suario.flx_integrator.core.customer.model.Customer;
import java.util.List;

public interface CustomerRepository {

	Customer save(Customer entity);

	Customer findById(Long id);

	List<Customer> findByExample(Customer entity);

	List<Customer> findAll();

	boolean existsById(Long id);

	void deleteById(Long id);
}
