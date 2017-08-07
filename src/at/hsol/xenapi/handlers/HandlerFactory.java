package at.hsol.xenapi.handlers;

import at.hsol.xenapi.interfaces.Connection;

public class HandlerFactory {
	public static SearchHandler createSearchHandler(Connection connection) {
		return new SearchHandler(connection);
	}

	public static ReplyThreadHandler createReplyThreadHandler(Connection connection) {
		return new ReplyThreadHandler(connection);
	}

	public static ReplyConversationHandler createReplyConversationHandler(Connection connection) {
		return new ReplyConversationHandler(connection);
	}

	public static ProfilePostHandler createProfilePostHandler(Connection connection) {
		return new ProfilePostHandler(connection);
	}

	public static NewThreadHandler createNewThreadHandler(Connection connection) {
		return new NewThreadHandler(connection);
	}

	public static NewConversationHandler createNewConversationHandler(Connection connection) {
		return new NewConversationHandler(connection);
	}

	public static LoginHandler createLoginHandler(Connection connection) {
		return new LoginHandler(connection);
	}

	public static LogoutHandler createLogoutHandler(Connection connection) {
		return new LogoutHandler(connection);
	}

	public static ConversationListenerHandler createConversationListenerHandler(Connection connection) {
		return new ConversationListenerHandler(connection);
	}
}
