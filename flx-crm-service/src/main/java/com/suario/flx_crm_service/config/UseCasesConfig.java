/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.suario.flx_crm_service.core", includeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")}, useDefaultFilters = false)
public class UseCasesConfig {
}
