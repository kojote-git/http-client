package com.jkojote.http;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractHttpRequest<T extends AbstractHttpRequest<T>> implements HttpRequest {
	private Map<HttpHeaderName, HttpHeaderValue> headers;
	private URI uri;
	private HttpMethod method;
	private String requestLine;

	protected AbstractHttpRequest(URI uri, HttpMethod method) {
		this.method = method;
		this.uri = uri;
		this.requestLine = method.toString() + " /" + uri.getPath() + "HTTP/1.1";
		this.headers = new HashMap<>();
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
		return headers.entrySet().stream()
			.map(e -> HttpHeader.of(e.getKey(), e.getValue()))
			.collect(Collectors.toCollection(LinkedList::new));
	}

	public T addHeader(String name, String value) {
		HttpHeaderName headerName = HttpHeaderName.of(name);
		headers.put(headerName, HttpHeaderValue.of(value));
		return (T) this;
	}

	public T addHeader(HttpHeader header) {
		headers.put(header.getName(), header.getValue());
		return (T) this;
	}

	public T addHeader(HttpHeaderName name, HttpHeaderValue value) {
		headers.put(name, value);
		return (T) this;
	}
}
