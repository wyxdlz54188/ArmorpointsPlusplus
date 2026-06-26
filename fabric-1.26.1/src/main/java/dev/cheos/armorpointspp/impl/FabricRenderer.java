package dev.cheos.armorpointspp.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.Identifier;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.cheos.armorpointspp.core.SpriteInfo;
import dev.cheos.armorpointspp.core.adapter.IPoseStack;
import dev.cheos.armorpointspp.core.adapter.IRenderer;
import dev.cheos.armorpointspp.core.texture.ITextureSheet;

public class FabricRenderer implements IRenderer {
    private final Gui gui;
    private Identifier currentTexture;

    public FabricRenderer(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void blit(IPoseStack poseStack, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
        // TODO: implement with GuiGraphicsExtractor in 26.1
    }

    @Override
    public void blit(IPoseStack poseStack, int x, int y, float u, float v, int width, int height) {
        blit(poseStack, x, y, u, v, width, height, 256, 256);
    }

    @Override
    public void blitSprite(IPoseStack poseStack, int x, int y, int width, int height, SpriteInfo sprite) {}

    @Override
    public void blitSprite(IPoseStack poseStack, int x, int y, int width, int height, SpriteInfo sprite, int uOffset, int vOffset, int spriteWidth, int spriteHeight) {}

    @Override
    public void blitM(IPoseStack poseStack, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
        blit(poseStack, x, y, u, v, width, height, texWidth, texHeight);
    }

    @Override
    public void blitM(IPoseStack poseStack, int x, int y, float u, float v, int width, int height) {
        blit(poseStack, x, y, u, v, width, height);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        // TODO: color manipulation in 26.1
    }

    @Override
    public void setupAppp() {}

    @Override
    public void setupTexture(ITextureSheet texSheet) {
        currentTexture = Identifier.tryParse(texSheet.texLocation());
    }

    @Override
    public void setupVanilla() {}

    @Override
    public void text(IPoseStack poseStack, String text, float x, float y, int color, TextRenderType renderType) {
        text(poseStack, text, x, y, color, renderType, false);
    }

    @Override
    public void text(IPoseStack poseStack, String text, float x, float y, int color, TextRenderType renderType, boolean shadow) {
        PoseStack ps = (PoseStack) poseStack.getPoseStack();
        gui.getFont().drawInBatch(text, x, y, color, shadow,
                ps.last().pose(), Minecraft.getInstance().renderBuffers().bufferSource(),
                Font.DisplayMode.NORMAL, 0, 15728880);
    }

    @Override
    public int width(Object... objs) {
        if (objs == null || objs.length == 0) return 0;
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(String.valueOf(obj));
        }
        return gui.getFont().width(sb.toString());
    }
}
