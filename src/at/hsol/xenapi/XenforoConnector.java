package at.hsol.xenapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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

import at.hsol.xenapi.err.ValueNotFoundException;

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

		HttpPost post = new HttpPost(url + UrlConstants.LOGIN);
		post.setHeader("User-Agent", USER_AGENT);

		try {
			Set<BasicNameValuePair> urlParameters = new PostSetBuilder("").addLogin(username).addRegister(null)
					.addPassword("admin").addCookieCheck(null).addRedirect(null).addToken().build();
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
		} catch (ValueNotFoundException e) {
			// TODO Auto-generated catch block
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

	public String addReply(String url, String message) {
		Set<BasicNameValuePair> vals = getAddreplyHiddenValues(url);
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", USER_AGENT);
			vals.add(new BasicNameValuePair(PostConstants.MESSAGE_HTML_NAME, message));
			for (BasicNameValuePair basicNameValuePair : vals) {
				System.out.println(basicNameValuePair);
			}
			post.setEntity(new UrlEncodedFormEntity(vals));

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post, context);
			String html;
			try {
				renewCurrentUrl(response);
				System.out.println(currentUrl);

				html = createHtmlString(response);
			} finally {
				response.close();
			}
			return html;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Set<BasicNameValuePair> getAddreplyHiddenValues(String url) {
		Set<BasicNameValuePair> nameValSet = null;
		try {
			HttpPost post = new HttpPost(url + UrlConstants.ADD_REPLY);
			post.setHeader("User-Agent", USER_AGENT);
			Set<BasicNameValuePair> values = getAddreplyAccessHiddenValues(url);

			post.setEntity(new UrlEncodedFormEntity(values));

			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post, context);
			try {
				String html = createHtmlString(response);
				renewCurrentUrl(response);

				nameValSet = new PostSetBuilder(html).addToken().addRelativeResolver().addWatchThreadState()
						.addAttachmentHash().addRequestUri().addNoRedirect(null).addResponseType(null).build();
				for (BasicNameValuePair basicNameValuePair : values) {
					System.out.println(basicNameValuePair);
				}
			} catch (ValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		Set<BasicNameValuePair> nameValSet = null;
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get, context);
			try {
				String html = createHtmlString(response);
				System.out.println(currentUrl);

				System.out.println(html);
				nameValSet = new PostSetBuilder(html).addToken().addRelativeResolver().addLastDate().addLastKnownDate()
						.addAttachmentHash().addMoreOptions(null).addMessageHtml(null).build();

			} catch (ValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
