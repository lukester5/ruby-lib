package net.lukester.rubylib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class RubyModInitializer implements ModInitializer {
    public final static String MOD_ID = "modid";

	@Override
	public void onInitialize() {}

    public static String getModid() {
        return MOD_ID;
    }

    public static Identifier getLocation(String id) {
        return new Identifier(getModid() + id);
    }
}
