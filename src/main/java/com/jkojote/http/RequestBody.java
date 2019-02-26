package com.jkojote.http;

import com.jkojote.http.bodies.EmptyRequestBody;

import java.io.InputStream;

public interface RequestBody {

	RequestBody EMPTY = new EmptyRequestBody();

	InputStream getInputStream();

	long getContentLength();
}
