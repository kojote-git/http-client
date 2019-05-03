package com.jkojote.http;

import java.net.URI;

import static com.jkojote.http.utils.Preconditions.checkNotNull;

public abstract class AbstractHttpRequestWithBody
		extends AbstractHttpRequest implements HttpRequestWithBody {
	private RequestBody requestBody = RequestBody.EMPTY;

	protected AbstractHttpRequestWithBody(URI uri, HttpMethod method) {
		super(uri, method);
	}

	@Override
	public RequestBody getRequestBody() {
		return requestBody;
	}

	protected void setInternalRequestBody(RequestBody requestBody) {
		checkNotNull(requestBody);
		this.requestBody = requestBody;
		putHeader("Content-Length", "" + requestBody.getContentLength());
	}
}
