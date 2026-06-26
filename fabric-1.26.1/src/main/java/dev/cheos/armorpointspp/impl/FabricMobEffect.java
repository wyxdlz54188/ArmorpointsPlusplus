package dev.cheos.armorpointspp.impl;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import dev.cheos.armorpointspp.core.adapter.IMobEffect;

public class FabricMobEffect implements IMobEffect {
    private final MobEffect effect;

    public FabricMobEffect(MobEffect effect) {
        this.effect = effect;
    }

    @Override
    public Object getEffect() {
        return effect;
    }
}
