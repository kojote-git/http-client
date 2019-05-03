package com.jkojote.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jkojote.http.utils.Preconditions.checkArgument;
import static com.jkojote.http.utils.Preconditions.checkNotNull;

class DefaultHttpHeaderNameValidator implements HttpHeaderNameValidator {
	private static final Pattern CONTROL_CHARACTERS = Pattern.compile("[\\u0000-\\u001F]|\\u007F");
	private static final Pattern SEPARATORS = Pattern.compile(
		"\\(|\\)|<|>|@|,|;|:|\\\\|\"|/|\\[|]|\\?|=|\\{|}|\\u0020"
	);

	@Override
	public void checkName(String name) {
		checkNotNull(name, "header name was null");
		checkArgument(name.length() != 0, "header name is empty");
		Matcher ctrlCharactersMatcher = CONTROL_CHARACTERS.matcher(name);
		Matcher separatorsMatcher = SEPARATORS.matcher(name);
		if (ctrlCharactersMatcher.find()) {
			throw new IllegalArgumentException("header name contains control characters");
		}
		if (separatorsMatcher.find()) {
			throw new IllegalArgumentException("header name contains separators");
		}
	}

	@Override
	public boolean validate(String name) {
		try {
			checkName(name);
		} catch (NullPointerException | IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
