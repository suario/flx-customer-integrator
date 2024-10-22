/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.core.customer.model;

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
public class Customer {

	private Long customerId;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private Address address;
}
