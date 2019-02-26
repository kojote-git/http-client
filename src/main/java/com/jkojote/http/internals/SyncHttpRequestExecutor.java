package com.jkojote.http.internals;

import com.jkojote.http.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyncHttpRequestExecutor implements HttpRequestExecutor {

	@Override
	public HttpResponse execute(HttpRequest request) {
		try {
			HttpURLConnection connection = openConnection(request);
			return execute(request, connection);
		} catch (IOException e) {
			throw new RequestExecutionException(e);
		}
	}

	private HttpURLConnection openConnection(HttpRequest request) throws IOException {
		return (HttpURLConnection) request.getUri().toURL().openConnection();
	}

	private HttpResponse execute(HttpRequest request, HttpURLConnection connection) throws IOException {
		addRequestHeaders(request, connection);
		connection.setRequestMethod(request.getMethod().toString());
		connection.setDoInput(true);
		if (request instanceof HttpRequestWithBody) {
			return executeRequestWithBody((HttpRequestWithBody) request, connection);
		}
		return readResponse(connection);
	}

	private HttpResponse executeRequestWithBody(HttpRequestWithBody request, HttpURLConnection connection)
	throws IOException {
		connection.setDoOutput(true);
		try (OutputStream out = connection.getOutputStream();
			InputStream in = request.getInputStream()) {
			transfer(in, out);
			return readResponse(connection);
		}
	}

	private void addRequestHeaders(HttpRequest request, HttpURLConnection connection) {
		for (HttpHeader header: request.getRequestHeaders()) {
			connection.addRequestProperty(header.getName().get(), header.getValue().get());
		}
	}

	private InputStream getInputStream(HttpURLConnection con) throws IOException {
		if (con.getResponseCode() >= 400)
			return con.getErrorStream();
		else
			return con.getInputStream();
	}

	private HttpResponse readResponse(HttpURLConnection connection) throws IOException {
		int statusCode = connection.getResponseCode();
		String statusLine = null;
		boolean statusLineIsParsed = false;
		byte[] response = readResponseAsBytes(connection);
		Collection<HttpHeader> headers = new ArrayList<>();
		for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
			String key = header.getKey();
			String value = String.join("; ", header.getValue());
			if (!statusLineIsParsed && isStatusLine(value)) {
				statusLineIsParsed = true;
				statusLine = value;
				continue;
			}
			headers.add(HttpHeader.of(key, value));
		}
		statusLine = statusLineIsParsed ? statusLine : "";
		return new InternalHttpResponse(
			new ByteArrayInputStream(response), statusCode,
			statusLine, headers
		);
	}

	private boolean isStatusLine(String line) {
		Pattern pattern = Pattern.compile("HTTP/[0-9]+\\.[0-9]+\\u0020[1-5][0-9]{2}\\u0020[a-z\\sA-Z]+");
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}

	private void transfer(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[4096];
		int read;
		while ((read = in.read(buffer)) > 0) {
			out.write(buffer, 0, read);
		}
		out.flush();
	}

	private byte[] readResponseAsBytes(HttpURLConnection con) throws IOException {
		try (InputStream in = getInputStream(con)) {
			return readAsBytes(in);
		}
	}

	private byte[] readAsBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		transfer(in, out);
		return out.toByteArray();
	}
}
