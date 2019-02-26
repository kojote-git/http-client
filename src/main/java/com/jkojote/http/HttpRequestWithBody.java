package com.jkojote.http;

public interface HttpRequestWithBody extends HttpRequest {

	RequestBody getRequestBody();
}
