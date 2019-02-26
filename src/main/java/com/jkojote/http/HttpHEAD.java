package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpHEAD extends AbstractHttpRequest {

	private HttpHEAD(URI uri) {
		super(uri, HttpMethod.HEAD);
	}

	public static HttpHEAD create(String uri) throws URISyntaxException {
		return new HttpHEAD(new URI(uri));
	}

	public static HttpHEAD create(URI uri) {
		return new HttpHEAD(uri);
	}

	public HttpHEAD addHeader(HttpHeaderName name, HttpHeaderValue value) {
		putHeader(name, value);
		return this;
	}

	public HttpHEAD addHeader(String name, String value) {
		putHeader(name, value);
		return this;
	}

	public HttpHEAD addHeader(HttpHeader header) {
		putHeader(header);
		return this;
	}
}