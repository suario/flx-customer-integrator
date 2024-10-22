/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApplicationResponseEnum {
	APPLICATION_BAD_REQUEST(400, "Bad request", HttpStatus.BAD_REQUEST), APPLICATION_RESOURCE_NOT_FOUND(404,
			"Not found", HttpStatus.NOT_FOUND), APPLICATION_RETRIES_EXHAUSTED(1, "Web client retries exhausted",
					HttpStatus.INTERNAL_SERVER_ERROR), APPLICATION_SERVER_ERROR(500, "Internal Server Error",
							HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final String message;
	private final HttpStatus status;

	ApplicationResponseEnum(int code, String message, HttpStatus status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
