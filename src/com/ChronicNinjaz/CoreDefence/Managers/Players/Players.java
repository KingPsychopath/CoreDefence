package com.ChronicNinjaz.CoreDefence.Managers.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.Managers.Enums.Kit;

public class Players {

	private static Map<Players, UUID> players = new HashMap<Players, UUID>();
	
	public static Players getPlayer(Player player){
		for(Entry<Players, UUID> p: players.entrySet()){
			if(p.getValue() == player.getUniqueId()){
				return p.getKey();
			}
		}
		return null;
	}
	
	private UUID UUID;
	private Player player;
	
	private Kit kit;
	
	private boolean inGame;
	private boolean pickedKit;
	private boolean hasVoted;
	private boolean hasTeam;
	
	private boolean loaded;
	
	private int kills;
	private int deaths;
	private int cores_captured;
	private int buildings_distroyed;
	private int game_played;
	
	private int gems;

	public Players(Player player) {
		super();
		this.player = player;
		this.UUID = player.getUniqueId();
		
		this.inGame = false;
		this.pickedKit = false;
		this.hasVoted = false;
		this.hasTeam = false;
		
		this.kit = Kit.ARCHER;
		
		this.loaded = true;
		getPlayers().put(this, getUUID());
	}

	public static Map<Players, UUID> getPlayers() {
		return players;
	}

	public static void setPlayers(Map<Players, UUID> players) {
		Players.players = players;
	}

	public UUID getUUID() {
		return UUID;
	}

	public void setUUID(UUID uUID) {
		UUID = uUID;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean isPickedKit() {
		return pickedKit;
	}

	public void setPickedKit(boolean pickedKit) {
		this.pickedKit = pickedKit;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getGame_played() {
		return game_played;
	}

	public void setGame_played(int game_played) {
		this.game_played = game_played;
	}

	public int getGems() {
		return gems;
	}

	public void setGems(int gems) {
		this.gems = gems;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCores_captured() {
		return cores_captured;
	}

	public void setCores_captured(int cores_captured) {
		this.cores_captured = cores_captured;
	}

	public int getBuildings_distroyed() {
		return buildings_distroyed;
	}

	public void setBuildings_distroyed(int buildings_distroyed) {
		this.buildings_distroyed = buildings_distroyed;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public boolean playerHasTeam() {
		return hasTeam;
	}

	public void setPlayerTeam(boolean hasTeam) {
		this.hasTeam = hasTeam;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

}
 