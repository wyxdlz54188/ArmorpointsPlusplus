package dev.cheos.armorpointspp.impl;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.cheos.armorpointspp.core.adapter.IPoseStack;

public class FabricPoseStack implements IPoseStack {
    private final PoseStack poseStack;

    public FabricPoseStack(PoseStack poseStack) {
        this.poseStack = poseStack;
    }

    @Override
    public void pushPose() {
        poseStack.pushPose();
    }

    @Override
    public void popPose() {
        poseStack.popPose();
    }

    @Override
    public void scale(float x, float y, float z) {
        poseStack.scale(x, y, z);
    }

    @Override
    public void translate(double x, double y, double z) {
        poseStack.translate(x, y, z);
    }

    @Override
    public Object getPoseStack() {
        return poseStack;
    }
}
