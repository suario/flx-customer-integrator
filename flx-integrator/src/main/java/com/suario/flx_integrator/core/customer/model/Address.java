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
public class Address {

	private Long id;

	private String street;

	private String city;

	private String state;

	private String zipCode;

	@Override
	public String toString() {
		return "Address[" + id + "]";
	}
}
