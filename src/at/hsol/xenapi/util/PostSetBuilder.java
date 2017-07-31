package at.hsol.xenapi.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import at.hsol.xenapi.constants.PostConstants;
import at.hsol.xenapi.constants.SelectConstants;
import at.hsol.xenapi.err.ValueNotFoundException;

/**
 * This class builds a set of post values which it reads from the html string
 * provided in the constructor or with a default value. To build the set, call
 * the add functions and finally build() to receive the set.
 * 
 * @author Felix Batusic, thomassulzbacher
 *
 */
public class PostSetBuilder {

	private final Set<BasicNameValuePair> postSet;
	private final Document htmlDoc;

	public PostSetBuilder(String html) {
		this.htmlDoc = Jsoup.parse(html);
		this.postSet = new HashSet<>();
	}

	public PostSetBuilder addLogin(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		postSet.add(new BasicNameValuePair(PostConstants.LOGIN, s));
		return this;
	}

	public PostSetBuilder addRegister(String s) {
		if (s == null) {
			s = PostConstants.REGISTER_OFF;
		}
		postSet.add(new BasicNameValuePair(PostConstants.REGISTER, s));
		return this;
	}

	public PostSetBuilder addPassword(String s) {
		if (s == null) {
			throw new NullPointerException();
		}
		postSet.add(new BasicNameValuePair(PostConstants.PASSWORD, s));
		return this;
	}

	public PostSetBuilder addCookieCheck(String s) {
		if (s == null) {
			s = PostConstants.COOKIE_CHECK_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.COOKIE_CHECK, s));
		return this;
	}

	public PostSetBuilder addRedirect(String s) {
		if (s == null) {
			s = PostConstants.REDIRECT_ROOT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.REDIRECT, s));
		return this;
	}

	public PostSetBuilder addMoreOptions(String s) {
		if (s == null) {
			s = PostConstants.MORE_OPIONS_VALUE;
		}
		postSet.add(new BasicNameValuePair(PostConstants.MORE_OPIONS_NAME, s));
		return this;
	}

	public PostSetBuilder addTitle(String s) throws ValueNotFoundException {
		if (s == null) {
			throw new ValueNotFoundException("Title can not be null!");
		}
		postSet.add(new BasicNameValuePair(PostConstants.TITLE, s));
		return this;
	}

	public PostSetBuilder addDiscussionOpen(String s) {
		if (s == null) {
			s = PostConstants.DISCUSSION_OPEN_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.DISCUSSION_OPEN, s));
		return this;
	}

	public PostSetBuilder addMessageHtml(String s) {
		if (s == null) {
			s = PostConstants.MESSAGE_HTML_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, s));
		return this;
	}

	public PostSetBuilder addRelativeResolver() throws ValueNotFoundException {
		postSet.add(createBasicNameValuePair(PostConstants.RELATIVE_RESOLVER,
				"[name=" + PostConstants.RELATIVE_RESOLVER + "]", SelectConstants.ATTR_VALUE));
		return this;
	}

	public PostSetBuilder addResponseType(String s) {
		if (s == null) {
			s = PostConstants.RESPONSE_TYPE_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.RESPONSE_TYPE, s));
		return this;
	}

	public PostSetBuilder addTags(String s) {
		if (s == null) {
			s = PostConstants.TAGS_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.TAGS, s));

		return this;
	}

	public PostSetBuilder addRecipients(String s) throws ValueNotFoundException {
		if (s == null) {
			throw new ValueNotFoundException("Recipients can not be null!");
		}
		postSet.add(new BasicNameValuePair(PostConstants.RECIPIENTS, s));

		return this;
	}

	public PostSetBuilder addMessage(String s) throws ValueNotFoundException {
		if (s == null) {
			throw new ValueNotFoundException("Message can not be null!");
		}
		postSet.add(new BasicNameValuePair(PostConstants.MESSAGE, s));

		return this;
	}

	public PostSetBuilder addSetDiscussionOpen(String s) {
		if (s == null) {
			s = PostConstants.SET_DISCUSSION_OPEN_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.SET_DISCUSSION_OPEN, s));
		return this;
	}

	public PostSetBuilder addSetSticky(String s) {
		if (s == null) {
			s = PostConstants.SET_STICKY_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.SET_STICKY, s));
		return this;
	}

	public PostSetBuilder addPollQuestion(String s) {
		if (s == null) {
			s = PostConstants.POLL_QUESTION_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.POLL_QUESTION, s));
		return this;
	}

	public PostSetBuilder addPollResponse(String s) {
		if (s == null) {
			s = PostConstants.POLL_RESPONSES_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.POLL_RESPONSES, s));
		return this;
	}

	public PostSetBuilder addPollMaxVotesType(String s) {
		if (s == null) {
			s = PostConstants.POLL_MAX_VOTES_TYPE_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.POLL_MAX_VOTES_TYPE, s));
		return this;
	}

	public PostSetBuilder addPollChangeVote(String s) {
		if (s == null) {
			s = PostConstants.POLL_CHANGE_VOTE_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.POLL_CHANGE_VOTE, s));
		return this;
	}

	public PostSetBuilder addPollViewResultsUnvoted(String s) {
		if (s == null) {
			s = PostConstants.POLL_VIEW_RESULTS_UNVOTED_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.POLL_VIEW_RESULTS_UNVOTED, s));
		return this;
	}

	public PostSetBuilder addToken() throws ValueNotFoundException {
		try {
			postSet.add(createBasicNameValuePair(PostConstants.TOKEN, "[name=" + PostConstants.TOKEN + "]",
					SelectConstants.ATTR_VALUE));
		} catch (ValueNotFoundException e) {
			postSet.add(new BasicNameValuePair(PostConstants.TOKEN, ""));
		}
		return this;
	}

	public PostSetBuilder addNoRedirect(String s) {
		if (s == null) {
			s = PostConstants.NO_REDIRECT_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.NO_REDIRECT, s));
		return this;
	}

	public PostSetBuilder addRequestUri(String selector) throws ValueNotFoundException {
		postSet.add(createBasicNameValuePair(PostConstants.REQUEST_URI, selector, SelectConstants.ATTR_ACTION));
		return this;
	}

	public PostSetBuilder addWatchThreadState() throws ValueNotFoundException {
		postSet.add(createBasicNameValuePair(PostConstants.WATCH_THREAD_STATE,
				"[name=" + PostConstants.WATCH_THREAD_STATE + "]", SelectConstants.ATTR_VALUE));
		return this;
	}

	public PostSetBuilder addLastDate() throws ValueNotFoundException {
		postSet.add(createBasicNameValuePair(PostConstants.LAST_DATE, "[name=" + PostConstants.LAST_DATE + "]",
				SelectConstants.ATTR_VALUE));
		return this;
	}

	public PostSetBuilder addLastKnownDate() throws ValueNotFoundException {
		try {
			postSet.add(createBasicNameValuePair(PostConstants.LAST_KNOWN_DATE,
					"[name=" + PostConstants.LAST_KNOWN_DATE + "]", SelectConstants.ATTR_VALUE));
		} catch (ValueNotFoundException e) {
			postSet.add(new BasicNameValuePair(PostConstants.LAST_KNOWN_DATE, ""));
		}
		return this;
	}

	public PostSetBuilder addAttachmentHash() throws ValueNotFoundException {
		postSet.add(createBasicNameValuePair(PostConstants.ATTACHMENT_HASH,
				"[name=" + PostConstants.ATTACHMENT_HASH + "]", SelectConstants.ATTR_VALUE));
		return this;
	}

	public PostSetBuilder addKeywords(String s) {
		if (s == null) {
			s = PostConstants.KEYWORDS_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.KEYWORDS, s));
		return this;
	}

	public PostSetBuilder addUsers(String s) {
		if (s == null) {
			s = PostConstants.USERS_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.USERS, s));
		return this;
	}

	public PostSetBuilder addDate(String s) {
		if (s == null) {
			s = PostConstants.DATE_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.DATE, s));
		return this;
	}

	public PostSetBuilder addNodes(String s) {
		if (s == null) {
			s = PostConstants.NODES_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.NODES, s));
		return this;
	}

	public PostSetBuilder addChildNodes(String s) {
		if (s == null) {
			s = PostConstants.CHILD_NODES_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.CHILD_NODES, s));
		return this;
	}

	public PostSetBuilder addOrder(String s) {
		if (s == null) {
			s = PostConstants.ORDER_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.ORDER, s));
		return this;
	}

	public PostSetBuilder addSearchTypePost(String s) {
		if (s == null) {
			s = PostConstants.SEARCH_TYPE_POST;
		}
		postSet.add(new BasicNameValuePair(PostConstants.SEARCH_TYPE, s));
		return this;
	}

	public PostSetBuilder addProfileUsers(String s) {
		if (s == null) {
			s = PostConstants.PROFILE_USERS_DEFAULT;
		}
		postSet.add(new BasicNameValuePair(PostConstants.PROFILE_USERS, s));
		return this;
	}

	private BasicNameValuePair createBasicNameValuePair(String postConstant, String selectVal, String attrVal)
			throws ValueNotFoundException {
		String parsed = htmlDoc.select(selectVal).attr(attrVal);
		if (parsed.equals("")) {
			throw new ValueNotFoundException("Value could not be parsed: " + postConstant);
		}
		return new BasicNameValuePair(postConstant, parsed);
	}

	public Set<BasicNameValuePair> build() {
		return postSet;
	}
}
