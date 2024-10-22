/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "AddressResponse", description = "Schema to hold Address response")
@Data
public class AddressResponse {

	@Schema(description = "id of the Address", example = "12345")
	private Long id;

	@Schema(description = "Street of the Address", example = "123 Main St")
	private String street;

	@Schema(description = "City of the Address", example = "Springfield")
	private String city;

	@Schema(description = "State of the Address", example = "IL")
	private String state;

	@Schema(description = "Zip Code of the Address", example = "62704")
	private String zipCode;
}
