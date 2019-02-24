package com.jkojote.http;

public interface HttpRequestExecutor {

	HttpResponse execute(HttpRequest request) throws RequestExecutionException;
}
