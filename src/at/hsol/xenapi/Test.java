package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnection con = new XenforoConnection();
		String result = con.login("http://d548d61c6281c174.demo-xenforo.com/1510/index.php", "admin", "admin");
		if (result != null) {
			System.out.println(result);
		}

	}
}
