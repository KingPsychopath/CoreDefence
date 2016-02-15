package com.ChronicNinjaz.CoreDefence.Managers.Game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.Kit;
import com.ChronicNinjaz.CoreDefence.Managers.Game.Buildings.Region;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class ArenaManager {
	
	private ArrayList<Region> regions;
	
	private Location point1;
	private Location point2;
	private int minPlayersPerTeam;
	private int restartTimer;
	private int maxCoreDamage = 300;
	
	private boolean gameRunning;
	
	public void start(){
		CoreDefence.setGameState(GameState.STARTED);
		setGameRunning(true);
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
		Bukkit.broadcastMessage("end");
		CoreDefence.setGameState(GameState.WAITING);
		setGameRunning(false);
		
		for(Team t: TeamManager.getTeams()){
			t.setPoints(0);
			t.setCoreDamaged(0);
			t.setTeamDeaths(0);
			t.setTeamKills(0);
			for(UUID p: t.getPlayers()){
				Player player = Bukkit.getPlayer(p);
				player.teleport(CoreDefence.getConfiguration().getLocation(CoreDefence.getConfiguration().getConfig().getString("Locations.Spawn")));
				Players.getPlayer(player).setInGame(false);
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				player.getInventory().setItem(0, new ItemStackBuilder(new ItemStack(Material.BOW)).withName("Choice Kit").withAmount(1).withLore("(Right Click) to open inventory!").build());
				player.getInventory().setItem(3, new ItemStackBuilder().buildSkull(player).build());
			}
		}
	}
	
	public boolean gameMeetsRequirements(){
		for(Team t: TeamManager.getTeams()){
			if(t != null){
				if(t.getPlayers().size() < getMinPlayersPerTeam()){
					Message.GAME_ERROR_NOT_ENOUGH_PLAYERS.boardcastMessage();
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
	

}
