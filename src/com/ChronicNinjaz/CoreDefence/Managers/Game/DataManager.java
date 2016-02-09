package com.ChronicNinjaz.CoreDefence.Managers.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;

public class DataManager {
	
	private static ArrayList<UUID> players = new ArrayList<UUID>();
	private static HashMap<GameLocations, Location> gameLocations = new HashMap<GameLocations, Location>();
	
	public static enum GameLocations {
		RED_TEAM_SPAWN,
		BLUE_TEAM_SPAWN,
		LOBBY
	}

	public static ArrayList<UUID> getPlayers() {
		return players;
	}

	public static HashMap<GameLocations, Location> getGameLocations() {
		return gameLocations;
	}
	
	public static void addGameLocation(GameLocations name, Location location){
		if(!getGameLocations().containsKey(name)){
			getGameLocations().put(name, location);
		}
	}
	
	public static void loadLocationFromSQL(){
		//load from SQL
	}
	
	public static void loadLocationFromFile(){
		//load from File
	}
}
