/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.exceptions;

import com.suario.flx_crm_service.api.enums.ApplicationResponseEnum;

public class BusinessException extends RuntimeException {

	private final ApplicationResponseEnum responseCode;

	public BusinessException(ApplicationResponseEnum responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}

	public BusinessException(ApplicationResponseEnum responseCode, Exception exception) {
		super(responseCode.getMessage(), exception);
		this.responseCode = responseCode;
	}

	public BusinessException(ApplicationResponseEnum responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}

	public ApplicationResponseEnum getResponseCode() {
		return responseCode;
	}
}
