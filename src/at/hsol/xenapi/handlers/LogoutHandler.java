package at.hsol.xenapi.handlers;

import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import at.hsol.xenapi.interfaces.Connection;
import at.hsol.xenapi.util.Tools;

public class LogoutHandler extends AbstractFunctionalityHandler {

	LogoutHandler(Connection connection) {
		super(connection);
	}

	public String logout() {
		HttpGet get = new HttpGet(getIndexUrl());
		String html = Tools.executeHttpRequest(connection, get, false);
		html = parseLogoutUrl(html);
		get = new HttpGet(getIndexUrl() + "/" + html);
		html = Tools.executeHttpRequest(connection, get, false);
		return html;
	}

	private String parseLogoutUrl(String indexHtml) {
		Document htmlDoc = Jsoup.parse(indexHtml);
		String parsed = htmlDoc.select("a.LogOut").attr("href");
		return parsed;
	}
}
