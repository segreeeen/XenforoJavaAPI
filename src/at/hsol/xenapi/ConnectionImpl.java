package at.hsol.xenapi;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpCoreContext;

import at.hsol.xenapi.interfaces.Connection;

class ConnectionImpl implements Connection {
	private final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	private CloseableHttpClient client;
	private HttpClientContext context;
	private String currentUrl;
	private String indexUrl;
	private ExecutorService executor;

	ConnectionImpl(String indexUrl) {
		this.indexUrl = indexUrl;
		this.client = HttpClients.custom().setConnectionManager(cm).build();
		this.context = HttpClientContext.create();
		executor = Executors.newScheduledThreadPool(50);
	}

	@Override
	public String renewCurrentUrl(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			try {
				throw new IOException(response.getStatusLine().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);
		HttpHost currentHost = (HttpHost) context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
		this.currentUrl = (currentReq.getURI().isAbsolute()) ? currentReq.getURI().toString()
				: (currentHost.toURI() + currentReq.getURI());
		return this.currentUrl;
	}

	@Override
	public HttpClientContext getContext() {
		return this.context;
	}

	@Override
	public HttpClient getClient() {
		return this.client;
	}

	@Override
	public String getCurrentUrl() {
		return currentUrl;
	}

	@Override
	public String getIndexUrl() {
		return this.indexUrl;
	}

	@Override
	public ScheduledExecutorService getExecutor() {
		return (ScheduledExecutorService) executor;
	}

	@Override
	public void close() {
		try {
			client.close();
			executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
