package com.jkojote.http.testserver;

import com.jkojote.http.HttpHeader;

import java.util.ArrayList;
import java.util.Collection;

public class HttpMockResponse {
	private Collection<HttpHeader> headers;
	private int statusCode;
	private String reasonPhrase;
	private String body;

	public HttpMockResponse(Collection<HttpHeader> headers, int statusCode, String reasonPhrase, String body) {
		this.headers = headers;
		this.statusCode = statusCode;
		this.body = body;
		this.reasonPhrase = reasonPhrase;
		headers.add(HttpHeader.of("Content-Length", body.getBytes().length + ""));
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public Collection<HttpHeader> getHeaders() {
		return headers;
	}

	public static final class HttpMockResponseBuilder {
		private Collection<HttpHeader> headers;
		private int statusCode;
		private String body;
		private String reasonPhrase;

		private HttpMockResponseBuilder() {
			headers = new ArrayList<>();
		}

		public static HttpMockResponseBuilder create() {
			return new HttpMockResponseBuilder();
		}

		public HttpMockResponseBuilder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public HttpMockResponseBuilder setResponseBody(String body) {
			this.body = body;
			return this;
		}

		public HttpMockResponseBuilder setHeader(String name, String value) {
			headers.add(HttpHeader.of(name, value));
			return this;
		}

		public HttpMockResponseBuilder setReasonPhrase(String reasonPhrase) {
			this.reasonPhrase = reasonPhrase;
			return this;
		}

		public HttpMockResponse build() {
			return new HttpMockResponse(headers, statusCode, reasonPhrase, body);
		}
	}
}
