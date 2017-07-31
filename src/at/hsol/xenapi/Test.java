package at.hsol.xenapi;

public class Test {
	public static void main(String[] args) {
		XenforoConnection con = new XenforoConnection();
		String result = con.login("", "", "");
		if (result != null) {
			System.out.println(result);
		}

	}
}
