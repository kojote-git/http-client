package com.jkojote.http.bodies;

import com.jkojote.http.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class StringRequestBody implements RequestBody {
	private String string;

	public StringRequestBody(String str) {
		this.string = str;
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(string.getBytes());
	}
}
