package com.jkojote.http;

import com.jkojote.http.bodies.BytesRequestBody;
import com.jkojote.http.bodies.StringRequestBody;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpPUT extends AbstractHttpRequestWithBody{

	private HttpPUT(URI uri) {
		super(uri, HttpMethod.PUT);
	}

	public static HttpPUT create(String uri) throws URISyntaxException {
		return new HttpPUT(new URI(uri));
	}

	public static HttpPUT create(URI uri) {
		checkNotNull(uri);
		return new HttpPUT(uri);
	}

	public HttpPUT addHeader(String name, String value) {
		putHeader(name, value);
		return this;
	}

	public HttpPUT addHeader(HttpHeader header) {
		putHeader(header);
		return this;
	}

	public HttpPUT addHeader(HttpHeaderName name, HttpHeaderValue value) {
		putHeader(name, value);
		return this;
	}

	public HttpPUT setRequestBody(RequestBody body) {
		setInternalRequestBody(body);
		return this;
	}

	public HttpPUT setRequestBody(byte[] bytes) {
		setRequestBody(new BytesRequestBody(bytes));
		return this;
	}

	public HttpPUT setRequestBody(String string) {
		setRequestBody(new StringRequestBody(string));
		return this;
	}
}
