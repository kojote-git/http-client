package com.jkojote.http.utils;

import com.jkojote.http.HttpResponseReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringResponseReader implements HttpResponseReader<String> {
	public static final StringResponseReader INSTANCE = new StringResponseReader();

	@Override
	public String readResponse(InputStream responseInputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null)
			builder.append(line).append("\n");
		return builder.toString();
	}
}
