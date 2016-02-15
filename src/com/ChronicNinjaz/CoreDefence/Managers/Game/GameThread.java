package com.ChronicNinjaz.CoreDefence.Managers.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.ScorebaordManager.ScorebaordManager;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class GameThread extends BukkitRunnable{

	@Override
	public void run() {
		
		if(CoreDefence.getGameState() == GameState.STARTED){
			int count = (CoreDefence.getManager().getMin() * 60) + CoreDefence.getManager().getSeconds();
			if((!(count <= 0) && CoreDefence.getManager().gameMeetsRequirements())){
				count--;
				if(count % 60 <= 0 && count > 60){
					Message.GAME_TIMER_FINISHING.boardcastGameTimer(count);
				}
			}else{
				Message.GAME_HAS_ENDED.boardcastMessage();
				CoreDefence.getManager().end();
				cancel();
			}
		}
		
		
		
		
		
		ScorebaordManager.setScore(ScorebaordManager.getScore() + 1);
		for(Player player: Bukkit.getOnlinePlayers()){
				ScorebaordManager.updateScorebaord(player, "game");
		}
	}

}
