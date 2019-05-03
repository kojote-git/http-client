package com.jkojote.http;

import com.jkojote.http.bodies.BytesRequestBody;
import com.jkojote.http.bodies.StringRequestBody;

import java.net.URI;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public abstract class AbstractHttpRequestWithBody<T extends AbstractHttpRequestWithBody<T>>
		extends AbstractHttpRequest<T> implements HttpRequestWithBody {
	private RequestBody requestBody = RequestBody.EMPTY;

	protected AbstractHttpRequestWithBody(URI uri, HttpMethod method) {
		super(uri, method);
	}

	@Override
	public RequestBody getRequestBody() {
		return requestBody;
	}

	public T setRequestBody(RequestBody body) {
		checkNotNull(body);
		requestBody = body;
		addHeader("Content-Length", "" + requestBody.getContentLength());
		return (T) this;
	}

	public T setRequestBody(byte[] bytes) {
		setRequestBody(new BytesRequestBody(bytes));
		return (T) this;
	}

	public T setRequestBody(String string) {
		setRequestBody(new StringRequestBody(string));
		return (T) this;
	}
}
