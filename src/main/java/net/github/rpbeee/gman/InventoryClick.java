package net.github.rpbeee.gman;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
        if (!event.getInventory().getTitle().equals("Gman") || itemMeta == null) {
            return;
        }
        String itemName = itemMeta.getDisplayName();
        if (itemName==null) {
            return;
        }
        event.setCancelled(true);
        if (itemName.contains("<M>")) {
            //メニューをクリックしたことを検知
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("You clicked the inventory!");
            player.sendMessage("You clicked the slot No." + event.getSlot());
        }
    }

}
