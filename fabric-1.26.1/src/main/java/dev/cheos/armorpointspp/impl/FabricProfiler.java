package dev.cheos.armorpointspp.impl;

import net.minecraft.util.profiling.ProfilerFiller;
import dev.cheos.armorpointspp.core.adapter.IProfiler;

public class FabricProfiler implements IProfiler {
    private final ProfilerFiller profiler;

    public FabricProfiler(ProfilerFiller profiler) {
        this.profiler = profiler;
    }

    @Override
    public void push(String s) {
        profiler.push(s);
    }

    @Override
    public void pop() {
        profiler.pop();
    }

    @Override
    public void popPush(String s) {
        profiler.popPush(s);
    }

    public static IProfiler empty() {
        return new IProfiler() {
            @Override public void push(String s) {}
            @Override public void pop() {}
            @Override public void popPush(String s) {}
        };
    }
}
