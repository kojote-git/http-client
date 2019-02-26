package com.jkojote.http.bodies;

import com.jkojote.http.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkNotNull;

public final class BytesRequestBody implements RequestBody {
	private byte[] bytes;

	public BytesRequestBody(byte[] bytes) {
		checkNotNull(bytes);
		this.bytes = bytes;
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(bytes);
	}

	@Override
	public long getContentLength() {
		return bytes.length;
	}
}
