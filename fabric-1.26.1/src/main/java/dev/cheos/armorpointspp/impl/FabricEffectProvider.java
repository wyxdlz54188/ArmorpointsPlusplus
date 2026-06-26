package dev.cheos.armorpointspp.impl;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import dev.cheos.armorpointspp.core.adapter.IEffectProvider;
import dev.cheos.armorpointspp.core.adapter.IMobEffect;

public class FabricEffectProvider implements IEffectProvider {
    @Override
    public IMobEffect resistance() {
        return new FabricMobEffect(getEffect("resistance"));
    }

    @Override
    public IMobEffect regeneration() {
        return new FabricMobEffect(getEffect("regeneration"));
    }

    @Override
    public IMobEffect poison() {
        return new FabricMobEffect(getEffect("poison"));
    }

    @Override
    public IMobEffect wither() {
        return new FabricMobEffect(getEffect("wither"));
    }

    private MobEffect getEffect(String name) {
        return BuiltInRegistries.MOB_EFFECT.get(Identifier.withDefaultNamespace(name))
                .orElseThrow().value();
    }
}
