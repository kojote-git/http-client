package com.jkojote.http;

public interface HttpHeaderNameValidator {
	void checkName(String name);

	boolean validate(String name);
}
