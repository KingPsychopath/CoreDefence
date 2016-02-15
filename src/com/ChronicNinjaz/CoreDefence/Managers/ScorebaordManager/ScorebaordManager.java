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
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;

public class ScorebaordManager {
	
	private static ScoreboardManager manager;
	private static Scoreboard board;
	
	private static int score;
	
	public static void showScoreboard(Player player){
		if(player.getScoreboard().getObjective("game") == null){
			manager = Bukkit.getScoreboardManager();
			board = manager.getNewScoreboard();
			
			Objective objective = board.registerNewObjective("game", "dummy");
			
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Core Defence   ");
			
			Score longBar1 = objective.getScore(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "+-------------------+");
			longBar1.setScore(16);
			
			Score space1 = objective.getScore(" ");
			space1.setScore(15);
			
		//	Score team = objective.getScore(ChatColor.RED + "" + ChatColor.ITALIC + ">>" + ChatColor.YELLOW + "" + ChatColor.ITALIC + "Team:");
		//	team.setScore(14);
			
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
			player.getScoreboard().getObjective("game").unregister();
			showScoreboard(player);
		}
	}
	
	public static void updateScorebaord(Player player, String objective){
		if(player.getScoreboard().getObjective(objective) != null){
			player.getScoreboard().getObjective(objective).unregister();
			showScoreboard(player);
		}else{
			showScoreboard(player);
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
