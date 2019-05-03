package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpDELETE extends AbstractHttpRequest<HttpDELETE> {

	private HttpDELETE(URI uri) {
		super(uri, HttpMethod.DELETE);
	}

	public static HttpDELETE create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpDELETE(new URI(uri));
	}

	public static HttpDELETE create(URI uri) {
		checkNotNull(uri);
		return new HttpDELETE(uri);
	}

}
