package com.ChronicNinjaz.CoreDefence.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

	  private Utils() {}

	    public static String color(String msg) {
	        return ChatColor.translateAlternateColorCodes('&', msg);
	    }

	    public static int getNumber(Player player, String input) {
	        try {
	            return Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            if (player != null) {
	               // Messages.BAD_INPUT.send(player, input);
	            }

	            return -1;
	        }
	    }

}
