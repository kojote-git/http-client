package com.jkojote.http;

public enum HttpMethod {
	GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"),
	PATCH("PATCH"), HEAD("HEAD"), OPTIONS("OPTIONS");

	private String stringRepresentation;

	HttpMethod(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}
}
