package at.hsol.xenapi.interfaces;

import at.hsol.xenapi.events.ConversationEvent;

public interface ConversationListener {
	void newConversationReply(ConversationEvent e);
}
