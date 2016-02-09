package com.ChronicNinjaz.CoreDefence.Managers.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class StatsManager implements Listener{
	
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = player.getOpenInventory().getTopInventory();
        
        if(inv.getName().endsWith("Stats")){
        	event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onClick(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = player.getOpenInventory().getTopInventory();
        
        if(inv.getName().endsWith("Stats")){
        	event.setCancelled(true);
        }
    }
}
