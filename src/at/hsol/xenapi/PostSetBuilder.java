package at.hsol.xenapi;

import java.util.HashSet;
import java.util.Set;

import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class builds a set of post values which it reads from the html string
 * provided in the constructor. To build the set, call the add functions.
 * 
 * @author Felix Batusic
 *
 */
public class PostSetBuilder {
	private final Set<BasicNameValuePair> postSet;
	private final Document htmlDoc;

	public static void main(String[] args) {
		String s = "<input type=\"hidden\" name=\"watch_thread_state\" value=\"1\">"
				+ "		<input type=\"hidden\" name=\"_xfToken\" value=\"4308,1501060148,a4d53c77938a734f59a0c4ff4252c4d16bf46a37\">\r\n"
				+ "		<input type=\"hidden\" name=\"attachment_hash\" value=\"517b809031c2902f368e1b7f72442328\">\r\n"
				+ "		<input type=\"hidden\" name=\"_xfRelativeResolver\" value=\"http://www.hoer-talk.de/threads/forenspiel-licht-an-licht-aus.19072/add-reply\">"
				+ "<input type=\"hidden\" name=\"last_date\" value=\"1379833388\" data-load-value=\"1379833388\">\r\n"
				+ "<input type=\"hidden\" name=\"last_known_date\" value=\"1501014954\">"
				+ "<form action=\"threads/forenspiel-licht-an-licht-aus.19072/add-reply\" method=\"post\" id=\"ThreadReply\"\r\n"
				+ "	class=\"xenForm Preview AutoValidator\"\r\n"
				+ "	data-previewUrl=\"threads/forenspiel-licht-an-licht-aus.19072/reply/preview\"\r\n"
				+ "	data-redirect=\"on\">";
		PostSetBuilder psBuilder = new PostSetBuilder(s);
		Set<BasicNameValuePair> val = psBuilder.addRelativeResolver().addAttachmentHash().addToken()
				.addWatchThreadState().addLogin("blabla").addPassword("blub").addRegister(null).addCookieCheck(null)
				.addLastDate().addLastKnownDate().addMessageHtml("Test123").addResponseType(null).addRedirect(null)
				.addMoreOptions(null).addNoRedirect(null).addRequestUri().build();
		for (BasicNameValuePair basicNameValuePair : val) {
			System.out.println(basicNameValuePair);
		}

	}

	PostSetBuilder(String html) {
		this.htmlDoc = Jsoup.parse(html);
		this.postSet = new HashSet<>();
	}

	PostSetBuilder addLogin(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		postSet.add(new BasicNameValuePair(PostConstants.LOGIN, s));
		return this;
	}

	PostSetBuilder addRegister(String s) {
		if (s == null) {
			s = PostConstants.REGISTER_OFF;
		}
		postSet.add(new BasicNameValuePair(PostConstants.REGISTER, s));
		return this;
	}

	PostSetBuilder addPassword(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		postSet.add(new BasicNameValuePair(PostConstants.PASSWORD, s));
		return this;
	}

	PostSetBuilder addCookieCheck(String s) {
		if (s == null) {
			s = PostConstants.COOKIE_CHECK_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.COOKIE_CHECK, s));
		return this;
	}

	PostSetBuilder addRedirect(String s) {
		if (s == null) {
			s = PostConstants.REDIRECT_ROOT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.REDIRECT, s));
		return this;
	}

	PostSetBuilder addMoreOptions(String s) {
		if (s == null) {
			s = PostConstants.MORE_OPIONS_VALUE;
		}
		postSet.add(new BasicNameValuePair(PostConstants.MORE_OPIONS_NAME, s));
		return this;
	}

	PostSetBuilder addMessageHtml(String s) {
		if (s == null) {
			s = PostConstants.MESSAGE_HTML_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, s));
		return this;
	}

	PostSetBuilder addRelativeResolver() {
		postSet.add(new BasicNameValuePair(PostConstants.RELATIVE_RESOLVER,
				htmlDoc.select("[name=" + PostConstants.RELATIVE_RESOLVER + "]").attr("value")));
		return this;
	}

	PostSetBuilder addResponseType(String s) {
		if (s == null) {
			s = PostConstants.RESPONSE_TYPE_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.RESPONSE_TYPE, s));
		return this;
	}

	PostSetBuilder addToken() {
		postSet.add(new BasicNameValuePair(PostConstants.TOKEN,
				htmlDoc.select("[name=" + PostConstants.TOKEN + "]").attr("value")));
		return this;
	}

	PostSetBuilder addNoRedirect(String s) {
		if (s == null) {
			s = PostConstants.NO_REDIRECT_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.NO_REDIRECT, s));
		return this;
	}

	PostSetBuilder addRequestUri() {
		postSet.add(new BasicNameValuePair(PostConstants.REQUEST_URI, htmlDoc.select("#ThreadReply").attr("action")));
		return this;
	}

	PostSetBuilder addWatchThreadState() {
		postSet.add(new BasicNameValuePair(PostConstants.WATCH_THREAD_STATE,
				htmlDoc.select("[name=" + PostConstants.WATCH_THREAD_STATE + "]").attr("value")));
		return this;
	}

	PostSetBuilder addLastDate() {
		postSet.add(new BasicNameValuePair(PostConstants.LAST_DATE,
				htmlDoc.select("[name=" + PostConstants.LAST_DATE + "]").attr("value")));
		return this;
	}

	PostSetBuilder addLastKnownDate() {
		postSet.add(new BasicNameValuePair(PostConstants.LAST_KNOWN_DATE,
				htmlDoc.select("[name=" + PostConstants.LAST_KNOWN_DATE + "]").attr("value")));
		return this;
	}

	PostSetBuilder addAttachmentHash() {
		postSet.add(new BasicNameValuePair(PostConstants.ATTACHMENT_HASH,
				htmlDoc.select("[name=" + PostConstants.ATTACHMENT_HASH + "]").attr("value")));
		return this;
	}

	Set<BasicNameValuePair> build() {
		return postSet;
	}
}
