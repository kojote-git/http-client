package com.jkojote.http;

import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractHttpRequest implements HttpRequest {
	private Map<HttpHeaderName, HttpHeader> headers;
	private URI uri;
	private HttpMethod method;
	private String requestLine;

	protected AbstractHttpRequest(URI uri, HttpMethod method) {
		this.method = method;
		this.uri = uri;
		this.requestLine = method.toString() + " /" + uri.getPath() + "HTTP/1.1";
		this.headers = new TreeMap<>();
	}

	@Override
	public String getRequestLine() {
		return requestLine;
	}

	@Override
	public HttpMethod getMethod() {
		return method;
	}

	@Override
	public URI getUri() {
		return uri;
	}

	@Override
	public Iterable<HttpHeader> getRequestHeaders() {
		return headers.values();
	}

	protected void putHeader(String name, String value) {
		HttpHeaderName headerName = HttpHeaderName.of(name);
		headers.put(headerName, HttpHeader.of(headerName, HttpHeaderValue.of(value)));
	}

	protected void putHeader(HttpHeader header) {
		headers.put(header.getName(), header);
	}

	protected void putHeader(HttpHeaderName name, HttpHeaderValue value) {
		headers.put(name, HttpHeader.of(name, value));
	}
}
