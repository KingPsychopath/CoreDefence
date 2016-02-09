package com.ChronicNinjaz.CoreDefence.Managers.Teams;

import java.util.ArrayList;
import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class TeamManager{
	
	private static ArrayList<Team> teams = new ArrayList<Team>();
	
	public static boolean teamExsits(String team){
		if(teams.contains(team)){
			return true;
		}
		return false;
	}
	
	public static boolean isOnSameTeam(Player player1, Player player2){
		for(Team t: teams){
			if(t.getPlayers().contains(player1.getUniqueId()) && t.getPlayers().contains(player2.getUniqueId())){
				return true;
			}else{
				continue; 
			}
		}
		return false;
	}
	
	public static boolean hasTeam(Player player){
		for(Team t: teams){
			if(t.getPlayers().contains(player.getUniqueId())){
				return true;
			}
		}
		return false;
	}
	
	public static Team getTeam(Player player){
		for(Team t: teams){
			if(t.getPlayers().contains(player.getUniqueId())){
				return t;
			}
		}
		return null;
	}
	
	public static void removeFromTeam(Player player){
			if(hasTeam(player)){
				getTeam(player).getPlayers().remove(player.getUniqueId());
			}
	}
	
	public static void addToTeam(Player player){
		Team team = null;
		for(Team t: teams){
			if(team != null){
				if(t.getPlayers().size() < team.getPlayers().size()){
					team = t;
				}
			}else{
				team = t;
			}
		}
		
		if(team != null){
			team.getPlayers().add(player.getUniqueId());
			if(team.getName() == "RED"){
				Message.JOINED_RED_TEAM.boardcastMessage(player);
			}else{
				Message.JOINED_BLUE_TEAM.boardcastMessage(player);
			}
		}else{
			Message.ERROR_TEAM_IS_NULL.consoleMessage();
		}
	}
	
	public static void deleteAllTeams(){
		teams.clear();
	}
	
	
	
	public static ArrayList<Team> getTeams(){
		return teams;
	}
}