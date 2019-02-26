package com.jkojote.http;

import com.jkojote.http.internals.SyncHttpRequestExecutor;
import com.jkojote.http.testserver.HttpMockServer;
import com.jkojote.http.testserver.HttpMockServerConfig;
import com.jkojote.http.utils.StringResponseReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static com.jkojote.http.testserver.HttpMockRequest.HttpMockRequestBuilder;
import static com.jkojote.http.testserver.HttpMockResponse.HttpMockResponseBuilder;
import static org.junit.Assert.assertEquals;

public class HttpPOSTTest {
	private HttpMockServer server;
	private HttpRequestExecutor executor = new SyncHttpRequestExecutor();

	@Before
	public void setUpServer() {
		HttpMockServerConfig config = new HttpMockServerConfig();
		config.addMockResponse(
			HttpMockRequestBuilder.create()
				.withMethod(HttpMethod.POST)
				.withPath("/")
				.withBody("Hello")
				.build(),
			HttpMockResponseBuilder.create()
				.setStatusCode(200)
				.setReasonPhrase("OK")
				.setResponseBody("Hello")
				.build()
		);
		server = new HttpMockServer(config);
		ForkJoinPool.commonPool().execute(server);
	}

	@After
	public void shutDownServer() throws Exception {
		server.stop();
		server.close();
	}

	@Test
	public void sendPostRequest_RespondOk() throws Exception {
		String uri = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpPOST.create(uri + "/")
				.setRequestBody("Hello");
		HttpResponse response = executor.execute(request);
		String responseMessage = response.read(StringResponseReader.INSTANCE);

		assertEquals(200, response.getStatusCode());
		assertEquals("Hello", responseMessage);
	}

	@Test
	public void sendEmptyPostRequest_RespondNotFound() throws Exception {
		String uri = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpPOST.create(uri + "/");
		HttpResponse response = executor.execute(request);

		assertEquals(404, response.getStatusCode());
	}
}
