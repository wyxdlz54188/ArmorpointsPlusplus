package dev.cheos.armorpointspp.impl;

import dev.cheos.armorpointspp.core.adapter.IEnchantment;
import dev.cheos.armorpointspp.core.adapter.IEnchantmentHelper;
import dev.cheos.armorpointspp.core.adapter.IItemStack;

public class FabricEnchantmentHelper implements IEnchantmentHelper {
    @Override
    public int getLevel(IEnchantment ench, IItemStack stack) {
        return 0;
    }
}
