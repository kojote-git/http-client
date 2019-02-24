package com.jkojote.http;

import java.net.URI;

public interface HttpRequest {

	String getRequestLine();

	HttpMethod getMethod();

	URI getUri();

	Iterable<HttpHeader> getRequestHeaders();

}
