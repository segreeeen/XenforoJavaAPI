package at.hsol.xenapi.handlers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;

import at.hsol.xenapi.interfaces.Connection;

public abstract class AbstractConnectionHandler {
	private Connection connection;
	protected String currentUrl;

	public AbstractConnectionHandler(Connection connection) {
		this.connection = connection;
		this.currentUrl = connection.getCurrenUrl();
	}

	protected String renewCurrentUrl(HttpResponse response) {
		this.currentUrl = connection.renewCurrentUrl(response);
		return this.currentUrl;
	}

	protected HttpClient getClient() {
		return connection.getClient();
	}

	protected HttpClientContext getContext() {
		return connection.getContext();
	}
}
