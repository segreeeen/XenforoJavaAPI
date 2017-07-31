package at.hsol.xenapi.handlers;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import at.hsol.xenapi.constants.SelectConstants;
import at.hsol.xenapi.constants.UrlConstants;
import at.hsol.xenapi.err.ValueNotFoundException;
import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.PostSetBuilder;
import at.hsol.xenapi.util.Tools;

public class CreateThreadHandler extends AbstractConnectionHandler {

	public CreateThreadHandler(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public String addNewThread(String url, String title, String message, String tags) {
		HttpGet get = new HttpGet(url + UrlConstants.CREATE_THREAD);

		try {
			CloseableHttpResponse response = (CloseableHttpResponse) getClient().execute(get, getContext());
			String html = null;
			try {
				html = Tools.createHtmlString(response);
				System.out.println();
			} finally {
				response.close();
			}
			Set<BasicNameValuePair> values = new PostSetBuilder(html).addTitle(title).addMessageHtml(message)
					.addRelativeResolver().addTags(tags).addAttachmentHash().addWatchThreadState()
					.addDiscussionOpen(null).addSetDiscussionOpen(null).addSetSticky(null).addPollQuestion(null)
					.addPollResponse(null).addPollResponse(null).addPollMaxVotesType(null).addPollChangeVote(null)
					.addPollViewResultsUnvoted(null).addToken().addRequestUri(SelectConstants.ID_THREAD_CREATE)
					.addNoRedirect(null).addResponseType(null).build();

			HttpPost post = new HttpPost(url + UrlConstants.ADD_THREAD);
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
