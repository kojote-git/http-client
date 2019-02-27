package com.jkojote.http;

import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * An object capable of executing http requests
 */
public interface HttpRequestExecutor {

	/**
	 * Executes given request
	 * @param request a request to be executed
	 * @return a response on given request
	 * @throws RequestExecutionException if some error is encountered during an execution of a request
	 */
	HttpResponse execute(HttpRequest request);

	/**
	 * Executes given request asynchronously
	 * @param request a request to be executed
	 * @return a response on given request
	 * @throws RequestExecutionException if some error is encountered during an execution of a request
	 */
	Future<HttpResponse> executeAsync(HttpRequest request) throws RequestExecutionException;

	/**
	 * Executes given request asynchronously and calls {@code onCompletion} when a response is ready to be read
	 * @param request a request to be executed
	 * @param onCompletion a callback to be executed when a response is ready to be read
	 * @throws RequestExecutionException if some error is encountered during an execution of a request
	 */
	void executeAsync(HttpRequest request, Consumer<HttpResponse> onCompletion) throws RequestExecutionException;

}
