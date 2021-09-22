package net.github.rpbeee.gman;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Gman extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Gmanが開始しました");
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        File directorys;
        for (String key : config.getConfigurationSection("manuals").getKeys(false)) {
            directorys = new File(this.getDataFolder()+"/manuals/"+key+"/");
            if(!directorys.exists()) {
                directorys.mkdir();
            }
        }

        File mandir = new File(this.getDataFolder()+"/manuals/");
        if (!mandir.exists()) {
            mandir.mkdir();
        }
        Bukkit.getPluginCommand("man").setExecutor(new CmdExecutor());
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Gmanが終了しました");
    }
}
