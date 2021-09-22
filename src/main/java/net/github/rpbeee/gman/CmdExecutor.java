package net.github.rpbeee.gman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class CmdExecutor implements CommandExecutor {

    private Inventory inv;

    String premsg = ChatColor.GREEN+"[Gman] "+ChatColor.WHITE;

    public void argserror(CommandSender sender, boolean ecode) {
        if(ecode){
            sender.sendMessage(premsg+"引数が多いか足りません！");
        } else {
            sender.sendMessage(premsg+"引数が間違っています！");
        }
        sender.sendMessage(premsg+"/man");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

        Plugin plugin = sender.getServer().getPluginManager().getPlugin("Gman");

        CustomYML customyml = new CustomYML(plugin, "struct.yml");
        FileConfiguration struct = customyml.getConfig();
        FileConfiguration config = plugin.getConfig();
        int manuals = config.getConfigurationSection("manuals").getKeys(false).size();
        String menuName[] = new String[manuals];
        int count = 0;
        for (String key : config.getConfigurationSection("manuals").getKeys(false)) {
            menuName[count] = key;
            count++;
        }

        //main gui code to here.
        Player player = (Player)sender;
        int i;
        this.inv = Bukkit.createInventory(null,54,"Gman");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        ItemStack menuItem = new ItemStack(Material.MAP);
        ItemMeta menuMeta = menuItem.getItemMeta();

        for (i=0; i<54; i++) {
            if (i==11) {
                menuMeta.setDisplayName(menuName[0]+"<M>");
                menuItem.setItemMeta(menuMeta);
            } else if (i==17) {
                menuMeta.setDisplayName(menuName[1]+"<M>");
                menuItem.setItemMeta(menuMeta);
            } else {
                this.inv.setItem(i, glass);
            }
        }
        player.openInventory(this.inv);
        sender.sendMessage(premsg+"新規インベントリを開きました");

        return true;
    }
}
