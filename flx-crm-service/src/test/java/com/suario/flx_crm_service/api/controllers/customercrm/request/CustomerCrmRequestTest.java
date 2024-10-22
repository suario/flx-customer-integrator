/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.api.controllers.customercrm.request;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.suario.flx_crm_service.utils.TestUtils;
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
public class CustomerCrmRequestTest {

	private LocalValidatorFactoryBean validator;

	@BeforeAll
	public void setup() {
		validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		validator.afterPropertiesSet();
	}

	@Test
	void fieldsTest() {
		CustomerCrmRequest request = buildRequestObject();

		assertAll(() -> assertNotNull(request), () -> assertNotNull(request.getFullName()),
				() -> assertEquals("Smith, Phil", request.getFullName()),
				() -> assertNotNull(request.getContactEmail()),
				() -> assertEquals("email@email.com", request.getContactEmail()),
				() -> assertNotNull(request.getPrimaryPhone()),
				() -> assertEquals("1234567890", request.getPrimaryPhone()), () -> assertNotNull(request.getLocation()),
				() -> assertEquals("123 Main St, Springfield, IL, 62704", request.getLocation()),
				() -> assertNotNull(request.toString()));
	}

	@Test
	void validationTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate date = LocalDate.now();

		assertAll(() -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setFullName(TestUtils.getRandomString(90));

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'fullName' must have between 1 and 90 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setFullName("");

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'fullName' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'fullName' must have between 1 and 90 characters")));
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setContactEmail(TestUtils.getRandomString(120));

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'contactEmail' must have between 1 and 120 characters")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field must have an email format")));
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setContactEmail("");

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'contactEmail' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'contactEmail' must have between 1 and 120 characters")));
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setPrimaryPhone(TestUtils.getRandomString(10));

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'primaryPhone' must have 10 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setPrimaryPhone("");

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'primaryPhone' cannot be empty")));
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'primaryPhone' must have 10 characters")));
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setLocation(TestUtils.getRandomString(78));

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(1, constraintViolations.size());
			assertEquals("Field 'location' must have between 1 and 78 characters",
					constraintViolations.iterator().next().getMessage());
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			request.setLocation("");

			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(2, constraintViolations.size());
			assertTrue(constraintViolations.stream()
					.anyMatch(item -> item.getMessage().equals("Field 'location' cannot be empty")));
			assertTrue(constraintViolations.stream().anyMatch(
					item -> item.getMessage().equals("Field 'location' must have between 1 and 78 characters")));
		}, () -> {
			CustomerCrmRequest request = buildRequestObject();
			Set<ConstraintViolation<CustomerCrmRequest>> constraintViolations = validator.validate(request);
			assertEquals(0, constraintViolations.size());
		});
	}

	public static CustomerCrmRequest buildRequestObject() {
		CustomerCrmRequest request = new CustomerCrmRequest();

		request.setFullName("Smith, Phil");
		request.setContactEmail("email@email.com");
		request.setPrimaryPhone("1234567890");
		request.setLocation("123 Main St, Springfield, IL, 62704");

		return request;
	}
}
