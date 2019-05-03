package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpHEAD extends AbstractHttpRequest<HttpHEAD> {

	private HttpHEAD(URI uri) {
		super(uri, HttpMethod.HEAD);
	}

	public static HttpHEAD create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpHEAD(new URI(uri));
	}

	public static HttpHEAD create(URI uri) {
		checkNotNull(uri);
		return new HttpHEAD(uri);
	}

}