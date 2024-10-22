/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponse {

	@Schema(description = "API path invoked by client")
	private final String apiPath;

	@Schema(description = "Error code representing the error happened")
	private final Integer errorCode;

	@Schema(description = "Error message representing the error happened")
	private final String errorMessage;

	@Schema(description = "Time representing when the error happened")
	private final LocalDateTime errorTime;

	@Schema(description = "List of associated errors")
	private Map<String, String> validationErrors;
}
