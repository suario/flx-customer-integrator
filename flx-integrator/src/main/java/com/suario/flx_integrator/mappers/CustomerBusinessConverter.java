/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.mappers;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerBusinessConverter {

	CustomerBusinessConverter MAPPER = Mappers.getMapper(CustomerBusinessConverter.class);

	@Mapping(target = "contactEmail", source = "email")
	@Mapping(target = "primaryPhone", source = "phoneNumber")
	@Mapping(target = "fullName", ignore = true)
	@Mapping(target = "location", ignore = true)
	CustomerCrm toCRMCustomer(Customer model);

	@AfterMapping
	default void convertFields(@MappingTarget CustomerCrm customerCrm, Customer customer) {
		String fullName = Arrays.stream(new String[]{customer.getLastName(), customer.getFirstName()})
				.map(String::strip).collect(Collectors.joining(", "));

		Address address = customer.getAddress();
		String location = Arrays
				.stream(new String[]{address.getStreet(), address.getCity(), address.getState(), address.getZipCode()})
				.map(String::strip).collect(Collectors.joining(", "));

		customerCrm.setFullName(fullName);
		customerCrm.setLocation(location);
	}
}
