package com.jkojote.http.testserver;

import com.jkojote.http.HttpMethod;

import java.util.Objects;

public class HttpMockRequest {
	private HttpMethod method;
	private String path;
	private String body;

	public HttpMockRequest(HttpMethod method, String path, String body) {
		this.method = method;
		this.path = path;
		this.body = body;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof HttpMockRequest) {
			HttpMockRequest req = (HttpMockRequest) obj;
			return Objects.equals(method, req.method) &&
					Objects.equals(path, req.path) &&
					Objects.equals(body, req.body);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * (31 * hashCodeNullSafe(method) + hashCodeNullSafe(path)) + hashCodeNullSafe(body);
	}

	private int hashCodeNullSafe(Object obj) {
		return obj == null ? 0 : obj.hashCode();
	}

	public static final class HttpMockRequestBuilder {
		private HttpMethod method;
		private String path;
		private String body;

		private HttpMockRequestBuilder() {
		}

		public static HttpMockRequestBuilder create() {
			return new HttpMockRequestBuilder();
		}

		public HttpMockRequestBuilder withMethod(HttpMethod method) {
			this.method = method;
			return this;
		}

		public HttpMockRequestBuilder withPath(String path) {
			this.path = path;
			return this;
		}

		public HttpMockRequestBuilder withBody(String body) {
			this.body = body;
			return this;
		}

		public HttpMockRequest build() {
			return new HttpMockRequest(method, path, body);
		}
	}
}
