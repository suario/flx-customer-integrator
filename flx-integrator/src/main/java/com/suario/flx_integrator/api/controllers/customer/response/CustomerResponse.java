/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "CustomerResponse", description = "Schema to hold Customer response")
@Data
public class CustomerResponse {

	@Schema(description = "Id of the Customer", example = "12345")
	private Long customerId;

	@Schema(description = "First Name of the Customer", example = "Phil")
	private String firstName;

	@Schema(description = "Last Name of the Customer", example = "Smith")
	private String lastName;

	@Schema(description = "Email of the Customer", example = "email@email.com")
	private String email;

	@Schema(description = "Phone Number of the Customer", example = "1234567890")
	private String phoneNumber;

	@Schema(description = "Address of the Customer")
	private AddressResponse address;
}
