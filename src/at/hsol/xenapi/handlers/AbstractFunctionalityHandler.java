package at.hsol.xenapi.handlers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;

import at.hsol.xenapi.interfaces.Connection;

public abstract class AbstractFunctionalityHandler implements Connection {
	private Connection connection;
	protected String currentUrl;

	public AbstractFunctionalityHandler(Connection connection) {
		this.connection = connection;
		this.currentUrl = connection.getCurrenUrl();
	}

	@Override
	public String renewCurrentUrl(HttpResponse response) {
		this.currentUrl = connection.renewCurrentUrl(response);
		return this.currentUrl;
	}

	@Override
	public HttpClient getClient() {
		return connection.getClient();
	}

	@Override
	public HttpClientContext getContext() {
		return connection.getContext();
	}

	@Override
	public String getCurrenUrl() {
		return connection.getCurrenUrl();
	}
}
