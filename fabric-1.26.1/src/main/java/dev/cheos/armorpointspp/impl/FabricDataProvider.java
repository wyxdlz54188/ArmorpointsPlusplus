package dev.cheos.armorpointspp.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import dev.cheos.armorpointspp.core.adapter.IDataProvider;
import dev.cheos.armorpointspp.core.adapter.IEffectProvider;
import dev.cheos.armorpointspp.core.adapter.IEnchantmentProvider;
import dev.cheos.armorpointspp.core.adapter.IItemStack;
import dev.cheos.armorpointspp.core.adapter.IMobEffect;
import dev.cheos.armorpointspp.core.adapter.IMobEffectInstance;

public class FabricDataProvider implements IDataProvider {
    private final Minecraft minecraft;
    private final Player player;

    public FabricDataProvider(Minecraft minecraft, Player player) {
        this.minecraft = minecraft;
        this.player = player;
    }

    @Override
    public int armor() {
        return player.getArmorValue();
    }

    @Override
    public int maxArmor() {
        return (int) player.getAttributeValue(Attributes.ARMOR);
    }

    @Override
    public double toughness() {
        return player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
    }

    @Override
    public double maxToughness() {
        return player.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
    }

    @Override
    public int guiTicks() {
        return minecraft.gui.getGuiTicks();
    }

    @Override
    public int invulnTime() {
        return player.invulnerableTime;
    }

    @Override
    public long millis() {
        return System.currentTimeMillis();
    }

    @Override
    public float health() {
        return player.getHealth();
    }

    @Override
    public float maxHealth() {
        return player.getMaxHealth();
    }

    @Override
    public float absorption() {
        return player.getAbsorptionAmount();
    }

    @Override
    public float percentFrozen() {
        return player.getPercentFrozen();
    }

    @Override
    public IEffectProvider effects() {
        return new FabricEffectProvider();
    }

    @Override
    public IEnchantmentProvider enchantments() {
        return new FabricEnchantmentProvider();
    }

    @Override
    public boolean hidden() {
        return player.isInvisible();
    }

    @Override
    public boolean isPotionCoreLoaded() {
        return false;
    }

    @Override
    public boolean isFullyFrozen() {
        return player.isFullyFrozen();
    }

    @Override
    public boolean isHardcore() {
        return minecraft.level != null && minecraft.level.getLevelData().isHardcore();
    }

    @Override
    public boolean isEffectActive(String id) {
        return player.getActiveEffectsMap().keySet().stream()
                .anyMatch(effect -> effect.getRegisteredName().equals(id));
    }

    @Override
    public boolean isEffectActive(IMobEffect effect) {
        MobEffect mcEffect = (MobEffect) effect.getEffect();
        return player.hasEffect(Holder.direct(mcEffect));
    }

    @Override
    public boolean shouldDrawSurvivalElements() {
        return minecraft.gameMode != null && minecraft.gameMode.canHurtPlayer();
    }

    @Override
    public IMobEffectInstance getActiveEffect(IMobEffect effect) {
        MobEffect mcEffect = (MobEffect) effect.getEffect();
        net.minecraft.world.effect.MobEffectInstance instance =
                player.getEffect(Holder.direct(mcEffect));
        return instance != null ? new FabricMobEffectInstance(instance) : null;
    }

    @Override
    public IPotionCore potionCore() {
        return new IPotionCore() {
            @Override
            public double magicShield() { return 0; }
            @Override
            public double resistance() { return 0; }
        };
    }

    @Override
    public Iterable<IItemStack> armorSlots() {
        java.util.List<IItemStack> slots = new java.util.ArrayList<>(4);
        for (EquipmentSlot slot : new EquipmentSlot[] {
                EquipmentSlot.FEET, EquipmentSlot.LEGS,
                EquipmentSlot.CHEST, EquipmentSlot.HEAD}) {
            ItemStack stack = player.getItemBySlot(slot);
            if (!stack.isEmpty()) {
                slots.add(new FabricItemStack(stack));
            }
        }
        return slots;
    }
}
