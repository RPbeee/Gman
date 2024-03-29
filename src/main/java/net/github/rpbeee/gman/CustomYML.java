package net.github.rpbeee.gman;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

public class CustomYML {

    private FileConfiguration config = null;
    private final File configFile;
    private final String file;
    private final Plugin plugin;

    public CustomYML(Plugin plugin) {
        this(plugin, "config.yml");
    }

    public CustomYML(Plugin plugin, String filename) {
        this.plugin = plugin;
        this.file = filename;
        configFile = new File(plugin.getDataFolder(), file);
    }

    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            plugin.saveResource(file, false);
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);

        final InputStream defConfigStream = plugin.getResource(file);
        if(defConfigStream == null) {
            return;
        }

        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
    }

    public FileConfiguration getConfig() {
        if(config == null) {
            reloadConfig();
        }
        return config;
    }

    public void saveConfig() {
        SaveScheduler scheduler = new SaveScheduler(config, plugin, configFile);
        scheduler.runTaskAsynchronously(plugin);
    }
}
