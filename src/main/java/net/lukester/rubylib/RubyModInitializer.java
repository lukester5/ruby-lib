package net.lukester.rubylib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class RubyModInitializer implements ModInitializer {
    public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        
	}

    public String getModid() {
        return MOD_ID;
    }

    public Identifier getLocation(String id) {
        return new Identifier(getModid() + id);
    }
}
