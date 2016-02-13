package com.ChronicNinjaz.CoreDefence.Managers.Teams;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;

public class Team {
	
	private ArrayList<UUID> players;
	private String name;
	
	private int points;
	
	private int coreDamaged;
	
	private int teamKills;
	private int teamDeaths;
	
	private Location spawn;
	
	public Team(String name){
		this.name = name;
		this.players = new ArrayList<UUID>();
		this.points = 100;
		this.coreDamaged = 0;
		this.teamDeaths = 0;
		this.teamKills = 0;
		TeamManager.getTeams().add(this);
	}

	public ArrayList<UUID> getPlayers() {
		return players;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCoreDamaged() {
		return coreDamaged;
	}

	public void setCoreDamaged(int coreDamaged) {
		this.coreDamaged = coreDamaged;
	}

	public int getTeamKills() {
		return teamKills;
	}

	public void setTeamKills(int teamKills) {
		this.teamKills = teamKills;
	}

	public int getTeamDeaths() {
		return teamDeaths;
	}

	public void setTeamDeaths(int teamDeaths) {
		this.teamDeaths = teamDeaths;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

}
