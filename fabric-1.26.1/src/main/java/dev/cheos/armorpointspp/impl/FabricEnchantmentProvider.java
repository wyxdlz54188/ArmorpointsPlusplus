package dev.cheos.armorpointspp.impl;

import dev.cheos.armorpointspp.core.adapter.IEnchantment;
import dev.cheos.armorpointspp.core.adapter.IEnchantmentProvider;

public class FabricEnchantmentProvider implements IEnchantmentProvider {
    @Override
    public IEnchantment protection() {
        return null;
    }

    @Override
    public IEnchantment blastProtection() {
        return null;
    }

    @Override
    public IEnchantment fireProtection() {
        return null;
    }

    @Override
    public IEnchantment projectileProtection() {
        return null;
    }
}
