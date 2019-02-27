package com.jkojote.http;

import com.jkojote.http.executors.DefaultHttpRequestExecutor;
import com.jkojote.http.testserver.HttpMockServer;
import com.jkojote.http.testserver.HttpMockServerConfig;
import com.jkojote.http.utils.StringResponseReader;
import org.junit.After;
import org.junit.Before;
import com.jkojote.http.testserver.HttpMockResponse.HttpMockResponseBuilder;
import com.jkojote.http.testserver.HttpMockRequest.HttpMockRequestBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertEquals;

public class HttpGETTest {
	private HttpMockServer server;
	private HttpRequestExecutor executor = new DefaultHttpRequestExecutor();

	@Before
	public void setUpServer() {
		HttpMockServerConfig cfg = new HttpMockServerConfig();
		cfg.addMockResponse(
			HttpMockRequestBuilder.create()
				.withPath("/")
				.withMethod(HttpMethod.GET)
				.build(),
			HttpMockResponseBuilder.create()
				.setStatusCode(200)
				.setReasonPhrase("OK")
				.setResponseBody("Hi, User")
				.build()
		);
		server = new HttpMockServer(cfg);
		ForkJoinPool.commonPool().execute(server);
	}

	@After
	public void shutDownServer() throws IOException {
		server.stop();
		server.close();
	}

	@Test
	public void sendGetRequest_RespondOk() throws Exception {
		String path = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpGET.create(path + "/");
		HttpResponse response = executor.execute(request);
		String responseMessage = response.read(StringResponseReader.INSTANCE);
		assertEquals(200, response.getStatusCode());
		assertEquals("Hi, User", responseMessage);
	}

	@Test
	public void sendGetRequest_RespondNotFound() throws Exception {
		String path = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpGET.create(path + "/notfound");
		HttpResponse response = executor.execute(request);
		assertEquals(404, response.getStatusCode());
	}

	@Test
	public void sendAsyncGetRequest_RespondOk() throws Exception {
		String path = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpGET.create(path + "/");
		CompletableFuture<Integer> statusCode = new CompletableFuture<>();
		executor.executeAsync(request, response -> {
			statusCode.complete(response.getStatusCode());
		});
		assertEquals(Integer.valueOf(200), statusCode.get());
	}

	@Test
	public void sendAsyncGetRequest_RespondNotFound() throws Exception {
		String path = "http://localhost:" + server.getLocalPort();
		HttpRequest request = HttpGET.create(path + "/notfound");
		CompletableFuture<Integer> statusCode = new CompletableFuture<>();
		executor.executeAsync(request, response -> {
			statusCode.complete(response.getStatusCode());
		});
		assertEquals(Integer.valueOf(404), statusCode.get());
	}
}
