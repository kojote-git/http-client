package com.jkojote.http.bodies;

import com.jkojote.http.RequestBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class EmptyRequestBody implements RequestBody {
	public static final EmptyRequestBody INSTANCE = new EmptyRequestBody();

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public long getContentLength() {
		return 0;
	}
}
