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

public class SearchHandler extends AbstractFunctionalityHandler {

	public SearchHandler(Connection connection) {
		super(connection);
	}

	/**
	 * @param keywords
	 *            Keywords to look for everywhere
	 * @param users
	 *            Authors to look for
	 * @param date
	 *            yyyy-mm-dd
	 * @param childNodes
	 *            search child forums as well
	 * @return
	 */
	public String searchEverything(String url, String keywords, String users, String date, String childNodes,
			String titleOnly) {
		HttpGet get = new HttpGet(url);
		String html = Tools.executeHttpRequest(this, get, false);
		try {
			Set<BasicNameValuePair> valueSet = new PostSetBuilder(html).addKeywords(keywords).addUsers(users)
					.addDate(date).addChildNodes(childNodes).addToken().addNoRedirect(null).build();
			HttpPost post = new HttpPost(url + UrlConstants.SEARCH_ACTION_SUFFIX);
			post.setHeader("User-Agent", UrlConstants.USER_AGENT);

			post.setEntity(new UrlEncodedFormEntity(valueSet));
			html = Tools.executeHttpRequest(this, post, true);
			return html;
		} catch (ValueNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
