package at.hsol.xenapi.handlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.PostSetBuilder;
import at.hsol.xenapi.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.Tools;

public class LoginHandler extends AbstractHandler {

	public LoginHandler(Connection connection) {
		super(connection);
	}

	public String login(String url, String username, String password) {

		HttpPost post = new HttpPost(url + UrlConstants.LOGIN);
		post.setHeader("User-Agent", UrlConstants.USER_AGENT);

		try {
			Set<BasicNameValuePair> urlParameters = new PostSetBuilder("").addLogin(username).addRegister(null)
					.addPassword(password).addCookieCheck(null).addRedirect(null).addToken().build();
			String s;
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(post, getContext());
			try {
				renewCurrentUrl(response);
				s = Tools.createHtmlString(response);
			} finally {
				response.close();
			}
			return s;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ValueNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return null;

	}
}
