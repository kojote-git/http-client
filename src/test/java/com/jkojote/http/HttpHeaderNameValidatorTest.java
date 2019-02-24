package com.jkojote.http;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpHeaderNameValidatorTest {
	private HttpHeaderNameValidator validator = new DefaultHttpHeaderNameValidator();

	@Test
	public void testValidHeaderNames() {
		assertTrue(validate("h"));
		assertTrue(validate("header"));
		assertTrue(validate("content-type"));
		assertTrue(validate("Content-Type"));
	}

	@Test
	public void testInvalidHeaderNames() {
		assertFalse(validate(null));
		assertFalse(validate(""));

		// control characters
		assertFalse(validate("\n"));
		assertFalse(validate("\r"));
		assertFalse(validate("\u0000"));
		assertFalse(validate("\r\n"));
		assertFalse(validate("\r\nheader"));
		assertFalse(validate("\t"));

		// separators
		assertFalse(validate(";"));
		assertFalse(validate(" "));
		assertFalse(validate(":"));
		assertFalse(validate("("));
		assertFalse(validate(")"));
		assertFalse(validate("["));
		assertFalse(validate("]"));
		assertFalse(validate("{"));
		assertFalse(validate("}"));
		assertFalse(validate(","));
		assertFalse(validate("<"));
		assertFalse(validate(">"));
		assertFalse(validate("?"));
		assertFalse(validate("/"));

		assertFalse(validate(";header"));
		assertFalse(validate(" header"));
		assertFalse(validate(":header"));
		assertFalse(validate("(header"));
		assertFalse(validate(")header"));
		assertFalse(validate("[header"));
		assertFalse(validate("]header"));
		assertFalse(validate("{header"));
		assertFalse(validate("}header"));
		assertFalse(validate(",header"));
		assertFalse(validate("<header"));
		assertFalse(validate(">header"));
		assertFalse(validate("?header"));
		assertFalse(validate("/header"));
	}

	private boolean validate(String name) {
		return validator.validate(name);
	}

}
