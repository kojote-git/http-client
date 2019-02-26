package com.jkojote.http.bodies;

import com.jkojote.http.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;

public final class StringRequestBody implements RequestBody {
	private byte[] utf8bytes;

	public StringRequestBody(String str) {
		checkNotNull(str);
		this.utf8bytes = str.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(utf8bytes);
	}

	@Override
	public long getContentLength() {
		return utf8bytes.length;
	}
}
