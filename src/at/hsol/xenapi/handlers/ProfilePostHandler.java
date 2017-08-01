package at.hsol.xenapi.handlers;

import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.constants.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.PostSetBuilder;
import at.hsol.xenapi.util.Tools;

public class ProfilePostHandler extends AbstractFunctionalityHandler {

	ProfilePostHandler(Connection connection) {
		super(connection);
	}

	public String addProfilePost(String url, String message) {
		HttpGet get = new HttpGet(url);
		String html = Tools.executeHttpRequest(this, get, false);
		try {
			Set<BasicNameValuePair> values = new PostSetBuilder(html).addMessage(message).addToken().addNoRedirect(null)
					.addResponseType(null).build();
			HttpPost post = new HttpPost(url + UrlConstants.PROFILE_POST_ACTION);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);
			post.setEntity(new UrlEncodedFormEntity(values));
			html = Tools.executeHttpRequest(this, post, true);
			return html;
		} catch (ValueNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
