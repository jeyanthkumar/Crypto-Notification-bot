package com.javatrade.SpringtradeBot.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.javatrade.SpringtradeBot.message.Message;
import com.javatrade.SpringtradeBot.utitlities.FileUtils;
import com.javatrade.SpringtradeBot.utitlities.RSI;

@Component
public class Start extends TimerTask {

	@Value("${binanceApiKey}")
	private String binanceApiKey;

	@Value("${binanceSecret}")
	private String binanceSecret;

	@Value("${pairsListPath}")
	private String pairsListPath;

	@Value("${telegramApiToken}")
	private String telegramApiToken;

	@Value("${telegramChatId}")
	private String telegramChatId;

	public String getBinanceApiKey() {
		return binanceApiKey;
	}

	public void setBinanceApiKey(String binanceApiKey) {
		this.binanceApiKey = binanceApiKey;
	}

	public String getBinanceSecret() {
		return binanceSecret;
	}

	public void setBinanceSecret(String binanceSecret) {
		this.binanceSecret = binanceSecret;
	}

	public String getPairsListPath() {
		return pairsListPath;
	}

	public void setPairsListPath(String pairsListPath) {
		this.pairsListPath = pairsListPath;
	}

	public String getTelegramApiToken() {
		return telegramApiToken;
	}

	public void setTelegramApiToken(String telegramApiToken) {
		this.telegramApiToken = telegramApiToken;
	}

	public String getTelegramChatId() {
		return telegramChatId;
	}

	public void setTelegramChatId(String telegramChatId) {
		this.telegramChatId = telegramChatId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Autowired
	private Message message;

	@Override
	public void run() {

		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(binanceApiKey, binanceSecret);
		BinanceApiRestClient client = factory.newRestClient();
		client.ping();
		List<Candlestick> candlesticks = null;
		List<String> pairs = new ArrayList<String>();
		try {
			pairs = FileUtils.feedPairsFromTextFile(pairsListPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String pair : pairs) {
			candlesticks = client.getCandlestickBars(pair, CandlestickInterval.FIFTEEN_MINUTES);
			try {
				message.sendMessage(telegramApiToken, telegramChatId, RSI.calculate(candlesticks, pair));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
