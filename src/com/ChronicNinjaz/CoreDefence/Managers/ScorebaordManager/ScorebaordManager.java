package com.ChronicNinjaz.CoreDefence.Managers.ScorebaordManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.Game.GameThread;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;

public class ScorebaordManager {
	
	private static ScoreboardManager manager;
	private static Scoreboard board;
	
	private static int score;
	
	public static void updateScorebaord(Player player){
			manager = Bukkit.getScoreboardManager();
			board = manager.getNewScoreboard();
			if(CoreDefence.getGameState() == GameState.STARTED){
				if(player.getScoreboard().getObjective("game") != null){player.getScoreboard().getObjective("game").unregister();}
				
				Objective objective = board.registerNewObjective("game", "dummy");
			
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			
				objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Core Defence   ");
			
				Score longBar1 = objective.getScore(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
				longBar1.setScore(16);
			
				Score space1 = objective.getScore(" ");
				space1.setScore(15);
			
				if(TeamManager.getTeam(player) == null){
					Score team = objective.getScore(ChatColor.RED + "" + ChatColor.ITALIC + ">>" + ChatColor.YELLOW + "" + ChatColor.ITALIC + "Team " + ChatColor.GOLD + "" + ChatColor.ITALIC + "(" + ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + ChatColor.ITALIC + "N/A" + ChatColor.GOLD + "" + ChatColor.ITALIC + ")");
					team.setScore(14);
				}else {
					if(TeamManager.getTeam(player).getName().equalsIgnoreCase("RED")){
						Score team = objective.getScore(ChatColor.RED + "" + ChatColor.ITALIC + ">>" + ChatColor.YELLOW + "" + ChatColor.ITALIC + "Team " + ChatColor.GOLD + "" + ChatColor.ITALIC + "(" + ChatColor.DARK_RED + "" + ChatColor.ITALIC + "RED" + ChatColor.GOLD + "" + ChatColor.ITALIC + ")");
						team.setScore(14);
					
						Score teamPoints = objective.getScore(ChatColor.YELLOW + "Money " + ChatColor.RED + "> " + ChatColor.GOLD + TeamManager.getTeam(player).getPoints());
						teamPoints.setScore(13);
					
						Score teamCoreDamaged = objective.getScore(ChatColor.YELLOW + "TCD    " + ChatColor.RED + "> " + ChatColor.GOLD +  TeamManager.getTeam(player).getCoreDamaged()+ "/" + CoreDefence.getManager().getMaxCoreDamage());
						teamCoreDamaged.setScore(12);
					}else{
						Score team = objective.getScore(ChatColor.RED + "" + ChatColor.ITALIC + ">>" + ChatColor.YELLOW + "" + ChatColor.ITALIC + "Team " + ChatColor.GOLD + "" + ChatColor.ITALIC + "(" + ChatColor.BLUE + "" + ChatColor.ITALIC + "BLUE" + ChatColor.GOLD + "" + ChatColor.ITALIC + ")");
						team.setScore(14);
					}

				}
			
				player.setScoreboard(board);
			}else{
				if(player.getScoreboard().getObjective("lobby") != null){player.getScoreboard().getObjective("lobby").unregister();}
				Objective objective = board.registerNewObjective("lobby", "dummy");
				
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			
				objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "WAITING FOR PLAYERS!");
			
				Score longBar1 = objective.getScore(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
				longBar1.setScore(16);
				
				Score space1 = objective.getScore(" ");
				space1.setScore(15);
			
				CoreDefence.getThread();
				int totalSecs = GameThread.getLobbyCounter();
				int fminutes =(totalSecs %3600)/60;
				int fseconds = totalSecs %60;

				String timeString = String.format("%02d:%02d", fminutes, fseconds);
				
				Score timer = objective.getScore("              " + ChatColor.GOLD + timeString);
				timer.setScore(14);
				
				Score space2 = objective.getScore("  ");
				space2.setScore(13);
				
				Score longBar2 = objective.getScore(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "---------------------");
				longBar2.setScore(12);
				player.setScoreboard(board);
			}
	}

	public static ScoreboardManager getManager() {
		return manager;
	}

	public static void setManager(ScoreboardManager manager) {
		ScorebaordManager.manager = manager;
	}

	public static Scoreboard getBoard() {
		return board;
	}

	public static void setBoard(Scoreboard board) {
		ScorebaordManager.board = board;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		ScorebaordManager.score = score;
	}

}
