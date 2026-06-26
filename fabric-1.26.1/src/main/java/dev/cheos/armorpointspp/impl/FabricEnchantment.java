package dev.cheos.armorpointspp.impl;

import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import dev.cheos.armorpointspp.core.adapter.IEnchantment;

public class FabricEnchantment implements IEnchantment {
    private final Enchantment enchantment;

    public FabricEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public Object getEnchantment() {
        return enchantment;
    }
}
