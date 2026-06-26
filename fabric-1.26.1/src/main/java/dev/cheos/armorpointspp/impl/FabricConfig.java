package dev.cheos.armorpointspp.impl;

import java.io.*;
import java.util.*;

import com.google.gson.*;

import dev.cheos.armorpointspp.core.adapter.IConfig;

public class FabricConfig implements IConfig {
    private static final File CONFIG_FILE = new File("config/armorpointspp.json");
    private final Map<String, Object> values = new HashMap<>();
    private final Map<String, Object> defaults = new HashMap<>();
    private boolean loaded = false;

    private void ensureLoaded() {
        if (loaded) return;
        loaded = true;

        // register all option defaults
        for (BooleanOption opt : BooleanOption.values()) {
            defaults.put(opt.key(), opt.def());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def());
        }
        for (IntegerOption opt : IntegerOption.values()) {
            defaults.put(opt.key(), opt.def());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def());
        }
        for (HexOption opt : HexOption.values()) {
            defaults.put(opt.key(), opt.def());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def());
        }
        for (FloatOption opt : FloatOption.values()) {
            defaults.put(opt.key(), opt.def());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def());
        }
        for (StringOption opt : StringOption.values()) {
            defaults.put(opt.key(), opt.def());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def());
        }
        for (EnumOption<?> opt : EnumOption.values()) {
            defaults.put(opt.key(), opt.def().name());
            if (!values.containsKey(opt.key())) values.put(opt.key(), opt.def().name());
        }

        // load from file
        load();
    }

    private void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonElement elem = entry.getValue();
                if (elem.isJsonPrimitive()) {
                    JsonPrimitive prim = elem.getAsJsonPrimitive();
                    if (prim.isBoolean()) values.put(entry.getKey(), prim.getAsBoolean());
                    else if (prim.isNumber()) {
                        Number num = prim.getAsNumber();
                        if (defaults.get(entry.getKey()) instanceof Float)
                            values.put(entry.getKey(), num.floatValue());
                        else
                            values.put(entry.getKey(), num.intValue());
                    }
                    else if (prim.isString()) values.put(entry.getKey(), prim.getAsString());
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to load config: " + e.getMessage());
        }
    }

    private void save() {
        CONFIG_FILE.getParentFile().mkdirs();
        JsonObject json = new JsonObject();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object val = entry.getValue();
            if (val instanceof Boolean) json.addProperty(entry.getKey(), (Boolean) val);
            else if (val instanceof Integer) json.addProperty(entry.getKey(), (Integer) val);
            else if (val instanceof Float) json.addProperty(entry.getKey(), (Float) val);
            else if (val instanceof String) json.addProperty(entry.getKey(), (String) val);
        }
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(json, writer);
        } catch (Exception e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    @Override
    public boolean bool(Option<Boolean> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        return val instanceof Boolean ? (Boolean) val : key.def();
    }

    @Override
    public int hex(Option<Integer> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        return val instanceof Number ? ((Number) val).intValue() : key.def();
    }

    @Override
    public int num(Option<Integer> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        return val instanceof Number ? ((Number) val).intValue() : key.def();
    }

    @Override
    public float dec(Option<Float> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        return val instanceof Number ? ((Number) val).floatValue() : key.def();
    }

    @Override
    public String str(Option<String> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        return val instanceof String ? (String) val : key.def();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T enm(Option<T> key) {
        ensureLoaded();
        Object val = values.get(key.key());
        if (val instanceof String) {
            try {
                return Enum.valueOf(key.type(), (String) val);
            } catch (IllegalArgumentException ignored) {}
        }
        return key.def();
    }

    @Override
    public void invalidateAll() {
        loaded = false;
    }
}
