package com.jkojote.http;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public final class HttpHeader implements Serializable {
	private HttpHeaderName name;
	private HttpHeaderValue value;

	private HttpHeader(HttpHeaderName name, HttpHeaderValue value) {
		this.name = name;
		this.value = value;
	}

	public static HttpHeader of(String name, String value) {
		return new HttpHeader(HttpHeaderName.of(name), HttpHeaderValue.of(value));
	}

	public static HttpHeader of(HttpHeaderName name, HttpHeaderValue value) {
		checkNotNull(name);
		checkNotNull(value);
		return new HttpHeader(name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof HttpHeader) {
			HttpHeader that = (HttpHeader) obj;
			return name.equals(that.name) && value.equals(that.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * name.hashCode() + value.hashCode();
	}

	public HttpHeaderName getName() {
		return name;
	}

	public HttpHeaderValue getValue() {
		return value;
	}
}
