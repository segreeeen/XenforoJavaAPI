package at.hsol.xenapi.handlers;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.constants.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.PostSetBuilder;
import at.hsol.xenapi.util.Tools;

public class NewConversationHandler extends AbstractFunctionalityHandler {

	NewConversationHandler(Connection connection) {
		super(connection);
	}

	public String createConversation(String title, String message, String recipients) {
		HttpGet get = new HttpGet(getIndexUrl() + UrlConstants.CREATE_CONVERSATION);
		try {
			String html = Tools.executeHttpRequest(this, get, false);
			Set<BasicNameValuePair> values = new PostSetBuilder(html).addRecipients(recipients).addTitle(title)
					.addMessageHtml(message).addRelativeResolver().addAttachmentHash().addToken().addNoRedirect(null)
					.addResponseType(null).build();

			HttpPost post = new HttpPost(getIndexUrl() + UrlConstants.INSERT_CONVERSATION);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);

			post.setEntity(new UrlEncodedFormEntity(values));

			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(post, getContext());
			try {
				renewCurrentUrl(response);
				html = Tools.createHtmlString(response);
				return html;
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
