/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.customercrm.response;

import lombok.Data;

@Data
public class CustomerCrmResponse {

	private Long id;

	private String fullName;

	private String contactEmail;

	private String primaryPhone;

	private String location;
}
