package me.anying.server;

import java.util.Set;

import javax.ws.rs.core.Application;

import me.anying.server.controller.IndexController;
import me.anying.server.controller.MessageBoxController;
import me.anying.server.controller.StockPriceController;

import com.google.common.collect.Sets;

public class DemoApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = Sets.newHashSet();
		classes.add(IndexController.class);
		classes.add(MessageBoxController.class);
		classes.add(StockPriceController.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = Sets.newHashSet();
		return singletons;
	}
}
