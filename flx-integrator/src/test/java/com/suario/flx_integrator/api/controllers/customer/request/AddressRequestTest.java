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
public class AddressRequestTest {

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
		AddressRequest request = buildRequestObject(dateTime, date);

		assertAll(() -> assertNotNull(request), () -> assertNotNull(request.getStreet()),
				() -> assertEquals("Street", request.getStreet()), () -> assertNotNull(request.getCity()),
				() -> assertEquals("City", request.getCity()), () -> assertNotNull(request.getState()),
				() -> assertEquals("IL", request.getState()), () -> assertNotNull(request.getZipCode()),
				() -> assertEquals("17500", request.getZipCode()), () -> assertNotNull(request.toString()));
	}

	@Test
	void validationTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();

		assertAll(() -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setStreet(TestUtils.getRandomString(30));

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'street' must have between 1 and 30 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setStreet("");

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'street' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'street' must have between 1 and 30 characters")));
			assertTrue(constraintViolations.stream().anyMatch(item -> item.getMessage()
					.equals("Field 'street' must contain only alphanumeric characters and spaces")));
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setCity(TestUtils.getRandomString(20));

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'city' must have between 1 and 20 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setCity("");

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'city' cannot be empty")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'city' must have between 1 and 20 characters")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'city' must contain only characters and spaces")));
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setState(TestUtils.getRandomString(20));

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'state' must have 2 characters", constraintViolations.iterator().next().getMessage());
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setState("");

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'state' cannot be empty")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'state' must have 2 characters")));
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setZipCode(TestUtils.getRandomString(8));

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'zipCode' must have between 1 and 8 characters")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'zipCode' must contain only numbers")));
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			request.setZipCode("");

			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(3, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'zipCode' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'zipCode' must have between 1 and 8 characters")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'zipCode' must contain only numbers")));
		}, () -> {
			AddressRequest request = buildRequestObject(dateTime, date);
			Set<ConstraintViolation<AddressRequest>> constraintViolations = validator.validate(request);
			assertEquals(0, constraintViolations.size());
		});
	}

	public static AddressRequest buildRequestObject(LocalDateTime dateTime, LocalDate date) {
		AddressRequest request = new AddressRequest();

		request.setStreet("Street");
		request.setCity("City");
		request.setState("IL");
		request.setZipCode("17500");

		return request;
	}
}
