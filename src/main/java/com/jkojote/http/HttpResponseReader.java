package com.jkojote.http;

import java.io.IOException;
import java.io.InputStream;

public interface HttpResponseReader<T> {

	T readResponse(InputStream responseInputStream) throws IOException;
}
