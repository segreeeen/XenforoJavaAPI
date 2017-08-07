package at.hsol.xenapi.handlers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import at.hsol.xenapi.constants.SelectConstants;
import at.hsol.xenapi.events.ConversationEvent;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.interfaces.ConversationListener;
import at.hsol.xenapi.util.Tools;

public class ConversationListenerHandler extends AbstractFunctionalityHandler {
	List<ConversationListener> listeners = new LinkedList<>();

	ConversationListenerHandler(Connection connection) {
		super(connection);
		connection.getExecutor().scheduleAtFixedRate(new ConversationChecker(), 1000, 10000, TimeUnit.MILLISECONDS);
	}

	public void addListener(ConversationListener e) {
		listeners.add(e);
	}

	private void fireChangeEvent(ConversationEvent e) {
		for (ConversationListener l : listeners) {
			l.newConversationReply(e);
		}
	}

	private class ConversationChecker implements Runnable {

		private int convCount = 0;

		@Override
		public void run() {
			HttpGet get = new HttpGet(getIndexUrl());
			String html = Tools.executeHttpRequest(connection, get, false);
			String parsed = parseUnreadConversations(html);
			if (convCount != Integer.parseInt(parsed)) {
				convCount = Integer.parseInt(parsed);
				ConversationEvent e = new ConversationEvent(convCount);
				fireChangeEvent(e);
			}
		}

		private String parseUnreadConversations(String html) {
			Document htmlDoc = Jsoup.parse(html);
			String parsed = htmlDoc.select(SelectConstants.ID_CONVERSATIONS_TOTAL).select("span.Total").text();
			return parsed;
		}

	}

}
