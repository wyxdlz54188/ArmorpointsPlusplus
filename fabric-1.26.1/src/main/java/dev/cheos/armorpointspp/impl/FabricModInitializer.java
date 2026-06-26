package dev.cheos.armorpointspp.impl;

import net.fabricmc.api.ModInitializer;

public class FabricModInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        // Common initialization
        dev.cheos.armorpointspp.core.VanillaSprites.init();
    }
}
