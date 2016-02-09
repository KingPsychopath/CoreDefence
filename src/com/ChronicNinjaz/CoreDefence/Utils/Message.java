package com.ChronicNinjaz.CoreDefence.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Message {
	
	
	STATE_WAITING("&6This Game Is Waiting To Start!"),
	STATE_STATING("&4Game Has Started!"),
	STATE_RESTARTING("&4Game Is Restarting!"),
	
	JOINED_RED_TEAM("&6&l%p &eHas Joined The &4&lRed &eTeam!"),
	JOINED_BLUE_TEAM("&6&l%p &eHas Joined The &b&lBlue &eTeam!"),
	
	PLAYER_QUIT_MESSAGE("&4%p &eHas Quit The Game!"),
	PLAYER_KICK_MESSAGE("&4%p &dHas Been Kicked!"),
	
	
	ERROR_TEAM_IS_NULL("&4&lError: Team Is Null!");
	
	private String message;
	
    Message(String message) {
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public String toString(){
    	return message;
    }
    
    public void boardcastMessage(){
    	Bukkit.broadcastMessage(message);
    }
    
    public void boardcastMessage(Player player){
    	Bukkit.broadcastMessage(message.replace("%p", player.getName()));
    }
    
    public void consoleMessage(){
    	System.out.println(message);
    }
    
    public void boardcastReplaceMessage(String replace, String with){
    	Bukkit.broadcastMessage(message.replace(replace, with));
    }

}
