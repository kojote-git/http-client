package com.jkojote.http;

import java.io.IOException;
import java.io.InputStream;

public interface HttpResponse {

	String getStatusLine();

	int getStatusCode();

	Iterable<HttpHeader> getHeaders();

	InputStream getInputStream() throws IOException;

	default <T> T read(HttpResponseReader<T> reader) throws IOException {
		try (InputStream in = getInputStream()) {
			return reader.readResponse(in);
		}
	}
}
