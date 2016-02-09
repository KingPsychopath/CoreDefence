package com.ChronicNinjaz.CoreDefence.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.Kit;
import com.ChronicNinjaz.CoreDefence.Menus.StatsMenu;

public class InteractEvent implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		ItemStack item = event.getItem();
		
		if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
			String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
			
			switch(name){
			case "Choice Kit":
				CoreDefence.getMenuManager().getMenu("kit").show(player);
				break;
			case "Stats":
				new StatsMenu(player).show(player);
				break;
				default:
					for(Kit k: Kit.values()){
						if(k.getName().equals(name)){
							k.getKitManager().interact(event, player, item);
							break;
						}
					}
					break;
			}
		}
	}
}
