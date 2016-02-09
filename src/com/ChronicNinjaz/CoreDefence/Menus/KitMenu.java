package com.ChronicNinjaz.CoreDefence.Menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ChronicNinjaz.CoreDefence.Managers.Enums.Kit;
import com.ChronicNinjaz.CoreDefence.Managers.Menus.Menu;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;

public class KitMenu extends Menu{

	public KitMenu(String title, int size) {
		super(title, size);
		inventory.setItem(0, new ItemStackBuilder(new ItemStack(Material.BOW)).withName("Archer").withAmount(1).withLore("&7Right Click To Chose This Kit!").build());
		inventory.setItem(1, new ItemStackBuilder(new ItemStack(Material.BLAZE_ROD)).withName("Wizard").withAmount(1).withLore("&7Right Click To Chose This Kit!").build());
	}

	@Override
	public void onClick(Player player, ItemStack itemstack) {
		String itemName = getFriendlyName(itemstack);
		
		 if(itemName == null){
			 return;
		 }
		 
		 for(Kit kit: Kit.values()){
			 if(kit.getName().equalsIgnoreCase(itemName)){
				 Players.getPlayer(player).setKit(kit);
				 kit.getKitManager().givePlayerKit(player, TeamManager.getTeam(player));
			 }
		 }
	}

}
