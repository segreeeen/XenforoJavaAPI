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

import at.hsol.xenapi.handlers.CreateConversationHandler;
import at.hsol.xenapi.handlers.CreateThreadHandler;
import at.hsol.xenapi.handlers.LoginHandler;
import at.hsol.xenapi.handlers.ReplyConversationHandler;
import at.hsol.xenapi.handlers.ReplyThreadHandler;
import at.hsol.xenapi.interfaces.Connection;

public class XenforoConnection implements Connection {

	private final HttpClient client;
	private final HttpClientContext context;

	private String currentUrl;

	public XenforoConnection() {
		this.client = HttpClients.createDefault();
		this.context = HttpClientContext.create();
	}

	/**
	 * This function should be called to login at the Xenforo Board. This has to be
	 * done once per session.
	 * 
	 * @param url
	 *            url of the index page of the Xenforo Board
	 * @param username
	 *            user to log in
	 * @param password
	 *            password of the user
	 * @return response of Xenforo
	 */
	public String login(String url, String username, String password) {
		return new LoginHandler(this).login(url, username, password);
	}

	public String createConversation(String url, String title, String message, String recipients) {
		return new CreateConversationHandler(this).createConversation(url, title, message, recipients);
	}

	public String replyConversation(String url, String message, String recipients) {
		return new ReplyConversationHandler(this).replyConversation(url, message, recipients);
	}

	/**
	 * @param url
	 *            Url of the thread to reply to
	 * @param message
	 *            Message that should be posted
	 * @return response of Xenforo
	 */
	public String addThreadReply(String url, String message) {
		return new ReplyThreadHandler(this).addThreadReply(url, message);
	}

	/**
	 * @param url
	 *            Url of the Forum the new Thread should be created in.
	 * @param title
	 *            Title of the Thread
	 * @param message
	 *            Initial Message of the Thread
	 * @param tags
	 *            Tags for the thread separated by comma
	 * @return response of Xenforo
	 */
	public String addNewThread(String url, String title, String message, String tags) {
		return new CreateThreadHandler(this).addNewThread(url, title, message, tags);
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
	public String getCurrenUrl() {
		return currentUrl;
	}
}
