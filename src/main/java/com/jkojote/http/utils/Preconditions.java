package com.jkojote.http.utils;

public class Preconditions {
	private Preconditions() { throw new AssertionError(); }

	public static void checkNotNull(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
	}

	public static void checkNotNull(Object obj, String message) {
		if (obj == null) {
			throw new NullPointerException(message);
		}
	}

	public static void checkArgument(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
}
