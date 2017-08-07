package at.hsol.xenapi.interfaces;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;

public interface Connection {
	public HttpClientContext getContext();

	public HttpClient getClient();

	public String renewCurrentUrl(HttpResponse response);

	public String getCurrentUrl();

	public String getIndexUrl();

	public ScheduledExecutorService getExecutor();

	public void close();
}
