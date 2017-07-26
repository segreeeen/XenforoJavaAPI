package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnector con = new XenforoConnector();
		String result = con.login("http://85f7e9e12144d9f8.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			System.out.println(result);
			// con.addReply("http://www.hoer-talk.de/threads/forenspiel-licht-an-licht-aus.19072",
			// "<p>Licht aus, damit sich alle &auml;rgern! Muahahahah...</p>");
		}

	}
}
