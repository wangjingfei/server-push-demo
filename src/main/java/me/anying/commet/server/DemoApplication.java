package me.anying.commet.server;

import java.util.Set;

import javax.ws.rs.core.Application;

import org.apache.wink.server.internal.providers.entity.html.HtmlProvider;

import me.anying.commet.server.controller.IndexController;

import com.google.common.collect.Sets;

public class DemoApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = Sets.newHashSet();
		classes.add(IndexController.class);
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = Sets.newHashSet();
		singletons.add(new HtmlProvider());
		return singletons;
	}
}
