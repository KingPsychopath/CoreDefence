package com.ChronicNinjaz.CoreDefence.Listeners;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Utils.ItemStackBuilder;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class JoinEvent implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		event.setJoinMessage(null);
		if(CoreDefence.getGameState() == GameState.WAITING){
			Player player = event.getPlayer();
			player.getInventory().clear();
			player.getInventory().setArmorContents(null);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.setFireTicks(0);
			
			Players profile = new Players(player);
			
			if(!CoreDefence.hasLoaded()){
				if(CoreDefence.getPlugin().getPlayerDataManager().playerExsits(player)){
					try {
						CoreDefence.getPlugin().getPlayerDataManager().getPlayerData(player);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}else{
				CoreDefence.getPlugin().getPlayerDataManager().insertPlayerData(player);
			}
			}else{
				if(!CoreDefence.getPlugin().getPlayerConfiguration().playerExsist(player)){
					CoreDefence.getPlugin().getPlayerConfiguration().createPlayer(player);
				}
				
				profile.setKills(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".kills"));
				profile.setDeaths(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".deaths"));
				profile.setCores_captured(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".cores_captured"));
				profile.setGame_played(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".games_played"));
				profile.setGems(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".gems"));
				profile.setBuildings_distroyed(CoreDefence.getPlugin().getPlayerConfiguration().getConfig().getInt(player.getName() + ".buildings_distroyed"));
			}
			
			try{
				CoreDefence.getPlugin();
				String l = CoreDefence.getConfiguration().getConfig().getString("Locations.Spawn");
					if(l != null){
						Location loc = CoreDefence.getConfiguration().getLocation(l);
						if(loc != null){
							player.teleport(loc);
							//Bukkit.getWorlds().get(0).playEffect(loc, Effect.SMOKE, 100);
							
							player.getWorld().spigot().playEffect(loc, Effect.SMOKE);
						}else{
							Bukkit.broadcastMessage("Error: Lobby Location Has Not Been Configured Properly.");
						}
					}
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			
			player.getInventory().setItem(0, new ItemStackBuilder(new ItemStack(Material.BOW)).withName("Choice Kit").withAmount(1).withLore("(Right Click) to open inventory!").build());
			player.getInventory().setItem(3, new ItemStackBuilder().buildSkull(player).build());
			
		}else{
			event.getPlayer().kickPlayer(CoreDefence.getGameState().equals(GameState.STARTED) ? Message.STATE_STATING.toString() : Message.STATE_RESTARTING.toString());
		}
	}
}
