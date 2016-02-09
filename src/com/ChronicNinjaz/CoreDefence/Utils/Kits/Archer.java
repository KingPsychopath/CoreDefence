package com.ChronicNinjaz.CoreDefence.Utils.Kits;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ChronicNinjaz.CoreDefence.Managers.Game.KitManager;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;

public class Archer implements KitManager{

	@Override
	public void givePlayerKit(Player player, Team t) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		switch(t.getName()){
		case "RED":
			player.getInventory().setHelmet(new ItemStackBuilder(Material.LEATHER_HELMET).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setChestplate(new ItemStackBuilder(Material.LEATHER_CHESTPLATE).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setLeggings(new ItemStackBuilder(Material.LEATHER_LEGGINGS).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setBoots(new ItemStackBuilder(Material.LEATHER_BOOTS).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			
			player.getInventory().setItem(0, new ItemStackBuilder(Material.BOW).withAmount(1).withName("&4Bow").withEnchantment(Enchantment.ARROW_DAMAGE, 2).withEnchantment(Enchantment.ARROW_INFINITE).build());
			player.getInventory().setItem(27, new ItemStackBuilder(Material.ARROW).withAmount(1).withName("&4Arrow").build());
			player.closeInventory();
			break;
		case "BLUE":
			player.getInventory().setHelmet(new ItemStackBuilder(Material.LEATHER_HELMET).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setChestplate(new ItemStackBuilder(Material.LEATHER_CHESTPLATE).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setLeggings(new ItemStackBuilder(Material.LEATHER_LEGGINGS).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setBoots(new ItemStackBuilder(Material.LEATHER_BOOTS).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			
			player.getInventory().setItem(0, new ItemStackBuilder(Material.BOW).withAmount(1).withName("&bBow").withEnchantment(Enchantment.ARROW_DAMAGE, 2).withEnchantment(Enchantment.ARROW_INFINITE).build());
			player.getInventory().setItem(27, new ItemStackBuilder(Material.ARROW).withAmount(1).withName("&bArrow").build());
			player.closeInventory();
			break;
			default:
				System.out.println("Error while giving player kit!");
				break;
		}
	}

	@Override
	public void interact(PlayerInteractEvent event, Player player,ItemStack itemStack) {}

}
