package dev.cheos.armorpointspp.impl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import dev.cheos.armorpointspp.core.RenderContext;
import dev.cheos.armorpointspp.core.adapter.IMath;
import dev.cheos.armorpointspp.core.render.Components;

public class FabricClientModInitializer implements ClientModInitializer {
    private static final Identifier APPP_ID = Identifier.fromNamespaceAndPath("armorpointspp", "hud");
    private final FabricConfig config = new FabricConfig();

    @Override
    public void onInitializeClient() {
        HudElementRegistry.addLast(APPP_ID, new HudElement() {
            @Override
            public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.player == null || minecraft.options.hideGui) return;

                FabricPoseStack poseStack = new FabricPoseStack(guiGraphics.pose());
                FabricDataProvider data = new FabricDataProvider(minecraft, minecraft.player);
                FabricRenderer renderer = new FabricRenderer(minecraft.gui);
                renderer.setGuiGraphics(guiGraphics);

                RenderContext ctx = new RenderContext(
                    config, data,
                    new FabricEnchantmentHelper(),
                    IMath.INSTANCE,
                    renderer, poseStack,
                    FabricProfiler.empty(),
                    0, 0
                );

                if (!ctx.shouldRender()) return;

                renderer.setupAppp();

                Components.ARMOR.render(ctx);
                Components.HEALTH.render(ctx);
                Components.TOUGHNESS.render(ctx);
                Components.ABSORPTION.render(ctx);
                Components.ABSORPTION_OVER.render(ctx);
                Components.TOUGHNESS_OVER.render(ctx);
                Components.MAGIC_SHIELD.render(ctx);
                Components.RESISTANCE.render(ctx);
                Components.PROTECTION.render(ctx);
                Components.VANILLA_ARMOR.render(ctx);
                Components.ARMOR_TEXT.render(ctx);
                Components.HEALTH_TEXT.render(ctx);
                Components.TOUGHNESS_TEXT.render(ctx);
                Components.DEBUG.render(ctx);
                Components.DEBUG_TEXT.render(ctx);

                renderer.setupVanilla();
            }
        });
    }
}
