package com.jkojote.http;

public class HttpHeader {
	private String key;
	private String value;

	private HttpHeader(String key, String value) {
		checkValue(value);
		this.key = key;
		this.value = value;
	}

	private void checkValue(String value) { }

	@Override
	public String toString() {
		return key + ": " + value;
	}
}
