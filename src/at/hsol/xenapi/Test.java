package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnection con = new XenforoConnection();
		String result = con.login("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			con.addNewThread("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php?forums/main-forum.2/", "abcd",
					"yeeeahgggggggggggggg", "<p>maybe!</p>");
		}

	}
}
