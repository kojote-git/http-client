package com.jkojote.http.utils;

import com.jkojote.http.HttpResponseReader;

import java.io.*;

public class StringResponseReader implements HttpResponseReader<String> {
	public static final StringResponseReader INSTANCE = new StringResponseReader();

	@Override
	public String readResponse(InputStream responseInputStream) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int read;
		while ((read = responseInputStream.read(buffer)) > 0) {
			out.write(buffer, 0, read);
		}
		return out.toString();
	}
}
