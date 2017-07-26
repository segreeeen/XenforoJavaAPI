package at.hsol.xenapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpCoreContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XenforoConnector {
	public final String USER_AGENT = "Mozilla/5.0";

	private final HttpClient client;
	private final HttpClientContext context;

	private String currentUrl;

	public XenforoConnector() {
		this.client = HttpClients.createDefault();
		this.context = HttpClientContext.create();
	}

	public String login(String url, String username, String password) {

		HttpPost post = new HttpPost(url + UrlConstants.URL);
		post.setHeader("User-Agent", USER_AGENT);
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair(PostConstants.LOGIN, username));
		urlParameters.add(new BasicNameValuePair(PostConstants.REGISTER, "0"));
		urlParameters.add(new BasicNameValuePair(PostConstants.PASSWORD, password));
		urlParameters.add(new BasicNameValuePair(PostConstants.COOKIE_CHECK, "0"));
		urlParameters.add(new BasicNameValuePair(PostConstants.REDIRECT, "/"));
		urlParameters.add(new BasicNameValuePair(PostConstants.TOKEN, ""));

		try {
			String s;
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post, context);
			try {
				renewCurrentUrl(response);
				s = createHtmlString(response);
			} finally {
				response.close();
			}
			return s;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	private String createHtmlString(HttpResponse response) throws UnsupportedOperationException, IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line + "\n");
		}
		return result.toString();
	}

	private void renewCurrentUrl(HttpResponse response) throws IOException {
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new IOException(response.getStatusLine().toString());
		HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);
		HttpHost currentHost = (HttpHost) context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
		this.currentUrl = (currentReq.getURI().isAbsolute()) ? currentReq.getURI().toString()
				: (currentHost.toURI() + currentReq.getURI());
	}

	private void addReply(String url, String message) {
		HashSet<BasicNameValuePair> vals = getAddreplyHiddenValues(url);
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", USER_AGENT);
			vals.add(new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, message));
			for (BasicNameValuePair basicNameValuePair : vals) {
				System.out.println(basicNameValuePair);
			}
			post.setEntity(new UrlEncodedFormEntity(vals));

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post, context);
			try {
				renewCurrentUrl(response);
				System.out.println(currentUrl);

				String html = createHtmlString(response);
				System.out.println(html);
			} finally {
				response.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HashSet<BasicNameValuePair> getAddreplyHiddenValues(String url) {
		HashSet<BasicNameValuePair> nameValSet = new HashSet<>();
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", USER_AGENT);
			Set<BasicNameValuePair> values = getAddreplyAccessHiddenValues(url);

			post.setEntity(new UrlEncodedFormEntity(values));

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post, context);
			try {
				renewCurrentUrl(response);
				System.out.println(currentUrl);

				String html = createHtmlString(response);
				Document doc = Jsoup.parse(html);
				Elements vals = doc.select("input, textarea");

				for (Element e : vals) {
					if (e.attr("name").equals(PostConstants.TOKEN)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.RELATIVE_RESOLVER)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.WATCH_THREAD_STATE)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.LAST_KNOWN_DATE)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.ATTACHMENT_HASH)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					}
				}
				nameValSet.add(
						new BasicNameValuePair(PostConstants.REQUEST_URI, doc.select("#ThreadReply").attr("action")));
				nameValSet.add(new BasicNameValuePair(PostConstants.NO_REDIRECT, PostConstants.NO_REDIRECT_DEFAULT));
				nameValSet
						.add(new BasicNameValuePair(PostConstants.RESPONSE_TYPE, PostConstants.RESPONSE_TYPE_DEFAULT));
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
		HashSet<BasicNameValuePair> nameValSet = new HashSet<>();
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get, context);
			try {
				String html = createHtmlString(response);
				Document doc = Jsoup.parse(html);
				Elements links = doc.select("input").attr("type", "hidden");
				for (Element e : links) {
					if (e.attr("name").equals(PostConstants.TOKEN)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.RELATIVE_RESOLVER)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.LAST_DATE)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.LAST_KNOWN_DATE)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					} else if (e.attr("name").equals(PostConstants.ATTACHMENT_HASH)) {
						nameValSet.add(new BasicNameValuePair(e.attr("name"), e.attr("value")));
					}
				}
				nameValSet.add(
						new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, PostConstants.MESSAGE_HTML_DEFAULT));
				nameValSet.add(new BasicNameValuePair(PostConstants.MORE_OPIONS_NAME, PostConstants.MORE_OPIONS_VALUE));
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
