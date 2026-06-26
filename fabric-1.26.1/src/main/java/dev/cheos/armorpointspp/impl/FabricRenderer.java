package dev.cheos.armorpointspp.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderSystem;
import net.minecraft.resources.Identifier;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix4f;

import dev.cheos.armorpointspp.core.SpriteInfo;
import dev.cheos.armorpointspp.core.adapter.IPoseStack;
import dev.cheos.armorpointspp.core.adapter.IRenderer;
import dev.cheos.armorpointspp.core.texture.ITextureSheet;

public class FabricRenderer implements IRenderer {
    private final Gui gui;
    private GuiGraphicsExtractor guiGraphics;
    private Identifier currentTexture;
    private float currentRed = 1.0f;
    private float currentGreen = 1.0f;
    private float currentBlue = 1.0f;

    public FabricRenderer(Gui gui) {
        this.gui = gui;
    }

    public void setGuiGraphics(GuiGraphicsExtractor guiGraphics) {
        this.guiGraphics = guiGraphics;
    }

    @Override
    public void blit(IPoseStack poseStack, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
        if (this.guiGraphics != null && this.currentTexture != null) {
            RenderSystem.setShaderColor(currentRed, currentGreen, currentBlue, 1.0f);
            RenderSystem.setShaderTexture(0, this.currentTexture);
            
            this.guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                this.currentTexture,
                x, y, u, v, width, height, texWidth, texHeight
            );
            
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public void blit(IPoseStack poseStack, int x, int y, float u, float v, int width, int height) {
        blit(poseStack, x, y, u, v, width, height, 256, 256);
    }

    @Override
    public void blitSprite(IPoseStack poseStack, int x, int y, int width, int height, SpriteInfo sprite) {
        if (this.guiGraphics != null) {
            Identifier tex = Identifier.tryParse(sprite.location());
            if (tex != null) {
                RenderSystem.setShaderColor(currentRed, currentGreen, currentBlue, 1.0f);
                RenderSystem.setShaderTexture(0, tex);
                this.guiGraphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    tex,
                    x, y, sprite.u(), sprite.v(),
                    width, height, 256, 256
                );
                RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public void blitSprite(IPoseStack poseStack, int x, int y, int width, int height, SpriteInfo sprite, int uOffset, int vOffset, int spriteWidth, int spriteHeight) {
        blit(poseStack, x, y, sprite.u() + uOffset, sprite.v() + vOffset, width, height, spriteWidth, spriteHeight);
    }

    @Override
    public void blitM(IPoseStack poseStack, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight) {
        if (this.guiGraphics != null && this.currentTexture != null) {
            RenderSystem.setShaderColor(currentRed, currentGreen, currentBlue, 1.0f);
            RenderSystem.setShaderTexture(0, this.currentTexture);
            this.guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                this.currentTexture,
                x + width, y, -(u + width), v, width, height, texWidth, texHeight
            );
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public void blitM(IPoseStack poseStack, int x, int y, float u, float v, int width, int height) {
        blitM(poseStack, x, y, u, v, width, height, 256, 256);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        this.currentRed = r;
        this.currentGreen = g;
        this.currentBlue = b;
        RenderSystem.setShaderColor(r, g, b, a);
    }

    @Override
    public void setupAppp() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void setupTexture(ITextureSheet texSheet) {
        currentTexture = Identifier.tryParse(texSheet.texLocation());
        if (currentTexture != null) {
            RenderSystem.setShaderTexture(0, currentTexture);
        }
    }

    @Override
    public void setupVanilla() {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
    }

    @Override
    public void text(IPoseStack poseStack, String text, float x, float y, int color, TextRenderType renderType) {
        text(poseStack, text, x, y, color, renderType, false);
    }

    @Override
    public void text(IPoseStack poseStack, String text, float x, float y, int color, TextRenderType renderType, boolean shadow) {
        Object ps = poseStack.getPoseStack();
        
        if (ps instanceof PoseStack) {
            PoseStack stack = (PoseStack) ps;
            gui.getFont().drawInBatch(text, x, y, color, shadow,
                    stack.last().pose(), Minecraft.getI