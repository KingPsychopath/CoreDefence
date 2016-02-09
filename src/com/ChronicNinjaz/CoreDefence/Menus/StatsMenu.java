package com.ChronicNinjaz.CoreDefence.Menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;
import com.ChronicNinjaz.CoreDefence.Utils.Utils;

public class StatsMenu implements Listener{
	
	private Player player;
	private static Inventory inventory;
	
	private int size = 36;
	
	public StatsMenu(Player player) {
		this.setPlayer(player);
		inventory = Bukkit.createInventory(null, getSize(), Utils.color("&6[&e" + player.getName() + "&6] Stats"));
		Players profile = Players.getPlayer(getPlayer());
		inventory.setItem(11, new ItemStackBuilder(Material.DIAMOND_SWORD).withAmount(1).withName(getPlayer().getName()).withLore("&6Kills: " + profile.getKills()).build());
		inventory.setItem(13, new ItemStackBuilder(Material.ANVIL).withAmount(1).withName(getPlayer().getName()).withLore("&6Deaths: " + profile.getDeaths()).build());
		inventory.setItem(15, new ItemStackBuilder(Material.EMERALD).withAmount(1).withName(getPlayer().getName()).withLore("&6Gems: " + profile.getGems()).build());
		inventory.setItem(21, new ItemStackBuilder(Material.BOOK).withAmount(1).withName(getPlayer().getName()).withLore("&6Games Played: " + profile.getGame_played()).build());
		inventory.setItem(23, new ItemStackBuilder(Material.OBSIDIAN).withAmount(1).withName(getPlayer().getName()).withLore("&6Cores Captured: " + profile.getCores_captured()).build());
	}
	
	public void show(Player player){
		player.openInventory(inventory);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getSize() {
		return size;
	}
}
