package com.ChronicNinjaz.CoreDefence.Managers.Game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.Kit;
import com.ChronicNinjaz.CoreDefence.Managers.Game.Buildings.Region;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class ArenaManager {
	
	private ArrayList<Region> regions;
	
	private Location point1;
	private Location point2;
	private int min;
	private int seconds;	
	private int minPlayersPerTeam;
	private int restartTimer;
	private int maxCoreDamage = 300;
	
	private int gameTimer;
	private int lobbyTmier;
	
	private boolean gameRunning;
	
	public void start(){
		CoreDefence.setGameState(GameState.STARTED);
		Bukkit.broadcastMessage("RUN START!");
		setGameRunning(true);
		this.min = 2; //Mins
		this.seconds = 20; //Seconds
		this.maxCoreDamage = 300;
		
		for(Player player: Bukkit.getOnlinePlayers()){
			Players profile = Players.getPlayer(player);
			TeamManager.addToTeam(player);
			profile.setPlayerTeam(true);
		}
		
		for(Team t: TeamManager.getTeams()){
			t.setPoints(1000);
			t.setTeamDeaths(0);
			t.setCoreDamaged(0);
			t.setTeamKills(0);
			switch(t.getName()){
			case "RED":
				for(UUID p: t.getPlayers()){
					Player player = Bukkit.getPlayer(p);
					// && this.getRedTeamSpawn() != null
					if(player != null && player.isOnline()){
						Players profile = Players.getPlayer(player);
						Kit k = profile.getKit();
						k.getKitManager().givePlayerKit(player, TeamManager.getTeam(player));
						player.teleport(t.getSpawn());
						break;
					}else{
						Bukkit.broadcastMessage("Red Team Spawn Error");
						break;
					}
				}
				break;
			case "BLUE":
				for(UUID p: t.getPlayers()){
					Player player = Bukkit.getPlayer(p);
					// && this.getBlueTeamSpawn() != null
					if(player != null && player.isOnline()){
						Players profile = Players.getPlayer(player);
						Kit k = profile.getKit();	
						k.getKitManager().givePlayerKit(player, TeamManager.getTeam(player));
						player.teleport(t.getSpawn());
						break;
					}else{
						Bukkit.broadcastMessage("Blue Team Spawn Error");
						break;
					}
				}
				break;
			}
		}
		
		new GameThread().runTaskTimer(CoreDefence.getPlugin(), 0, 20);
	}
		
	/*	new BukkitRunnable(){
			int count = ((getMin() * 60) + getSeconds());
			@Override
			public void run() {
				if((!(count <= 0) && gameMeetsRequirements())){
					count--;
					if(count % 60 <= 0 && count > 60){
						Message.GAME_TIMER_FINISHING.boardcastGameTimer(count);
					}
				}else{
					Message.GAME_HAS_ENDED.boardcastMessage();
					end();
					cancel();
				}
			}
			
		}.runTaskTimer(CoreDefence.getPlugin(), 0, 20);
		
	}*/
	
	
	public void end(){
		CoreDefence.setGameState(GameState.WAITING);
		setGameRunning(false);
		setRestartTimer(20);
		new BukkitRunnable(){
				int count = getRestartTimer();
				@Override
				public void run() {
					Bukkit.broadcastMessage(count + " <<");
						count--;
						if(count % 10 <= 0 && count > 10){
							Message.GAME_TIMER_LOBBY.boardcastGameTimer(count);
						}else if(count < 10 && count > 0){
							Message.GAME_TIMER_LOBBY.boardcastGameTimer(count);
						}else if(count <= 0){
							Message.GAME_TIMER_STARTED.boardcastGameTimer(count);
							if(gameMeetsRequirements()){
								start();
								cancel();
							}else{
								Message.GAME_ERROR_NOT_ENOUGH_PLAYERS.boardcastMessage();
								count = getRestartTimer();
							}
						}
				}
				
			}.runTaskTimer(CoreDefence.getPlugin(), 0, 20);
		
	}
	
	public void firstStart(){
		Bukkit.broadcastMessage("1ST RUN!");
		CoreDefence.setGameState(GameState.WAITING);
		setGameRunning(false);
		setRestartTimer(20);
		this.min = 5; //Mins
		this.seconds = 60; //Seconds
		this.maxCoreDamage = 300;
		this.minPlayersPerTeam = 0;
		new BukkitRunnable(){
				int count = getRestartTimer();
				@Override
				public void run() {
						count--;
						if(count % 10 <= 0 && count > 10){
							Message.GAME_TIMER_LOBBY.boardcastGameTimer(count);
						}else if(count < 10 && count > 0){
							Message.GAME_TIMER_LOBBY.boardcastGameTimer(count);
						}else if(count <= 0){
							Message.GAME_TIMER_STARTED.boardcastGameTimer(count);
							if(gameMeetsRequirements()){
								Bukkit.broadcastMessage("HAS STARTED!");
								cancel();
								start();
							}else{
								Message.GAME_ERROR_NOT_ENOUGH_PLAYERS.boardcastMessage();
								count = getRestartTimer();
							}
						}
				}
				
			}.runTaskTimer(CoreDefence.getPlugin(), 0, 20);
	}
	
	public boolean gameMeetsRequirements(){
		for(Team t: TeamManager.getTeams()){
			if(t != null){
				if(t.getPlayers().size() < getMinPlayersPerTeam()){
					Bukkit.broadcastMessage("Players! <<");
					return false;
				}
			
				if(t.getCoreDamaged() >= getMaxCoreDamage()){
					Bukkit.broadcastMessage("Core <<");
					return false;
				}
			}else{
				Message.TEAM_IS_NULL.consoleMessage();
				return false;
			}
			
		}
		return true;
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public void setRegions(ArrayList<Region> regions) {
		this.regions = regions;
	}

	public Location getPoint1() {
		return point1;
	}

	public void setPoint1(Location point1) {
		this.point1 = point1;
	}

	public Location getPoint2() {
		return point2;
	}

	public void setPoint2(Location point2) {
		this.point2 = point2;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinPlayersPerTeam() {
		return minPlayersPerTeam;
	}

	public void setMinPlayersPerTeam(int minPlayersPerTeam) {
		this.minPlayersPerTeam = minPlayersPerTeam;
	}

	public int getMaxCoreDamage() {
		return maxCoreDamage;
	}

	public void setMaxCoreDamage(int maxCoreDamage) {
		this.maxCoreDamage = maxCoreDamage;
	}


	public boolean isGameRunning() {
		return gameRunning;
	}


	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}


	public int getRestartTimer() {
		return restartTimer;
	}


	public void setRestartTimer(int restartTimer) {
		this.restartTimer = restartTimer;
	}


	public int getGameTimer() {
		return gameTimer;
	}


	public void setGameTimer(int gameTimer) {
		this.gameTimer = gameTimer;
	}


	public int getLobbyTmier() {
		return lobbyTmier;
	}


	public void setLobbyTmier(int lobbyTmier) {
		this.lobbyTmier = lobbyTmier;
	}
	

}
