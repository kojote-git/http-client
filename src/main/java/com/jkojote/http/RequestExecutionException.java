package com.jkojote.http;

public class RequestExecutionException extends RuntimeException {
	private HttpRequest request;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public RequestExecutionException() {
	}

	public RequestExecutionException(String message) {
		super(message);
	}

	public RequestExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestExecutionException(Throwable cause) {
		super(cause);
	}

	public RequestExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
