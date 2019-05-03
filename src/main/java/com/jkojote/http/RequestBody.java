package com.jkojote.http;

import com.jkojote.http.bodies.EmptyRequestBody;

import java.io.InputStream;

public interface RequestBody {

	RequestBody EMPTY = new EmptyRequestBody();

	/**
	 * @return stream through which body can be read
	 */
	InputStream getInputStream();

	/**
	 * @return length of the body in bytes
	 */
	long getContentLength();
}
