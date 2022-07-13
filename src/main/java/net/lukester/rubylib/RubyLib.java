package net.lukester.rubylib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.lukester.rubylib.registry.BaseRegistry;

public class RubyLib implements ModInitializer {

	public static final String MOD_ID = "rubylib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BaseRegistry.register();
	}
	


}
