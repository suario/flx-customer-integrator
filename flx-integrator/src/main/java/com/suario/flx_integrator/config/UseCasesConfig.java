/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.suario.flx_integrator.core", includeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")}, useDefaultFilters = false)
public class UseCasesConfig {
}
