package net.github.rpbeee.gman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CmdExecutor implements CommandExecutor {

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

        //main gui code to here.
        Player player = (Player)sender;
        int i;
        Inventory inv;
        inv = Bukkit.createInventory(null,54,ChatColor.DARK_PURPLE+"Gman - GUI Manual");

        for (i=0; i<54; i++) {
            inv.setItem(i,new ItemStack(Material.STAINED_GLASS_PANE));
        }
        player.openInventory(inv);
        sender.sendMessage(premsg+"新規インベントリを開きました");

        return true;
    }
}
