package com.ChronicNinjaz.CoreDefence.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Game.Buildings.Building;
import com.ChronicNinjaz.CoreDefence.Managers.Game.Buildings.BuildingsManager;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;

public class CommandManager implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLable,
			String[] args) {
		if(cmd.getName().equalsIgnoreCase("CoreDefence")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("set")){
						if(player.hasPermission("coredefence.admin")){
							CoreDefence.getConfiguration().getConfig().set("Locations.Spawn", CoreDefence.getConfiguration().serialzeLocation(player.getLocation()));
							CoreDefence.getConfiguration().saveConfig();
						}
					}else if(args[0].equalsIgnoreCase("get")){
						Players p = Players.getPlayer(player);
						Bukkit.broadcastMessage("kills : " + p.getKills());
						Bukkit.broadcastMessage("deaths : " + p.getDeaths());
						Bukkit.broadcastMessage("gems : " + p.getGems());
						Bukkit.broadcastMessage("buildings : " + p.getBuildings_distroyed());
						Bukkit.broadcastMessage("played : " + p.getGame_played());
					}else if(args[0].equalsIgnoreCase("load")){
						 try {
							Building b = BuildingsManager.loadSchematic(new File(CoreDefence.getPlugin().getDataFolder(), "house.schematic"));
							
							BuildingsManager.pasteSchematic(player.getWorld(), player.getLocation(), b);
						} catch (IOException e) {
							e.printStackTrace();
						}
						 
						 
					}
				}
				
			}
		}
		return false;
	}

}