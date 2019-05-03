package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public class HttpGET extends AbstractHttpRequest<HttpGET> {

	private HttpGET(URI uri) {
		super(uri, HttpMethod.GET);
	}

	public static HttpGET create(String uri) throws URISyntaxException {
		checkNotNull(uri);
		return new HttpGET(new URI(uri));
	}

	public static HttpGET create(URI uri) {
		checkNotNull(uri);
		return new HttpGET(uri);
	}

}