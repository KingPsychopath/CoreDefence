package com.ChronicNinjaz.CoreDefence.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class QuitEvent implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		event.setQuitMessage(null);
		Message.PLAYER_QUIT_MESSAGE.boardcastReplaceMessage("%p", event.getPlayer().getName());
		if(TeamManager.hasTeam(event.getPlayer())){
			TeamManager.removeFromTeam(event.getPlayer());
		}
	}

}
