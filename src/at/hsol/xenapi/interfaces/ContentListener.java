package at.hsol.xenapi.interfaces;

import at.hsol.xenapi.events.ContentEvent;

public interface ContentListener {
	void onNewConversationReply(ContentEvent e);
}
