package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpPUT extends AbstractHttpRequestWithBody<HttpPUT> {

	private HttpPUT(URI uri) {
		super(uri, HttpMethod.PUT);
	}

	public static HttpPUT create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpPUT(new URI(uri));
	}

	public static HttpPUT create(URI uri) {
		checkNotNull(uri);
		return new HttpPUT(uri);
	}
}
