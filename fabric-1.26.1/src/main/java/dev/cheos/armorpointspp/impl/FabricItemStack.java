package dev.cheos.armorpointspp.impl;

import net.minecraft.world.item.ItemStack;
import dev.cheos.armorpointspp.core.adapter.IItemStack;

public class FabricItemStack implements IItemStack {
    private final ItemStack stack;

    public FabricItemStack(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public Object getStack() {
        return stack;
    }
}
