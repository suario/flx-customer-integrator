/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.customercrm.restclient;

import com.suario.flx_integrator.api.enums.ApplicationResponseEnum;
import com.suario.flx_integrator.client.restconsumers.customercrm.request.CustomerCrmRequest;
import com.suario.flx_integrator.client.restconsumers.customercrm.response.CustomerCrmResponse;
import com.suario.flx_integrator.client.restconsumers.helper.RestClient;
import com.suario.flx_integrator.exceptions.BusinessException;
import com.suario.flx_integrator.exceptions.ResourceNotFoundException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerCrmRestConsumer implements RestClient<CustomerCrmRequest, CustomerCrmResponse, Long> {
	private final WebClient client;

	// @CircuitBreaker(name = "cbCustomerCrmCreate" /*, fallbackMethod =
	// "testGetOk"*/)
	@Retry(name = "retryCustomerCrmCreate", fallbackMethod = "createFallback")
	@Override
	public CustomerCrmResponse create(CustomerCrmRequest request) {
		log.info("Calling external service for creating a new CustomerCrm record");
		return client.post().body(Mono.just(request), CustomerCrmRequest.class).retrieve()
				.bodyToMono(CustomerCrmResponse.class).block();
	}

	public CustomerCrmResponse createFallback(CustomerCrmRequest request, Exception exception) {
		log.info("Create calls to CustomerCrm external endpoint are exhausted");
		throw new BusinessException(ApplicationResponseEnum.APPLICATION_RETRIES_EXHAUSTED, exception);
	}

	// @CircuitBreaker(name = "cbCustomerCrmUpdate" /*, fallbackMethod =
	// "testGetOk"*/)
	@Retry(name = "retryCustomerCrmUpdate", fallbackMethod = "updateFallback")
	@Override
	public CustomerCrmResponse update(Long id, CustomerCrmRequest request) {
		log.info("Calling external service for updating a CustomerCrm record with id {}", id);
		return client.put().uri("{id}", id).body(Mono.just(request), CustomerCrmRequest.class).retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals,
						response -> response.bodyToMono(String.class).map(ResourceNotFoundException::new))
				.bodyToMono(CustomerCrmResponse.class).block();
	}

	public CustomerCrmResponse updateFallback(Long id, CustomerCrmRequest request, Exception exception) {
		log.info("Update calls to CustomerCrm external endpoint with id {} are exhausted", id);

		if (exception instanceof ResourceNotFoundException)
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND, exception);
		throw new BusinessException(ApplicationResponseEnum.APPLICATION_RETRIES_EXHAUSTED, exception);
	}

	// @CircuitBreaker(name = "cbCustomerCrmDelete" /*, fallbackMethod =
	// "testGetOk"*/)
	@Retry(name = "retryCustomerCrmDelete", fallbackMethod = "deleteFallback")
	@Override
	public void delete(Long id) {
		log.info("Calling external service for deleting a CustomerCrm record with id {}", id);
		client.delete().uri("{id}", id).retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals,
						response -> response.bodyToMono(String.class).map(ResourceNotFoundException::new))
				.bodyToMono(CustomerCrmResponse.class).block();
	}

	public void deleteFallback(Long id, Exception exception) {
		log.info("Delete calls to CustomerCrm external endpoint with id {} are exhausted", id);
		if (exception instanceof ResourceNotFoundException)
			throw new BusinessException(ApplicationResponseEnum.APPLICATION_RESOURCE_NOT_FOUND, exception);
		throw new BusinessException(ApplicationResponseEnum.APPLICATION_RETRIES_EXHAUSTED, exception);
	}
}
