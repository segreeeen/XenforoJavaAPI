package at.hsol.xenapi.handlers;

import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.constants.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.PostSetBuilder;
import at.hsol.xenapi.util.Tools;

public class LoginHandler extends AbstractFunctionalityHandler {

	LoginHandler(Connection connection) {
		super(connection);
	}

	public String login(String username, String password) {

		HttpPost post = new HttpPost(getIndexUrl() + UrlConstants.LOGIN);
		post.setHeader("User-Agent", UrlConstants.USER_AGENT);

		try {
			Set<BasicNameValuePair> urlParameters = new PostSetBuilder("").addLogin(username).addRegister(null)
					.addPassword(password).addCookieCheck(null).addRedirect(null).addToken().addResponseType(null)
					.build();
			String html;
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			html = Tools.executeHttpRequest(this, post, true);
			return html;
		} catch (UnsupportedOperationException | ValueNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}
}
