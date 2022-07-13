package net.lukester.rubylib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public abstract class RubyModInitializer implements ModInitializer {
    public final String MOD_ID;

    public RubyModInitializer(String modid) {
        this.MOD_ID = modid;
    }

	@Override
	public void onInitialize() {}

    public String getModid() {
        return this.MOD_ID;
    }

    public Identifier getLocation(String id) {
        return new Identifier(getModid() + id);
    }
}
