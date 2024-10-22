/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.customercrm;

import com.suario.flx_integrator.client.restconsumers.customercrm.mapper.CustomerCrmClientConverter;
import com.suario.flx_integrator.client.restconsumers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_integrator.client.restconsumers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_integrator.client.restconsumers.customercrm.restclient.CustomerCrmRestConsumer;
import com.suario.flx_integrator.client.restconsumers.helper.ClientAdapter;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import com.suario.flx_integrator.core.customercrm.model.gateways.CustomerCrmClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerCrmRestClientAdapter
		extends
			ClientAdapter<CustomerCrm, CustomerCrmRequest, CustomerCrmResponse, Long, CustomerCrmRestConsumer>
		implements
			CustomerCrmClient {
	protected CustomerCrmRestClientAdapter(CustomerCrmRestConsumer repository, CustomerCrmClientConverter converter) {
		super(repository, converter, converter::toModel);
	}
}
