package me.anying.cometd;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.anying.stock.StockPriceEmitter.Listener;
import me.anying.stock.StockPriceEmitter.Update;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ConfigurableServerChannel.Initializer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;

import com.google.common.collect.Maps;

@Service
public class StockPriceService implements Listener {
	@Inject
	private BayeuxServer bayeuxServer;
	@Session
	private LocalSession sender;

	@Override
	public void onUpdate(List<Update> updates) {
		for (Update update : updates) {
			String channelName = "/stock/" + update.getCode().toLowerCase();

			ServerChannel channel = getChannel(channelName);

			sendData(channel, update);
		}
	}

	private void sendData(ServerChannel channel, Update update) {
		Map<String, Object> data = Maps.newHashMap();

		data.put("code", update.getCode());
		data.put("oldPrice", update.getOldPrice());
		data.put("newPrice", update.getNewPrice());
		data.put("updateTime", update.getUpdateTime());

		channel.publish(sender, data, null);
	}

	private ServerChannel getChannel(String channelName) {
		bayeuxServer.createChannelIfAbsent(channelName, new Initializer() {
			public void configureChannel(ConfigurableServerChannel channel) {
				channel.setPersistent(true);
				channel.setLazy(true);
			}
		});

		return bayeuxServer.getChannel(channelName);
	}
}
