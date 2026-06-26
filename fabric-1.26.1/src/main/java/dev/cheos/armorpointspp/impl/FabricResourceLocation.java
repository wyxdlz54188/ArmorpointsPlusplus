package dev.cheos.armorpointspp.impl;

import net.minecraft.resources.Identifier;
import dev.cheos.armorpointspp.core.adapter.IResourceLocation;

public class FabricResourceLocation implements IResourceLocation {
    private final Identifier location;

    public FabricResourceLocation(Identifier location) {
        this.location = location;
    }

    @Override
    public Object getResourceLocation() {
        return location;
    }
}
