package at.hsol.xenapi.interfaces;

import at.hsol.xenapi.events.ContentEvent;

/**
 * @author Felix Batusic
 *
 */
public interface ContentListener {
	void onNewConversationReply(ContentEvent e);
}
