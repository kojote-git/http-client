package com.jkojote.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

public class HttpGET implements HttpRequest {
	private URI uri;
	private Map<HttpHeaderName, HttpHeader> headers;
	private String requestLine;

	private HttpGET(URI uri) {
		this.uri = uri;
		this.headers = new TreeMap<>();
		this.requestLine = "GET /" + uri.getPath() + " HTTP/1.1";
	}

	public static HttpGET create(String url) throws URISyntaxException {
		return new HttpGET(new URI(url));
	}

	public static HttpGET create(URI uri) {
		return new HttpGET(uri);
	}

	@Override
	public String getRequestLine() {
		return requestLine;
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.GET;
	}

	@Override
	public URI getUri() {
		return uri;
	}

	@Override
	public Iterable<HttpHeader> getRequestHeaders() {
		return headers.values();
	}

	public HttpGET addHeader(String name, String value) {
		HttpHeaderName headerName = HttpHeaderName.of(name);
		headers.put(headerName, HttpHeader.of(headerName, HttpHeaderValue.of(value)));
		return this;
	}

	public HttpGET addHeader(HttpHeader header) {
		headers.put(header.getName(), header);
		return this;
	}
}
