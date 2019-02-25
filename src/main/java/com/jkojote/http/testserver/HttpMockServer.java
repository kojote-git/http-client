package com.jkojote.http.testserver;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class HttpMockServer implements Runnable, Closeable {
	private ServerSocket socket;
	private ExecutorService executorService;
	private HttpMockServerConfig config;
	private boolean running;
	private boolean closed;

	public HttpMockServer(int port, HttpMockServerConfig config) {
		try {
			this.socket = new ServerSocket(port);
			this.executorService = ForkJoinPool.commonPool();
			this.config = config;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void stop() {
		running = false;
	}

	public int getLocalPort() {
		return socket.getLocalPort();
	}

	public SocketAddress getLocalAddress() {
		return socket.getLocalSocketAddress();
	}

	@Override
	public void close() throws IOException {
		socket.close();
		closed = true;
	}

	@Override
	public void run() {
		if (closed)
			return;
		running = true;
		while (running) {
			try {
				executorService.execute(new HttpRequestHandler(socket.accept(), config));
			} catch (Exception e) {

			}
		}
	}
}
