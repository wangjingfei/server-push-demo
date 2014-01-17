package me.anying.stock;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class StockPriceEmitter implements Runnable {
	private final ScheduledExecutorService scheduler = Executors
			.newSingleThreadScheduledExecutor();
	private final List<Listener> listeners = Lists.newCopyOnWriteArrayList();

	private final Map<String, Double> prices = Maps.newHashMap();
	private final List<String> codes = Lists.newArrayList("ORCL", "MSFT",
			"GOOG", "YHOO", "FB");

	public StockPriceEmitter() {
		prices.put("ORCL", 29.94);
		prices.put("MSFT", 27.10);
		prices.put("GOOG", 655.37);
		prices.put("YHOO", 17.82);
		prices.put("FB", 21.33);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void start() {
		run();
	}

	public void stop() {
		scheduler.shutdownNow();
	}

	@Override
	public void run() {
		List<Update> updates = getPriceUpdates();
		triggerListeners(updates);

		scheduler.schedule(this, new Random().nextInt(1000),
				TimeUnit.MILLISECONDS);
	}

	public List<Update> getPriceUpdates() {
		Random random = new Random();
		List<Update> updates = Lists.newArrayList();

		int count = random.nextInt(codes.size());
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(codes.size());
			String code = codes.get(index);
			double oldPrice = prices.get(code);

			double offset = random.nextDouble() * 0.1 * oldPrice;
			offset = random.nextBoolean() ? offset : -offset;
			double newPrice = oldPrice + offset;

			prices.put(code, newPrice);

			updates.add(new Update(code, oldPrice, newPrice, new Date()));
		}

		return updates;
	}

	public void triggerListeners(List<Update> updates) {
		for (Listener listener : listeners) {
			listener.onUpdate(updates);
		}
	}

	public static class Update {
		private final String code;
		private final double oldPrice;
		private final double newPrice;
		private final Date updateTime;

		private Update(String code, double oldPrice, double newPrice,
				Date updateTime) {
			this.code = code;
			this.oldPrice = oldPrice;
			this.newPrice = newPrice;
			this.updateTime = updateTime;
		}

		public String getCode() {
			return code;
		}

		public double getOldPrice() {
			return oldPrice;
		}

		public double getNewPrice() {
			return newPrice;
		}

		public Date getUpdateTime() {
			return updateTime;
		}
	}

	public interface Listener {
		void onUpdate(List<Update> updates);
	}
}
