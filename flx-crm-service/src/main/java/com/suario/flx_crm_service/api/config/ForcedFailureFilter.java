/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.config;

import com.suario.flx_crm_service.api.enums.ApplicationResponseEnum;
import com.suario.flx_crm_service.exceptions.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class ForcedFailureFilter extends OncePerRequestFilter {

	private final HandlerExceptionResolver resolver;

	private Integer failureRate;

	private ForcedFailureFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
			@Value("${failureRate}") Integer failureRate) {
		super();
		this.resolver = resolver;
		this.failureRate = failureRate;

		if (this.failureRate < 0) {
			this.failureRate = 0;
		} else if (this.failureRate > 100) {
			this.failureRate = 100;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Random random = new Random();

		int result = random.nextInt(1, 100);

		if (result <= this.failureRate) {
			resolver.resolveException(request, response, null,
					new BusinessException(ApplicationResponseEnum.APPLICATION_FORCED_FAILURE));
		}

		filterChain.doFilter(request, response);
	}
}
