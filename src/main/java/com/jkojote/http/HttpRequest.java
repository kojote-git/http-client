package com.jkojote.http;

import java.net.URI;

/**
 * An object that encapsulates necessary information for making http request
 */
public interface HttpRequest {

	String getRequestLine();

	HttpMethod getMethod();

	URI getUri();

	Iterable<HttpHeader> getRequestHeaders();

}
