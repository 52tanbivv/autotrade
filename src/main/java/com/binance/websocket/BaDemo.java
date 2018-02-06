package com.binance.websocket;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.market.OrderBookEntry;

public class BaDemo {

	public static void main(String[] args) {
		BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
		// client.onAggTradeEvent("ethbtc", (AggTradeEvent response) -> {
		// //System.out.println("价格：" + response.getPrice() + " 成交量：" +
		// response.getQuantity());
		// });

		client.onDepthEvent("ethbtc", (DepthEvent response) -> {
			System.out.println(" -- 报价变化 --" + response.getAsks().size());

			final int size = response.getAsks().size() < 5 ? response.getAsks().size() : 5;
			for (int i = 0; i < size; i++) {
				final OrderBookEntry entry = response.getAsks().get(i);
				System.out.println("卖" + i + " 价格" + entry.getPrice() + " -- " + entry.getQty());
			}
		});
	}
}
