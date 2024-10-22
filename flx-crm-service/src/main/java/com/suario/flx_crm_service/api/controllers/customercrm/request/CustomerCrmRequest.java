/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Schema(name = "CustomerCrmRequest", description = "Schema to hold Customer information")
@Data
@Validated
public class CustomerCrmRequest {

	@Schema(description = "Full name of the CRM Customer", example = "Smith, Phil")
	@NotBlank(message = "Field 'fullName' cannot be empty")
	@Size(min = 1, max = 90, message = "Field 'fullName' must have between 1 and 90 characters")
	private String fullName;

	@Schema(description = "Contact email of the CRM Customer", example = "email@email.com")
	@NotBlank(message = "Field 'contactEmail' cannot be empty")
	@Size(min = 1, max = 120, message = "Field 'contactEmail' must have between 1 and 120 characters")
	@Email(message = "Field must have an email format")
	private String contactEmail;

	@Schema(description = "Primary phone of the CRM Customer", example = "1234567890")
	@NotBlank(message = "Field 'primaryPhone' cannot be empty")
	@Size(min = 10, max = 10, message = "Field 'primaryPhone' must have 10 characters")
	private String primaryPhone;

	@Schema(description = "Location of the CRM Customer", example = "123 Main St, Springfield, IL, 62704")
	@NotBlank(message = "Field 'location' cannot be empty")
	@Size(min = 1, max = 78, message = "Field 'location' must have between 1 and 78 characters")
	private String location;
}
