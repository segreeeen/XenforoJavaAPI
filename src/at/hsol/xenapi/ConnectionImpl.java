package at.hsol.xenapi;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpCoreContext;

import at.hsol.xenapi.interfaces.Connection;

class ConnectionImpl implements Connection {
	private final HttpClient client;
	private final HttpClientContext context;
	private String currentUrl;
	private String indexUrl;

	ConnectionImpl(String indexUrl) {
		this.indexUrl = indexUrl;
		this.client = HttpClients.createDefault();
		this.context = HttpClientContext.create();
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
}
