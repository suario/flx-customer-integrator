/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.exceptionhandler;

import com.suario.flx_crm_service.api.dtos.ErrorResponse;
import com.suario.flx_crm_service.api.enums.ApplicationResponseEnum;
import com.suario.flx_crm_service.exceptions.BusinessException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});

		ErrorResponse errorResponse = new ErrorResponse(webRequest.getDescription(false),
				ApplicationResponseEnum.APPLICATION_BAD_REQUEST.getCode(),
				ApplicationResponseEnum.APPLICATION_BAD_REQUEST.getMessage(), LocalDateTime.now(), validationErrors);

		return new ResponseEntity<>(errorResponse, ApplicationResponseEnum.APPLICATION_BAD_REQUEST.getStatus());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, WebRequest webRequest) {
		if (!ObjectUtils.isEmpty(ex.getCause())) {
			log.error("Exception: {}", ex.getCause().getLocalizedMessage());
		}

		ErrorResponse errorResponse = new ErrorResponse(webRequest.getDescription(false),
				ex.getResponseCode().getCode(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, ex.getResponseCode().getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorResponse errorResponse = new ErrorResponse(webRequest.getDescription(false),
				ApplicationResponseEnum.APPLICATION_SERVER_ERROR.getCode(), exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, ApplicationResponseEnum.APPLICATION_SERVER_ERROR.getStatus());
	}
}
