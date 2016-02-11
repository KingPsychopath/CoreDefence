package com.ChronicNinjaz.CoreDefence.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackBuilder {
	 private ItemStack ITEM_STACK;
	 
	    public ItemStackBuilder() { }

	    public ItemStackBuilder(Material mat) {
	        this.ITEM_STACK = new ItemStack(mat);
	    }

	    public ItemStackBuilder(ItemStack item) {
	        this.ITEM_STACK = item;
	    }

	    public ItemStackBuilder withAmount(int amount) {
	        ITEM_STACK.setAmount(amount);
	        return this;
	    }

	    public ItemStackBuilder withName(String name) {
	        final ItemMeta meta = ITEM_STACK.getItemMeta();
	        meta.setDisplayName(Utils.color(name));
	        ITEM_STACK.setItemMeta(meta);
	        return this;
	    }

	    public ItemStackBuilder withLore(String name) {
	        final ItemMeta meta = ITEM_STACK.getItemMeta();
	        List<String> lore = meta.getLore();
	        if (lore == null) {
	            lore = new ArrayList<>();
	        }
	        lore.add(Utils.color(name));
	        meta.setLore(lore);
	        ITEM_STACK.setItemMeta(meta);
	        return this;
	    }

	    public ItemStackBuilder withDurability(int durability) {
	        ITEM_STACK.setDurability((short) durability);
	        return this;
	    }

	    public ItemStackBuilder withData(int data) {
	        ITEM_STACK.setDurability((short) data);
	        return this;
	    }

	    public ItemStackBuilder withEnchantment(Enchantment enchantment, final int level) {
	        ITEM_STACK.addUnsafeEnchantment(enchantment, level);
	        return this;
	    }

	    public ItemStackBuilder withEnchantment(Enchantment enchantment) {
	        ITEM_STACK.addUnsafeEnchantment(enchantment, 1);
	        return this;
	    }

	    public ItemStackBuilder withType(Material material) {
	        ITEM_STACK.setType(material);
	        return this;
	    }

	    public ItemStackBuilder clearLore() {
	        final ItemMeta meta = ITEM_STACK.getItemMeta();
	        meta.setLore(new ArrayList<String>());
	        ITEM_STACK.setItemMeta(meta);
	        return this;
	    }

	    public ItemStackBuilder clearEnchantments() {
	        for (Enchantment enchantment : ITEM_STACK.getEnchantments().keySet()) {
	            ITEM_STACK.removeEnchantment(enchantment);
	        }
	        return this;
	    }

	    public ItemStackBuilder withColor(Color color) {
	        Material type = ITEM_STACK.getType();
	        if (type == Material.LEATHER_BOOTS || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_HELMET || type == Material.LEATHER_LEGGINGS) {
	            LeatherArmorMeta meta = (LeatherArmorMeta) ITEM_STACK.getItemMeta();
	            meta.setColor(color);
	            ITEM_STACK.setItemMeta(meta);
	            return this;
	        } else {
	            throw new IllegalArgumentException("withColor is only applicable for leather armor!");
	        }
	    }
	    
	    public ItemStackBuilder buildSkull(Player player){
	    	ITEM_STACK = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
	    	SkullMeta meta = (SkullMeta) ITEM_STACK.getItemMeta();
	    	if(player != null){
	    		meta.setOwner(player.getName());
	    		meta.setDisplayName(player.getName() + " Stats");
	    		ITEM_STACK.setItemMeta(meta);
	    	}
	    	return this;
	    }
	    
	    public ItemStack build() {
	        return ITEM_STACK;
	    }

}
