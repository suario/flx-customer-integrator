/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.api.controllers.customer.request;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.suario.flx_integrator.utils.TestUtils;
import jakarta.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerRequestTest {

	private LocalValidatorFactoryBean validator;

	@BeforeAll
	public void setup() {
		validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		validator.afterPropertiesSet();
	}

	@Test
	void fieldsTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();
		CustomerRequest request = buildRequestObject(dateTime, date);

		assertAll(() -> assertNotNull(request), () -> assertNotNull(request.getFirstName()),
				() -> assertEquals("Phil", request.getFirstName()), () -> assertNotNull(request.getLastName()),
				() -> assertEquals("Smith", request.getLastName()), () -> assertNotNull(request.getEmail()),
				() -> assertEquals("email@email.com", request.getEmail()),
				() -> assertNotNull(request.getPhoneNumber()),
				() -> assertEquals("1010101010", request.getPhoneNumber()), () -> assertNotNull(request.getAddress()),
				() -> assertNotNull(request.toString()));
	}

	@Test
	void validationTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();

		assertAll(() -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setFirstName(TestUtils.getRandomString(45));

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'firstName' must have between 1 and 45 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setFirstName("");

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'firstName' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'firstName' must have between 1 and 45 characters")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'firstName' must contain only characters and spaces")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setLastName(TestUtils.getRandomString(45));

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'lastName' must have between 1 and 45 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setLastName("");

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'lastName' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'lastName' must have between 1 and 45 characters")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'lastName' must contain only characters and spaces")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setEmail(TestUtils.getRandomString(120));

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'email' must have between 1 and 120 characters")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field must have an email format")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setEmail("");

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'email' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'email' must have between 1 and 120 characters")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setPhoneNumber(TestUtils.getRandomString(10));

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'phoneNumber' must contain only numbers")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'phoneNumber' must have 10 characters")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setPhoneNumber("");

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'phoneNumber' cannot be empty")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'phoneNumber' must have 10 characters")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'phoneNumber' must contain only numbers")));
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			request.setAddress(null);

			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'address' cannot be null", constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerRequest request = buildRequestObject(dateTime, date);
			Set<ConstraintViolation<CustomerRequest>> constraintViolations = validator.validate(request);
			assertEquals(0, constraintViolations.size());
		});
	}

	public static CustomerRequest buildRequestObject(LocalDateTime dateTime, LocalDate date) {
		CustomerRequest request = new CustomerRequest();

		request.setFirstName("Phil");
		request.setLastName("Smith");
		request.setEmail("email@email.com");
		request.setPhoneNumber("1010101010");
		request.setAddress(AddressRequestTest.buildRequestObject(dateTime, date));

		return request;
	}
}
