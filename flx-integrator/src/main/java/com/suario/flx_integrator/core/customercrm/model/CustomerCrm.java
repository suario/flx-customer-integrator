/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customercrm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCrm {

	private Long id;

	private String fullName;

	private String contactEmail;

	private String primaryPhone;

	private String location;
}
