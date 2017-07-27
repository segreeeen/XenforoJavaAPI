package at.hsol.xenapi.handlers;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.PostSetBuilder;
import at.hsol.xenapi.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.Tools;

public class ReplyConversationHandler extends AbstractHandler {

	public ReplyConversationHandler(Connection connection) {
		super(connection);

	}

	public String replyConversation(String url, String message, String recipients) {
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(get, getContext());
			String html = null;
			try {
				html = Tools.createHtmlString(response);
			} finally {
				response.close();
			}
			Set<BasicNameValuePair> values = new PostSetBuilder(html).addMessageHtml(null).addRelativeResolver()
					.addMoreOptions(null).addAttachmentHash().addLastDate().addLastKnownDate().addToken().build();

			HttpPost post = new HttpPost(url + UrlConstants.INSERT_REPLY);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);

			post.setEntity(new UrlEncodedFormEntity(values));

			response = (CloseableHttpResponse) getClient().execute(post, getContext());
			try {
				renewCurrentUrl(response);
				html = Tools.createHtmlString(response);
			} finally {
				response.close();
			}

			values = new PostSetBuilder(html).addMessageHtml(message).addRelativeResolver().addAttachmentHash()
					.addToken().build();

			post = new HttpPost(url + UrlConstants.INSERT_REPLY);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);

			post.setEntity(new UrlEncodedFormEntity(values));

			response = (CloseableHttpResponse) getClient().execute(post, getContext());
			try {
				renewCurrentUrl(response);
				html = Tools.createHtmlString(response);
				System.out.println(html);
			} finally {
				response.close();
			}
		} catch (IOException | ValueNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
