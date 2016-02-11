package com.ChronicNinjaz.CoreDefence.Utils.Kits;


import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.ChronicNinjaz.CoreDefence.Managers.Game.KitManager;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;

public class Wizard implements KitManager{

	@Override 
	public void givePlayerKit(Player player, Team team) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		Bukkit.broadcastMessage("wizard");
		switch(team.getName()){
		case "RED":
			player.getInventory().setHelmet(new ItemStackBuilder(Material.LEATHER_HELMET).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setChestplate(new ItemStackBuilder(Material.LEATHER_CHESTPLATE).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setLeggings(new ItemStackBuilder(Material.LEATHER_LEGGINGS).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setBoots(new ItemStackBuilder(Material.LEATHER_BOOTS).withColor(Color.RED).withAmount(1).withName("&4Red Team").build());
			player.getInventory().setItem(0, new ItemStackBuilder(new ItemStack(Material.BLAZE_ROD)).withName("&4Wizard").withAmount(1).withLore("&7Right Click To Fire A Fireball!").build());
			player.closeInventory();
			break;
		case "BLUE":
			player.getInventory().setHelmet(new ItemStackBuilder(Material.LEATHER_HELMET).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setChestplate(new ItemStackBuilder(Material.LEATHER_CHESTPLATE).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setLeggings(new ItemStackBuilder(Material.LEATHER_LEGGINGS).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setBoots(new ItemStackBuilder(Material.LEATHER_BOOTS).withColor(Color.BLUE).withAmount(1).withName("&bBlue Team").build());
			player.getInventory().setItem(0, new ItemStackBuilder(new ItemStack(Material.BLAZE_ROD)).withName("&bWizard").withAmount(1).withLore("&7Right Click To Fire A Fireball!").build());
			player.closeInventory();
			break;
			default:
				System.out.println("Error while giving player kit!");
				break;
		}
	}

	@Override
	public void interact(PlayerInteractEvent event, Player player, ItemStack itemStack) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR){
				 Projectile fireball;
                 int fb_speed = 1;
                 final Vector fb_direction = player.getEyeLocation().getDirection().multiply(fb_speed);
                 player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 10, 1);
                 fireball = player.getWorld().spawn(player.getEyeLocation().add(fb_direction.getX(), fb_direction.getY(), fb_direction.getZ()), Fireball.class);
                 fireball.setFireTicks(0);
                 fireball.setShooter((ProjectileSource) player);
                 fireball.setVelocity(fb_direction);
		}
	}

}
