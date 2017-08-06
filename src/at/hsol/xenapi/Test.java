package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoJavaAPI con = new XenforoJavaAPI("http://227211cf5737881d.demo-xenforo.com/1510/index.php");
		String result = con.login("admin", "admin");
		if (result != null) {
			String html;
			// html = con.addProfilePost("admin.1", "ficken SIE sich!");

			// html = con.addNewThread("main-forum.2", "Testthread", "testmessage",
			// "bla,blub");

			// html = con.addThreadReply("testthread.4", "Hello!");
			// html = con.createConversation("you old bastard", "your mother sucks cocks in
			// hell!", "fufu");
			// html = con.replyConversation("you-old-bastard.1", "fuck YOU sir.");
			html = con.searchEverything("dadadada", null, null, null, null);
			System.out.println(html);
		}

	}
}
