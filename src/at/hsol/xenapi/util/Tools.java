package at.hsol.xenapi.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import at.hsol.xenapi.interfaces.Connection;

public class Tools {
	public static String createHtmlString(HttpResponse response) throws UnsupportedOperationException, IOException {
		HttpEntity entity = response.getEntity();
		String html = null;
		html = new String(EntityUtils.toString(entity));
		return html;
	}

	/**
	 * 
	 * @param con
	 *            The connection object.
	 * @param get
	 *            The get Statement
	 * @param renewCurrentUrl
	 *            true = renew, false = do nothing
	 * @return The html response as a String
	 */
	public static String executeHttpRequest(Connection con, HttpUriRequest get, boolean renewCurrentUrl) {
		try {
			CloseableHttpResponse response;

			response = (CloseableHttpResponse) con.getClient().execute(get, con.getContext());

			String html = null;
			try {
				html = Tools.createHtmlString(response);
				if (renewCurrentUrl) {
					con.renewCurrentUrl(response);
				}
			} finally {
				response.close();
			}
			return html;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
