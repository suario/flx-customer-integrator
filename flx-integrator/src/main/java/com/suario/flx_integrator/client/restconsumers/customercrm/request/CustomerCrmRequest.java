/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.customercrm.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCrmRequest {

	private Long id;

	private String fullName;

	private String contactEmail;

	private String primaryPhone;

	private String location;
}
