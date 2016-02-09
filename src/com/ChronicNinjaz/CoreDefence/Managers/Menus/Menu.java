package com.ChronicNinjaz.CoreDefence.Managers.Menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ChronicNinjaz.CoreDefence.Utils.Utils;

public abstract class Menu implements InventoryHolder{
	
	private int size;
	protected Inventory inventory;
	
	
	public Menu(String title, int size){
		inventory = Bukkit.createInventory(null, size, Utils.color(title));
		this.size = size;
	}
	
	public abstract void onClick(Player player, ItemStack itemstack);
	
	 protected String getFriendlyName(ItemStack itemStack) {
	        if (itemStack == null) {
	            return null;
	        }

	        ItemMeta itemMeta = itemStack.getItemMeta();

	        if (itemMeta == null || !itemMeta.hasDisplayName()) {
	            return null;
	        }

	        return ChatColor.stripColor(itemMeta.getDisplayName());
	    }
	
	public void openInventory(Player player){
		player.openInventory(inventory);
	}
	
	public void show(Player player){
		player.openInventory(inventory);
	}

	public int getSize() {
		return size;
	}
	
	public Inventory getInventory(){
		return inventory;
	}

}
