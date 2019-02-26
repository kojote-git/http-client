package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpDELETE extends AbstractHttpRequest {

	private HttpDELETE(URI uri) {
		super(uri, HttpMethod.DELETE);
	}

	public static HttpDELETE create(String uri) throws URISyntaxException {
		return new HttpDELETE(new URI(uri));
	}

	public static HttpDELETE create(URI uri) {
		return new HttpDELETE(uri);
	}

	public HttpDELETE addHeader(String name, String value) {
		putHeader(name, value);
		return this;
	}

	public HttpDELETE addHeader(HttpHeader header) {
		putHeader(header);
		return this;
	}

	public HttpDELETE addHeader(HttpHeaderName name, HttpHeaderValue value) {
		putHeader(name, value);
		return this;
	}
}
