package com.jkojote.http;

public interface HttpHeaderValueValidator {

	void checkValue(String headerValue);

	boolean validate(String headerValue);
}
