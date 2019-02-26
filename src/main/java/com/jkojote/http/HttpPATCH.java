package com.jkojote.http;

import java.net.URI;

public class HttpPATCH extends AbstractHttpRequestWithBody {

	private HttpPATCH(URI uri) {
		super(uri, HttpMethod.PATCH);
	}
}
