package com.jkojote.http;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpHeaderValueValidatorTest {
	private HttpHeaderValueValidator validator = new DefaultHttpHeaderValueValidator();

	@Test
	public void testValidHeaderValues() {
		assertTrue(validate(""));
		assertTrue(validate("\t"));
		assertTrue(validate(" "));
		assertTrue(validate("value"));
		assertTrue(validate("value1;value2"));
		assertTrue(validate("\tvalue1\tvalue2"));
		assertTrue(validate("(){}[]|;.,\"'!@#$%^&*_-=+?<>:"));
	}

	@Test
	public void testInvalidHeaderValues() {
		assertFalse(validate(null));
		assertFalse(validate("\n"));
		assertFalse(validate("\r"));
		assertFalse(validate("\u0000"));
		assertFalse(validate("\r\n"));
	}

	private boolean validate(String value) {
		return validator.validate(value);
	}
}
