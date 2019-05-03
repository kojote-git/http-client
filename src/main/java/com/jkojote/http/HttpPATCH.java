package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpPATCH extends AbstractHttpRequestWithBody<HttpPATCH> {

	private HttpPATCH(URI uri) {
		super(uri, HttpMethod.PATCH);
	}

	public static HttpPATCH create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpPATCH(new URI(uri));
	}

	public static HttpPATCH create(URI uri) {
		checkNotNull(uri);
		return new HttpPATCH(uri);
	}
}
