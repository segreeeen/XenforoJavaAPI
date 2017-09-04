package at.hsol.xenapi.handlers;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;

import at.hsol.xenapi.interfaces.Connection;

/**
 * @author Felix Batusic
 *
 */
abstract class AbstractFunctionalityHandler implements Connection {
	protected Connection connection;
	protected String currentUrl;

	AbstractFunctionalityHandler(Connection connection) {
		this.connection = connection;
		this.currentUrl = connection.getCurrentUrl();
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
	public String getCurrentUrl() {
		return connection.getCurrentUrl();
	}

	@Override
	public String getIndexUrl() {
		return connection.getIndexUrl();
	}

	@Override
	public ScheduledExecutorService getExecutor() {
		return connection.getExecutor();
	}

	@Override
	public void close() {
		connection.close();
	}
}
