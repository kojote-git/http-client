package com.jkojote.http.testserver;

import java.util.HashMap;
import java.util.Map;

public class HttpMockServerConfig {
	private Map<HttpMockRequest, HttpMockResponse> mockResponses;

	public HttpMockServerConfig() {
		mockResponses = new HashMap<>();
	}

	public HttpMockServerConfig addMockResponse(HttpMockRequest request, HttpMockResponse response) {
		mockResponses.put(request, response);
		return this;
	}

	public HttpMockResponse getResponse(HttpMockRequest request) {
		return mockResponses.get(request);
	}
}
