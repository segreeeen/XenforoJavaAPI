package at.hsol.xenapi.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class Tools {
	public static String createHtmlString(HttpResponse response) throws UnsupportedOperationException, IOException {
		HttpEntity entity = response.getEntity();
		String html = null;
		html = new String(EntityUtils.toString(entity));
		return html;
	}

}
