package at.hsol.xenapi.handlers;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.PostConstants;
import at.hsol.xenapi.PostSetBuilder;
import at.hsol.xenapi.SelectConstants;
import at.hsol.xenapi.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.Tools;

public class AddReplyHandler extends AbstractHandler {

	public AddReplyHandler(Connection connection) {
		super(connection);
	}

	public String addReply(String url, String message) {
		Set<BasicNameValuePair> vals = getAddreplyHiddenValues(url);
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);
			vals.add(new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, message));
			for (BasicNameValuePair basicNameValuePair : vals) {
				System.out.println(basicNameValuePair);
			}
			post.setEntity(new UrlEncodedFormEntity(vals));

			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(post, getContext());
			String html;
			try {
				renewCurrentUrl(response);
				html = Tools.createHtmlString(response);
			} finally {
				response.close();
			}
			return html;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Set<BasicNameValuePair> getAddreplyHiddenValues(String url) {
		Set<BasicNameValuePair> nameValSet = null;
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);
			Set<BasicNameValuePair> values = getAddreplyAccessHiddenValues(url);

			post.setEntity(new UrlEncodedFormEntity(values));

			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(post, getContext());
			try {
				String html = Tools.createHtmlString(response);
				super.renewCurrentUrl(response);

				nameValSet = new PostSetBuilder(html).addToken().addRelativeResolver().addWatchThreadState()
						.addAttachmentHash().addRequestUri(SelectConstants.ID_THREAD_REPLY).addNoRedirect(null)
						.addResponseType(null).build();
				for (BasicNameValuePair basicNameValuePair : values) {
					System.out.println(basicNameValuePair);
				}
			} catch (ValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				response.close();
			}

			return nameValSet;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Set<BasicNameValuePair> getAddreplyAccessHiddenValues(String url) {
		Set<BasicNameValuePair> nameValSet = null;
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(get, getContext());
			try {
				String html = Tools.createHtmlString(response);
				System.out.println(currentUrl);

				System.out.println(html);
				nameValSet = new PostSetBuilder(html).addToken().addRelativeResolver().addLastDate().addLastKnownDate()
						.addAttachmentHash().addMoreOptions(null).addMessageHtml(null).build();

			} catch (ValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				response.close();
			}
			return nameValSet;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
