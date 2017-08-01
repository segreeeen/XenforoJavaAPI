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

public class ReplyConversationHandler extends AbstractFunctionalityHandler {

	ReplyConversationHandler(Connection connection) {
		super(connection);

	}

	public String replyConversation(String url, String message) {
		HttpGet get = new HttpGet(url);
		try {
			String html = Tools.executeHttpRequest(this, get, false);
			Set<BasicNameValuePair> values = new PostSetBuilder(html).addMessageHtml(null).addRelativeResolver()
					.addMoreOptions(null).addAttachmentHash().addLastDate().addLastKnownDate().addToken().build();

			HttpPost post = new HttpPost(url + UrlConstants.INSERT_REPLY);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);

			post.setEntity(new UrlEncodedFormEntity(values));

			html = Tools.executeHttpRequest(this, post, true);

			values = new PostSetBuilder(html).addMessageHtml(message).addRelativeResolver().addAttachmentHash()
					.addToken().addResponseType(null).build();

			post = new HttpPost(url + UrlConstants.INSERT_REPLY);
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
