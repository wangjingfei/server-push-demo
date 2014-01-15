package me.anying.server;

import org.apache.wink.guice.server.internal.GuiceDeploymentConfiguration;
import org.apache.wink.guice.server.internal.lifecycle.WinkGuiceModule;

import com.google.inject.Module;

public class DemoDeploymentConfiguration extends GuiceDeploymentConfiguration {
	@Override
	public Module[] createModules() {
		return new Module[] { new WinkGuiceModule() };
	}
}
