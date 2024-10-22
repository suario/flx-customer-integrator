/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.utils;

import static com.suario.flx_integrator.constants.Constants.INDEX_CITY;
import static com.suario.flx_integrator.constants.Constants.INDEX_FIRST_NAME;
import static com.suario.flx_integrator.constants.Constants.INDEX_LAST_NAME;
import static com.suario.flx_integrator.constants.Constants.INDEX_STATE;
import static com.suario.flx_integrator.constants.Constants.INDEX_STREET;
import static com.suario.flx_integrator.constants.Constants.INDEX_ZIPCODE;

import com.suario.flx_integrator.core.customer.model.Address;
import com.suario.flx_integrator.core.customer.model.Customer;
import com.suario.flx_integrator.core.customercrm.model.CustomerCrm;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerUtils {

	public static CustomerCrm convertToCustomerCrm(Customer customer) {
		String fullName = Arrays.stream(new String[]{customer.getLastName(), customer.getFirstName()})
				.map(String::strip).collect(Collectors.joining(", "));

		Address address = customer.getAddress();
		String location = Arrays
				.stream(new String[]{address.getStreet(), address.getCity(), address.getState(), address.getZipCode()})
				.map(String::strip).collect(Collectors.joining(", "));

		return CustomerCrm.builder().id(customer.getCustomerId()).fullName(fullName).contactEmail(customer.getEmail())
				.primaryPhone(customer.getPhoneNumber()).location(location).build();
	}

	public static Customer convertToCustomer(CustomerCrm customerCrm) {
		List<String> splittedName = Arrays.stream(customerCrm.getFullName().split(",")).map(String::strip).toList();
		List<String> splittedAddress = Arrays.stream(customerCrm.getLocation().split(",")).map(String::strip).toList();

		Address address = Address.builder().street(splittedAddress.get(INDEX_STREET))
				.city(splittedAddress.get(INDEX_CITY)).state(splittedAddress.get(INDEX_STATE))
				.zipCode(splittedAddress.get(INDEX_ZIPCODE)).build();
		return Customer.builder().customerId(customerCrm.getId()).lastName(splittedName.get(INDEX_LAST_NAME))
				.firstName(splittedName.get(INDEX_FIRST_NAME)).email(customerCrm.getContactEmail())
				.phoneNumber(customerCrm.getPrimaryPhone()).address(address).build();
	}
}
