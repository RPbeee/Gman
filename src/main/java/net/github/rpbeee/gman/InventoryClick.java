package net.github.rpbeee.gman;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("You clicked the inventory!");
    }

}
