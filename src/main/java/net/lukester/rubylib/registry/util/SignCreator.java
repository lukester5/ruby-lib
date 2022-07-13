package net.lukester.rubylib.registry.util;

import net.lukester.rubylib.mixin.SignTypeAccessor;
import net.minecraft.util.SignType;

public class SignCreator {
    
    public SignType createSign(String name) {
        return SignTypeAccessor.registerNew(SignTypeAccessor.newSignType(name));
    }
}
