package com.jkojote.http;

import java.io.InputStream;

public interface RequestBody {

	InputStream getInputStream();

	long getContentLength();
}
