package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnection con = new XenforoConnection();
		String result = con.login("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			con.replyConversation("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php?conversations/abcd.5/",
					"yeeeahgggggdddgg", "OldFecker, ");
		}

	}
}
