package at.hsol.xenapi;

import java.io.Closeable;
import java.io.IOException;

import at.hsol.xenapi.handlers.ConversationListenerHandler;
import at.hsol.xenapi.handlers.HandlerFactory;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.interfaces.ConversationListener;

public class XenforoJavaAPI implements Closeable {

	private final ConnectionImpl connection;
	private ConversationListenerHandler convHandler = null;
	private String username = null;
	private String password = null;
	private boolean loggedIn = false;

	/**
	 * @param indexUrl
	 *            Url to the index page of your Xenforo installation without
	 *            trailing '/'.
	 * @param username
	 *            Your Username
	 * @param password
	 *            Your Password
	 */
	public XenforoJavaAPI(String indexUrl, String username, String password) {
		this.connection = new ConnectionImpl(indexUrl);
		this.username = username;
		this.password = password;
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
	public String login(String username, String password) {
		this.username = username;
		this.password = password;
		if (loggedIn) {
			HandlerFactory.createLogoutHandler(connection).logout();
		}
		return HandlerFactory.createLoginHandler(connection).login(username, password);
	}

	/**
	 * This function should be called to login at the Xenforo Board. This has to be
	 * done once per session. Username and Password entered at object creation are
	 * used.
	 * 
	 * @param url
	 *            url of the index page of the Xenforo Board
	 * @return response of Xenforo
	 */
	public String login() {
		return login(username, password);
	}

	/**
	 * To log out manually
	 * 
	 * @return response
	 */
	public String logout() {
		loggedIn = false;
		return HandlerFactory.createLogoutHandler(connection).logout();
	}

	/**
	 * @param title
	 *            title of the conversation that shall be created
	 * @param message
	 *            message that shall be sent
	 * @param recipients
	 *            members of the forum that should receive the message
	 * @return
	 */
	public String createConversation(String title, String message, String recipients) {
		return HandlerFactory.createNewConversationHandler(connection).createConversation(title, message, recipients);
	}

	/**
	 * @param conversationName
	 *            name of the conversation.id - you can find out the conversation id
	 *            by opening the conversation in a browser.
	 * @param message
	 *            message to be sent
	 * @return
	 */
	public String replyConversation(String conversationName, String message) {
		return HandlerFactory.createReplyConversationHandler(connection).replyConversation(conversationName, message);
	}

	/**
	 * @param threadName
	 *            threadname.id. you can figure out the thread id by opening the
	 *            thread in a browser. it's the number after the dot.
	 * @param message
	 *            Message that should be posted
	 * @return response of Xenforo
	 */
	public String addThreadReply(String threadName, String message) {
		return HandlerFactory.createReplyThreadHandler(connection).addThreadReply(threadName, message);
	}

	/**
	 * @param forumName
	 *            Name of the forum to add the new thread in
	 * @param title
	 *            Title of the thread
	 * @param message
	 *            Initial Message of the Thread
	 * @param tags
	 *            Tags for the thread separated by comma
	 * @return response of Xenforo
	 */
	public String addNewThread(String forumName, String title, String message, String tags) {
		return HandlerFactory.createNewThreadHandler(connection).addNewThread(forumName, title, message, tags);
	}

	/**
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
	public String searchEverything(String keywords, String users, String date, String childNodes, String titleOnly) {
		return HandlerFactory.createSearchHandler(connection).searchEverything(keywords, users, date, childNodes,
				titleOnly);
	}

	/**
	 * @param username
	 *            username.id of the user. to find out the id visit the profilepage
	 *            of the user and watch the url
	 * @param message
	 *            message to post to users profilepage
	 * @return
	 */
	public String addProfilePost(String username, String message) {
		return HandlerFactory.createProfilePostHandler(connection).addProfilePost(username, message);
	}

	/**
	 * fires a ConversationEvent every time a new conversation or reply is detected.
	 * 
	 * @param l
	 */
	public void addConversationListener(ConversationListener l) {
		if (this.convHandler == null) {
			this.convHandler = HandlerFactory.createConversationListenerHandler(connection);
		}
		convHandler.addListener(l);
	}

	/**
	 * this is a method for retrieving the connection object. this is only for
	 * development stuff, don't use this if you just want to write a bot or
	 * something similar.
	 * 
	 * @return Connection object
	 */
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void close() throws IOException {
		this.connection.close();
	}

}
