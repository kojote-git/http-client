package com.jkojote.http;

import com.jkojote.http.bodies.BytesRequestBody;
import com.jkojote.http.bodies.StringRequestBody;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

public class HttpPOST implements HttpRequestWithBody {
	private RequestBody requestBody;
	private Map<HttpHeaderName, HttpHeader> headers;
	private String requestLine;
	private URI uri;

	private HttpPOST(URI uri) {
		this.uri = uri;
		this.headers = new TreeMap<>();
		this.requestLine = "POST /" + uri.getPath() + " HTTP/1.1";
	}

	public static HttpPOST create(String uri) throws URISyntaxException {
		return new HttpPOST(new URI(uri));
	}

	public static HttpPOST create(URI uri) {
		return new HttpPOST(uri);
	}

	@Override
	public InputStream getInputStream() {
		return requestBody.getInputStream();
	}

	@Override
	public String getRequestLine() {
		return requestLine;
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.POST;
	}

	@Override
	public URI getUri() {
		return uri;
	}

	@Override
	public Iterable<HttpHeader> getRequestHeaders() {
		return headers.values();
	}

	public HttpPOST addHeader(String name, String value) {
		HttpHeaderName headerName = HttpHeaderName.of(name);
		headers.put(headerName, HttpHeader.of(headerName, HttpHeaderValue.of(value)));
		return this;
	}

	public HttpPOST addHeader(HttpHeader header) {
		headers.put(header.getName(), header);
		return this;
	}

	public HttpPOST setRequestBody(byte[] bytes) {
		requestBody = new BytesRequestBody(bytes);
		return this;
	}

	public HttpPOST setRequestBody(String string) {
		requestBody = new StringRequestBody(string);
		return this;
	}

}
