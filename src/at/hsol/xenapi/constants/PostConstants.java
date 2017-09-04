package at.hsol.xenapi.constants;

/**
 * @author Felix Batusic
 *
 */
public class PostConstants {
	public static final String EMPTY = "";

	// Use for Login
	public static final String LOGIN = "login";
	public static final String REGISTER = "register";
	public static final String REGISTER_OFF = "0";
	public static final String REGISTER_ON = "1";
	public static final String PASSWORD = "password";
	public static final String COOKIE_CHECK = "cookie_check";
	public static final String COOKIE_CHECK_DEFAULT = "0";

	public static final String REDIRECT = "redirect";
	public static final String REDIRECT_ROOT = "/";

	public static final String MORE_OPIONS_NAME = "more_options";
	public static final String MORE_OPIONS_VALUE = "Weitere Einstellungen...";

	public static final String MESSAGE_HTML_NAME = "message_html";
	public static final String MESSAGE_HTML_DEFAULT = "<p><br></p>";

	public static final String MESSAGE = "message";

	public static final String RELATIVE_RESOLVER = "_xfRelativeResolver";
	public static final String RESPONSE_TYPE = "_xfResponseType";
	public static final String RESPONSE_TYPE_DEFAULT = "json";
	public static final String TOKEN = "_xfToken";
	public static final String NO_REDIRECT = "_xfNoRedirect";

	public static final String NO_REDIRECT_DEFAULT = "1";
	public static final String REQUEST_URI = "_xfRequestUri";
	public static final String WATCH_THREAD_STATE = "watch_thread_state";
	public static final String LAST_DATE = "last_date";
	public static final String LAST_KNOWN_DATE = "last_known_date";
	public static final String ATTACHMENT_HASH = "attachment_hash";

	// Creating new Threads
	public static final String TITLE = "title";
	public static final String TAGS = "tags";
	public static final String TAGS_DEFAULT = EMPTY;
	public static final String RECIPIENTS = "recipients";
	public static final String DISCUSSION_OPEN = "discussion_open";
	public static final String DISCUSSION_OPEN_DEFAULT = "1";
	public static final String SET_DISCUSSION_OPEN = "_set[discussion_open]";
	public static final String SET_DISCUSSION_OPEN_DEFAULT = "1";
	public static final String SET_STICKY = "_set[sticky]";
	public static final String SET_STICKY_DEFAULT = "1";
	public static final String POLL_QUESTION = "poll[question]";
	public static final String POLL_QUESTION_DEFAULT = EMPTY;
	public static final String POLL_RESPONSES = "poll[responses][]";
	public static final String POLL_RESPONSES_DEFAULT = EMPTY;
	public static final String POLL_MAX_VOTES_TYPE = "poll[max_votes_type]";
	public static final String POLL_MAX_VOTES_TYPE_DEFAULT = "single";
	public static final String POLL_CHANGE_VOTE = "poll[change_vote]";
	public static final String POLL_CHANGE_VOTE_DEFAULT = "1";
	public static final String POLL_VIEW_RESULTS_UNVOTED = "poll[view_results_unvoted]";
	public static final String POLL_VIEW_RESULTS_UNVOTED_DEFAULT = "1";

	// Search
	public static final String KEYWORDS = "keywords";
	public static final String KEYWORDS_DEFAULT = EMPTY;
	public static final String USERS = "users";
	public static final String USERS_DEFAULT = EMPTY;
	public static final String DATE = "date";
	public static final String DATE_DEFAULT = EMPTY;
	public static final String NODES = "nodes[]";
	public static final String NODES_DEFAULT = EMPTY;
	public static final String CHILD_NODES = "child_nodes";
	public static final String CHILD_NODES_DEFAULT = "1";
	public static final String ORDER = "order";
	public static final String ORDER_DEFAULT = "date";
	public static final String SEARCH_TYPE = "search_type";
	public static final String SEARCH_TYPE_POST = "post";
	public static final String PROFILE_USERS = "profile_users";
	public static final String PROFILE_USERS_DEFAULT = EMPTY;

}
