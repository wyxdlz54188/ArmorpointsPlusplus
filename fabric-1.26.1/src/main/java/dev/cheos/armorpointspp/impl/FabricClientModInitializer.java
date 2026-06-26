package dev.cheos.armorpointspp.impl;

import net.fabricmc.api.ClientModInitializer;

/**
 * Client initializer for ArmorPointsPP on Fabric 26.1.
 *
 * Note: HudRenderCallback was removed in Fabric API 0.153.0+26.1.2.
 * A Mixin into Gui.extractRenderState() is needed for HUD rendering.
 * See the 26.1 migration guide for details on the new rendering pipeline.
 */
public class FabricClientModInitializer implements ClientModInitializer {
    private final FabricConfig config = new FabricConfig();

    @Override
    public void onInitializeClient() {
        // TODO: Implement HUD rendering via Mixin into Gui.extractRenderState()
        // In 26.1, the rendering pipeline uses GuiGraphicsExtractor instead of GuiGraphics.
        // A Mixin targeting Gui.extractRenderState() or the rendering pipeline
        // is required to inject custom HUD elements.
    }
}
