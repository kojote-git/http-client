package com.jkojote.http.internals;

import com.jkojote.http.HttpHeader;
import com.jkojote.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

class InternalHttpResponse implements HttpResponse {
	private InputStream in;
	private int statusCode;
	private String statusLine;
	private Iterable<HttpHeader> headers;

	public InternalHttpResponse(InputStream in, int statusCode,
								String statusLine, Iterable<HttpHeader> headers) {
		this.in = in;
		this.statusCode = statusCode;
		this.statusLine = statusLine;
		this.headers = headers;
	}

	@Override
	public String getStatusLine() {
		return statusLine;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public Iterable<HttpHeader> getHeaders() {
		return headers;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return in;
	}
}
