package dev.cheos.armorpointspp.impl;

import net.minecraft.world.effect.MobEffectInstance;
import dev.cheos.armorpointspp.core.adapter.IMobEffect;
import dev.cheos.armorpointspp.core.adapter.IMobEffectInstance;

public class FabricMobEffectInstance implements IMobEffectInstance {
    private final MobEffectInstance instance;

    public FabricMobEffectInstance(MobEffectInstance instance) {
        this.instance = instance;
    }

    @Override
    public int amplifier() {
        return instance.getAmplifier();
    }

    @Override
    public int duration() {
        return instance.getDuration();
    }

    @Override
    public IMobEffect effect() {
        return new FabricMobEffect(instance.getEffect().value());
    }
}
