package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpGET extends AbstractHttpRequest {
	private HttpGET(URI uri) {
		super(uri, HttpMethod.GET);
	}

	public static HttpGET create(String url) throws URISyntaxException {
		return new HttpGET(new URI(url));
	}

	public static HttpGET create(URI uri) {
		return new HttpGET(uri);
	}

	public HttpGET addHeader(String name, String value) {
		putHeader(name, value);
		return this;
	}

	public HttpGET addHeader(HttpHeader header) {
		putHeader(header);
		return this;
	}

	public HttpGET addHeader(HttpHeaderName name, HttpHeaderValue value) {
		putHeader(name, value);
		return this;
	}
}
