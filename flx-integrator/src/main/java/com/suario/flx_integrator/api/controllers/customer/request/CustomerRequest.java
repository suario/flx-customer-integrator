/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Schema(name = "CustomerRequest", description = "Schema to hold Customer information")
@Data
@Validated
public class CustomerRequest {

	@Schema(description = "First Name of the Customer", example = "Phil")
	@NotBlank(message = "Field 'firstName' cannot be empty")
	@Size(min = 1, max = 45, message = "Field 'firstName' must have between 1 and 45 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Field 'firstName' must contain only characters and spaces")
	private String firstName;

	@Schema(description = "Last Name of the Customer", example = "Smith")
	@NotBlank(message = "Field 'lastName' cannot be empty")
	@Size(min = 1, max = 45, message = "Field 'lastName' must have between 1 and 45 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Field 'lastName' must contain only characters and spaces")
	private String lastName;

	@Schema(description = "Email of the Customer", example = "email@email.com")
	@NotBlank(message = "Field 'email' cannot be empty")
	@Size(min = 1, max = 120, message = "Field 'email' must have between 1 and 120 characters")
	@Email(message = "Field must have an email format")
	private String email;

	@Schema(description = "Phone Number of the Customer", example = "1234567890")
	@NotBlank(message = "Field 'phoneNumber' cannot be empty")
	@Size(min = 10, max = 10, message = "Field 'phoneNumber' must have 10 characters")
	@Pattern(regexp = "^\\d+$", message = "Field 'phoneNumber' must contain only numbers")
	private String phoneNumber;

	@Schema(description = "Address of the Customer")
	@Valid
	@NotNull(message = "Field 'address' cannot be null") private AddressRequest address;
}
