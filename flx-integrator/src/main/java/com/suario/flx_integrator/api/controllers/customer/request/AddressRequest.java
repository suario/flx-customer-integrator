/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Schema(name = "AddressRequest", description = "Schema to hold Address information")
@Data
@Validated
public class AddressRequest {

	@Schema(description = "street of the Address", example = "123 Main St")
	@NotBlank(message = "Field 'street' cannot be empty")
	@Size(min = 1, max = 30, message = "Field 'street' must have between 1 and 30 characters")
	@Pattern(regexp = "^[0-9a-zA-Z ]+$", message = "Field 'street' must contain only alphanumeric characters and spaces")
	private String street;

	@Schema(description = "city of the Address", example = "Springfield")
	@NotBlank(message = "Field 'city' cannot be empty")
	@Size(min = 1, max = 20, message = "Field 'city' must have between 1 and 20 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Field 'city' must contain only characters and spaces")
	private String city;

	@Schema(description = "state of the Address", example = "IL")
	@NotBlank(message = "Field 'state' cannot be empty")
	@Size(min = 2, max = 2, message = "Field 'state' must have 2 characters")
	private String state;

	@Schema(description = "zipCode of the Address", example = "62704")
	@NotBlank(message = "Field 'zipCode' cannot be empty")
	@Size(min = 1, max = 8, message = "Field 'zipCode' must have between 1 and 8 characters")
	@Pattern(regexp = "^\\d+$", message = "Field 'zipCode' must contain only numbers")
	private String zipCode;
}
