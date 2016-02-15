package com.ChronicNinjaz.CoreDefence.Managers.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.ScorebaordManager.ScorebaordManager;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class GameThread extends BukkitRunnable{

	private int count = 30;
	private static int lobbyCounter = 60;
	
	@Override
	public void run() {
		if(CoreDefence.getGameState() == GameState.STARTED){
			if((!(count <= 0))){
				// && CoreDefence.getManager().gameMeetsRequirements())
				count--;
				if(count % 60 <= 0 && count > 60){
					Message.GAME_TIMER_FINISHING.boardcastGameTimer(count);
				}
			}else{
				Message.GAME_HAS_ENDED.boardcastMessage();
				CoreDefence.getManager().end();
				count = 30;
				lobbyCounter = 60;
			}
		}else if(CoreDefence.getGameState() == GameState.WAITING){
			 if(lobbyCounter > 0){
				 lobbyCounter--;
			 }else{
				 if(CoreDefence.getManager().gameMeetsRequirements()){
					 if(!CoreDefence.getManager().isGameRunning()){
						 CoreDefence.getManager().start();
					 }else{
						 Bukkit.broadcastMessage("GAME RUNNING! ERROR");
					 }
				 }else {
					 Message.TIMER_ADD_SECONDS.boardcastReplaceMessage("%t", "30");
					 lobbyCounter = 30;
				 }
			 }
		}else {
			Bukkit.broadcastMessage("ERROR");
			cancel();
		}
		
		for(Player player: Bukkit.getOnlinePlayers()){
				ScorebaordManager.updateScorebaord(player);
		}
	}

	public static int getLobbyCounter() {
		return lobbyCounter;
	}

	public void setLobbyCounter(int lobbyCounter) {
		GameThread.lobbyCounter = lobbyCounter;
	}
	
	/*
	 * 	int totalSecs = ((60 * this.mins) + this.seconds);
		int fminutes =(totalSecs %3600)/60;
		int fseconds = totalSecs %60;

		String timeString = String.format("%02d:%02d", fminutes, fseconds);
		
		Bukkit.broadcastMessage(Utils.color("&5" + timeString));
	 */

}
