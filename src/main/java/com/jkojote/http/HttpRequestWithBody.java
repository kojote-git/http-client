package com.jkojote.http;

import java.io.InputStream;

public interface HttpRequestWithBody extends HttpRequest {

	InputStream getInputStream();
}
