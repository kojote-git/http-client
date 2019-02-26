package com.jkojote.http;

import com.jkojote.http.bodies.BytesRequestBody;
import com.jkojote.http.bodies.EmptyRequestBody;
import com.jkojote.http.bodies.StringRequestBody;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpPOST extends AbstractHttpRequest implements HttpRequestWithBody {
	private RequestBody requestBody = EmptyRequestBody.INSTANCE;

	private HttpPOST(URI uri) {
		super(uri, HttpMethod.POST);
	}

	public static HttpPOST create(String uri) throws URISyntaxException {
		return new HttpPOST(new URI(uri));
	}

	public static HttpPOST create(URI uri) {
		return new HttpPOST(uri);
	}

	@Override
	public InputStream getInputStream() {
		return requestBody.getInputStream();
	}

	public HttpPOST addHeader(String name, String value) {
		putHeader(name, value);
		return this;
	}

	public HttpPOST addHeader(HttpHeader header) {
		putHeader(header);
		return this;
	}

	public HttpPOST setRequestBody(byte[] bytes) {
		requestBody = new BytesRequestBody(bytes);
		addHeader("Content-Length", "" + requestBody.getContentLength());
		return this;
	}

	public HttpPOST setRequestBody(String string) {
		requestBody = new StringRequestBody(string);
		addHeader("Content-Length", "" + requestBody.getContentLength());
		return this;
	}
}
