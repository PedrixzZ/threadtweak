package com.github.getchoo.smoothboot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.github.getchoo.smoothboot.SmoothBoot;
import net.minecraft.util.Util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static boolean configChanged = false;

    public static SmoothBootConfig readConfig() {
        String configPath = System.getProperty("user.dir") + "/config/" + SmoothBoot.MOD_ID + ".json";
        SmoothBoot.LOGGER.debug("Config path: " + configPath);

        SmoothBootConfig config;
        try (FileReader reader = new FileReader(configPath)) {
            config = GSON.fromJson(reader, SmoothBootConfig.class);
            if (config == null) {
                throw new JsonParseException("Invalid config file format");
            }
            config.validate();
            SmoothBoot.LOGGER.debug("Config: " + config);
        } catch (IOException | JsonParseException e) {
            SmoothBoot.LOGGER.error("Error reading config: " + e.getMessage());
            // Create new default config
            config = new SmoothBootConfig();
            configChanged = true;
        }

        return config;
    }

    public static void saveConfig(SmoothBootConfig config) {
        String configPath = System.getProperty("user.dir") + "/config/" + SmoothBoot.MOD_ID + ".json";

        try (FileWriter writer = new FileWriter(configPath)) {
            GSON.toJson(config, writer);
            configChanged = false;
            SmoothBoot.LOGGER.debug("Config file saved");
        } catch (IOException e) {
            SmoothBoot.LOGGER.error("Error saving config: " + e.getMessage());
        }
    }

    public static void openConfigFile() {
        String configPath = System.getProperty("user.dir") + "/config/" + SmoothBoot.MOD_ID + ".json";
        Util.getOperatingSystem().open(new File(configPath));
    }

    public static boolean hasConfigChanged() {
        return configChanged;
    }
}
