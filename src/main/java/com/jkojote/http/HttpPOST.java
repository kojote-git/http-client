package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpPOST extends AbstractHttpRequestWithBody<HttpPOST> {

	private HttpPOST(URI uri) {
		super(uri, HttpMethod.POST);
	}

	public static HttpPOST create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpPOST(new URI(uri));
	}

	public static HttpPOST create(URI uri) {
		checkNotNull(uri);
		return new HttpPOST(uri);
	}
}
