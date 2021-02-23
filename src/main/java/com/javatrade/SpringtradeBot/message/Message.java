package com.javatrade.SpringtradeBot.message;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component("message")
public class Message {

	private static final String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

	public void sendMessage(String apiToken, String chatId, String message) {

		// String text = "Hello world!";

		String urlString = null;
		urlString = String.format(this.urlString, apiToken, chatId, message);

		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = new BufferedInputStream(conn.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
