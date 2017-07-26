package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnector con = new XenforoConnector();
		String result = con.login("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			System.out.println(result);
			con.addReply("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php?threads/testthread.1/",
					"<p>Hallo, API-Test</p>");
		}

	}
}
