package at.hsol.xenapi.interfaces;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;

public interface Connection {
	public HttpClientContext getContext();

	public HttpClient getClient();

	public String renewCurrentUrl(HttpResponse response);

	public String getCurrenUrl();
}
