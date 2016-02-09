package com.ChronicNinjaz.CoreDefence.Managers.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;

public interface KitManager {

	void givePlayerKit(Player player, Team team);
	
	void interact(PlayerInteractEvent event, Player player, ItemStack itemStack);
}
