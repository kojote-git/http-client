package com.jkojote.http.testserver;

import com.jkojote.http.HttpHeader;
import com.jkojote.http.HttpMethod;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HttpRequestHandler implements Runnable {
	private static final String CRLF = "\r\n";
	private static final HttpMockResponse MALFORMED_REQUEST;
	private static HttpMockResponse NOT_FOUND;
	private HttpMockServerConfig config;
	private Socket socket;

	static {
		MALFORMED_REQUEST = HttpMockResponse.HttpMockResponseBuilder.create()
				.setStatusCode(400)
				.setResponseBody("Malformed request")
				.setReasonPhrase("Bad Request")
				.build();
		NOT_FOUND = HttpMockResponse.HttpMockResponseBuilder.create()
				.setStatusCode(404)
				.setReasonPhrase("Not Found")
				.setResponseBody("Not Found")
				.build();
	}

	HttpRequestHandler(Socket socket, HttpMockServerConfig config) {
		this.socket = socket;
		this.config = config;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			handleRequest(in, out);
		} catch (IOException e) {

		}
	}

	private void handleRequest(BufferedReader in, BufferedWriter out) throws IOException {
		try {
			HttpMockRequest request = readRequest(in);
			HttpMockResponse response = config.getResponse(request);
			writeResponse(response, out);
		} catch (MalformedRequestException e) {
			writeResponse(MALFORMED_REQUEST, out);
		} catch (NotFoundException e) {
			writeResponse(NOT_FOUND, out);
		}
	}

	private HttpMockRequest readRequest(BufferedReader reader) throws IOException {
		HttpRequestLine requestLine = readRequestLine(reader.readLine());
		readHeaders(reader);
		String body = readBody(reader);
		return new HttpMockRequest(requestLine.method, requestLine.path, body);
	}

	private HttpRequestLine readRequestLine(String line) {
		Pattern methodPattern = Pattern.compile("GET|POST|PUT|DELETE|OPTIONS|HEAD|PATCH]");
		Pattern pathPattern = Pattern.compile("((/[a-zA-Z0-9%_]+)+/?)|/");
		Matcher methodMatcher = methodPattern.matcher(line);
		Matcher pathMatcher = pathPattern.matcher(line);
		if (!methodMatcher.find())
			throw new MalformedRequestException();
		if (!pathMatcher.find())
			throw new MalformedRequestException();
		String method = methodMatcher.group();
		String path = pathMatcher.group();
		return new HttpRequestLine(Enum.valueOf(HttpMethod.class, method), path);
	}

	private Collection<HttpHeader> readHeaders(BufferedReader reader) throws IOException {
		String line;
		Collection<HttpHeader> headers = new ArrayList<>();
		while (true) {
			line = reader.readLine();
			if (line == null || line.equals(CRLF))
				break;
			if (line.isEmpty())
				break;
			String[] header = line.split(":");
			String name = header[0];
			String value = header.length == 1 ? "" : header[1];
			try {
				headers.add(HttpHeader.of(name, value));
			} catch (IllegalArgumentException e) {
				throw new MalformedRequestException();
			}
		}
		return headers;
	}

	private String readBody(BufferedReader reader) throws IOException {
		StringBuilder body = new StringBuilder();
		char[] buff = new char[4096];
		int read;
		while (reader.ready() && (read = reader.read(buff)) > 0) {
			body.append(buff, 0, read);
		}
		return body.toString();
	}

	private void writeResponse(HttpMockResponse response, BufferedWriter out) throws IOException {
		if (response == null) {
			throw new NotFoundException();
		}
		writeStatusLine(response, out);
		writeHeaders(response, out);
		writeEmptyLine(out);
		out.write(response.getBody());
		out.flush();
	}

	private void writeStatusLine(HttpMockResponse response, BufferedWriter out) throws IOException {
		out.write("HTTP/1.1 ");
		out.write("" + response.getStatusCode());
		out.write(" ");
		out.write(response.getReasonPhrase());
		out.write(CRLF);
	}

	private void writeHeaders(HttpMockResponse response, BufferedWriter out) throws IOException {
		for (HttpHeader header : response.getHeaders()) {
			out.write(header.toString());
			out.write(CRLF);
		}
	}

	private void writeEmptyLine(BufferedWriter out) throws IOException {
		out.write(CRLF);
	}

	private class HttpRequestLine {
		private HttpMethod method;
		private String path;

		HttpRequestLine(HttpMethod method, String path) {
			this.method = method;
			this.path = path;
		}
	}
}
