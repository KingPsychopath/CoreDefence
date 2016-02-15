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
	PLAYER_CHOICE_KIT_ARCHER("&6&lYou Have Chosen The &e&lArcher &6&lKit! "),
	PLAYER_CHOICE_KIT_WIZARD("&6&lYou Have Chosen The &e&lWizard &6&lKit! "),
	PLAYER_NO_PERMISSION("&4You do not have permission to use this command!"),
	PLAYER_WRONG_USEAGE("&4Wrong usage of the command!"),
	
	ADMIN_SUCCSESSFULLY_SET_LOCATION("&bYou have &3&lSuccsessfully &bset &3&l%location%&b!"),
	
	
	ERROR_TEAM_IS_NULL("&4&lError: Team Is Null!"),
	
	GAME_TIMER_FINISHING("&6Game will end in %t."),
	GAME_TIMER_LOBBY("&6Game Going InTo Prosess In %t"),
	GAME_TIMER_STARTING("&6Game Starting In %t"),
	GAME_TIMER_STARTED("&6Game Has Started!"),
	GAME_HAS_ENDED("&6Game Has Ended!"),
	GAME_ERROR_NOT_ENOUGH_PLAYERS("&4Not Enough Players To Start The Game!"),
	
	TEAM_IS_NULL("&4Team >[ERROR]< Is Null!"),
	
	TIMER_ADD_SECONDS("&6Added %t Seconds To Countdown!");
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
    
    public String replace(String replace, String with){
    	return message.replace(replace, with);
    }
    
    public void consoleReplaceMessage(String replace, String with){
    	System.out.println(message.replace(replace, with));
    }
    
    public void boardcastReplaceMessage(String replace, String with){
    	Bukkit.broadcastMessage(message.replace(replace, with));
    }
    
    public void boardcastGameTimer(int count){
		int totalSecs = count;
		int fminutes =(totalSecs %3600)/60;
		int fseconds = totalSecs %60;

		String timeString = String.format("%02d:%02d", fminutes, fseconds);
		
		Bukkit.broadcastMessage(message.replace("%t", timeString));
    }

}
