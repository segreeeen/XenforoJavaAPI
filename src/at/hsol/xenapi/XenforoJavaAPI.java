package at.hsol.xenapi;

import at.hsol.xenapi.handlers.HandlerFactory;

public class XenforoJavaAPI {

	private final ConnectionImpl connection;

	public XenforoJavaAPI() {
		this.connection = new ConnectionImpl();
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
		return HandlerFactory.createLoginHandler(connection).login(url, username, password);
	}

	public String createConversation(String url, String title, String message, String recipients) {
		return HandlerFactory.createNewConversationHandler(connection).createConversation(url, title, message,
				recipients);
	}

	public String replyConversation(String url, String message) {
		return HandlerFactory.createReplyConversationHandler(connection).replyConversation(url, message);
	}

	/**
	 * @param url
	 *            Url of the thread to reply to
	 * @param message
	 *            Message that should be posted
	 * @return response of Xenforo
	 */
	public String addThreadReply(String url, String message) {
		return HandlerFactory.createReplyThreadHandler(connection).addThreadReply(url, message);
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
		return HandlerFactory.createNewThreadHandler(connection).addNewThread(url, title, message, tags);
	}

	/**
	 * @param url
	 *            the url of the forum index
	 * @param keywords
	 *            keywords to look after
	 * @param users
	 *            users who authored the post
	 * @param date
	 *            only look for posts newer than date
	 * @param childNodes
	 *            search child forums as well
	 * @param titleOnly
	 *            only look for keywords in the title
	 * @return
	 */
	public String searchEverything(String url, String keywords, String users, String date, String childNodes,
			String titleOnly) {
		return HandlerFactory.createSearchHandler(connection).searchEverything(url, keywords, users, date, childNodes,
				titleOnly);
	}

	public String addProfilePost(String url, String message) {
		return HandlerFactory.createProfilePostHandler(connection).addProfilePost(url, message);
	}

}
