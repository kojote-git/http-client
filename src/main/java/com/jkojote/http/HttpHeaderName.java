package com.jkojote.http;

import java.io.Serializable;

public final class HttpHeaderName implements Serializable, Comparable<HttpHeaderName> {
	private static final HttpHeaderNameValidator VALIDATOR = new DefaultHttpHeaderNameValidator();
	private String value;

	private HttpHeaderName(String value) {
		this.value = value;
	}

	public static HttpHeaderName of(String name) {
		VALIDATOR.checkName(name);
		return new HttpHeaderName(name);
	}

	public String get() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof HttpHeaderName) {
			HttpHeaderName name = (HttpHeaderName) obj;
			return value.compareToIgnoreCase(name.value) == 0;
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

	@Override
	public int compareTo(HttpHeaderName o) {
		return this.value.compareToIgnoreCase(o.value);
	}
}
