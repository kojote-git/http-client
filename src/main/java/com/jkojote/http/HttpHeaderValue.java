package com.jkojote.http;

import java.io.Serializable;

public final class HttpHeaderValue implements Serializable {
	private static final HttpHeaderValueValidator VALIDATOR = new DefaultHttpHeaderValueValidator();
	private String value;

	private HttpHeaderValue(String value) {
		this.value = value;
	}

	public static HttpHeaderValue of(String value) {
		VALIDATOR.checkValue(value);
		return new HttpHeaderValue(value);
	}

	public String get() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof HttpHeaderValue) {
			HttpHeaderValue that = (HttpHeaderValue) obj;
			return this.value.equals(that.value);
		}
		return false;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
