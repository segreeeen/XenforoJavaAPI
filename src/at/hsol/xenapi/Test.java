package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoJavaAPI con = new XenforoJavaAPI();
		String result = con.login("http://d548d61c6281c174.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			String html;
			// html =
			// con.addProfilePost("http://d548d61c6281c174.demo-xenforo.com/1510/index.php?members/admin.1/",
			// "ficken SIE sich!");
			//
			// html =
			// con.addNewThread("http://d548d61c6281c174.demo-xenforo.com/1510/index.php?forums/main-forum.2/",
			// "Testthread", "testmessage", "bla,blub");
			//
			// html =
			// con.addThreadReply("http://d548d61c6281c174.demo-xenforo.com/1510/index.php?threads/testthread.5/",
			// "Hello!");
			// html =
			// con.createConversation("http://d548d61c6281c174.demo-xenforo.com/1510/index.php",
			// "you old bastard",
			// "your mother sucks cocks in hell!", "fufu");
			// html = con.replyConversation(
			// "http://d548d61c6281c174.demo-xenforo.com/1510/index.php?conversations/you-old-bastard.1/",
			// "fuck YOU sir.");
			html = con.searchEverything("http://d548d61c6281c174.demo-xenforo.com/1510/index.php", "dadadada", null,
					null, null, null);
			System.out.println(html);
		}

	}
}
