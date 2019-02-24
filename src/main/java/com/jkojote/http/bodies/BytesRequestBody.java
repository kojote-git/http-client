package com.jkojote.http.bodies;

import com.jkojote.http.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class BytesRequestBody implements RequestBody {
	private byte[] bytes;

	public BytesRequestBody(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(bytes);
	}
}
