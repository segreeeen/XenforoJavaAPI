package at.hsol.xenapi;

import java.io.IOException;

import at.hsol.xenapi.events.ConversationEvent;
import at.hsol.xenapi.interfaces.ConversationListener;

public class Test {
	public static void main(String[] args) {
		XenforoJavaAPI con = new XenforoJavaAPI("http://www.hoer-talk.de", "SeGreeeen", "w3s3eeee");
		String result = con.login();
		if (result != null) {
			System.out.println(result);
			con.addConversationListener(new ConversationListener() {

				@Override
				public void newConversationReply(ConversationEvent e) {
					System.out.println(e.total);
				}

			});
			try {
				Thread.sleep(30000);
				con.close();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// html = con.addProfilePost("admin.1", "ficken SIE sich!");

			// html = con.addNewThread("main-forum.2", "Testthread", "testmessage",
			// "bla,blub");

			// html = con.addThreadReply("testthread.4", "Hello!");
			// html = con.createConversation("you old bastard", "your mother sucks cocks in
			// hell!", "fufu");
			// html = con.replyConversation("you-old-bastard.1", "fuck YOU sir.");
			// html = con.searchEverything("dadadada", null, null, null, null);
			// System.out.println(html);
		}

	}
}
