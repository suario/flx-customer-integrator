/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "CustomerCrmResponse", description = "Schema to hold Customer response")
@Data
public class CustomerCrmResponse {

	@Schema(description = "id of the CRM Customer", example = "12345")
	private Long id;

	@Schema(description = "Full name of the CRM Customer", example = "Smith, Phil")
	private String fullName;

	@Schema(description = "Contact email of the CRM Customer", example = "email@email.com")
	private String contactEmail;

	@Schema(description = "Primary phone of the CRM Customer", example = "1234567890")
	private String primaryPhone;

	@Schema(description = "Location of the CRM Customer", example = "123 Main St, Springfield, IL, 62704")
	private String location;
}
