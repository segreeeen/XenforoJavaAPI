package at.hsol.xenapi.constants;

/**
 * @author Felix Batusic
 *
 */
public class UrlConstants {
	public static final String FORUMS = "?forums/";
	public static final String MEMBERS = "?members/";
	public static final String THREADS = "?threads/";
	public static final String CONVERSATIONS = "?conversations/";
	public static final String LOGIN = "/login/login";
	public static final String ADD_REPLY = "/add-reply";
	public static final String ADD_THREAD = "/add-thread";
	public static final String CREATE_THREAD = "/create-thread";
	public static final String CREATE_CONVERSATION = CONVERSATIONS + "add";
	public static final String INSERT_CONVERSATION = CONVERSATIONS + "insert";
	public static final String INSERT_REPLY = "/insert-reply/";
	public static final String USER_AGENT = "Mozilla/5.0";
	public static final String SEARCH = "/?search";
	public static final String SEARCH_POST = SEARCH + "&type=post";
	public static final String SEARCH_PROFILE_POST = SEARCH + "&type=profile_post";
	public static final String SEARCH_ACTION_SUFFIX = SEARCH + "/search";
	public static final String PROFILE_POST_ACTION = "/post";
}
