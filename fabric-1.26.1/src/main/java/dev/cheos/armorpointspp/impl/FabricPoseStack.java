package dev.cheos.armorpointspp.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix3x2fStack;

import dev.cheos.armorpointspp.core.adapter.IPoseStack;

public class FabricPoseStack implements IPoseStack {
    private final PoseStack poseStack;
    private final Matrix3x2fStack matrixStack;

    public FabricPoseStack(PoseStack poseStack) {
        this.poseStack = poseStack;
        this.matrixStack = null;
    }

    public FabricPoseStack(Matrix3x2fStack matrixStack) {
        this.matrixStack = matrixStack;
        this.poseStack = null;
    }

    @Override
    public void pushPose() {
        if (poseStack != null) poseStack.pushPose();
        else if (matrixStack != null) matrixStack.pushMatrix();
    }

    @Override
    public void popPose() {
        if (poseStack != null) poseStack.popPose();
        else if (matrixStack != null) matrixStack.popMatrix();
    }

    @Override
    public void scale(float x, float y, float z) {
        if (poseStack != null) poseStack.scale(x, y, z);
        else if (matrixStack != null) matrixStack.scale(x, y);
    }

    @Override
    public void translate(double x, double y, double z) {
        if (poseStack != null) poseStack.translate(x, y, z);
        else if (matrixStack != null) matrixStack.translate((float) x, (float) y);
    }

    @Override
    public Object getPoseStack() {
        if (poseStack != null) return poseStack;
        return matrixStack;
    }
}
