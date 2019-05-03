package com.jkojote.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

class DefaultHttpHeaderValueValidator implements HttpHeaderValueValidator {
	private static final Pattern CONTROL_CHARACTERS_WITH_TAB_EXCLUDED = Pattern.compile(
		"[\\u0000-\\u0008]|[\\u000A-\\u001F]|\\u007F"
	);

	@Override
	public void checkValue(String headerValue) {
		checkNotNull(headerValue);
		Matcher matcher = CONTROL_CHARACTERS_WITH_TAB_EXCLUDED.matcher(headerValue);
		if (matcher.find()) {
			throw new IllegalArgumentException("header value contains control characters");
		}
	}

	@Override
	public boolean validate(String headerValue) {
		try {
			checkValue(headerValue);
			return true;
		} catch (NullPointerException | IllegalArgumentException e) {
			return false;
		}
	}
}
