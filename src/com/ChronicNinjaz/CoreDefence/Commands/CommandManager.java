package com.ChronicNinjaz.CoreDefence.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameLocations;
import com.ChronicNinjaz.CoreDefence.Utils.Message;

public class CommandManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String CommandLable, String[] args) {
		if (cmd.getName().equalsIgnoreCase("CoreDefence")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("coredefence.admin")) {
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("set")) {
							if (args[1].equalsIgnoreCase("lobby")) {
								CoreDefence.getConfiguration().getConfig().set("Locations.Spawn",CoreDefence.getConfiguration().serialzeLocation(player.getLocation()));
								CoreDefence.getConfiguration().saveConfig();
								player.sendMessage(Message.ADMIN_SUCCSESSFULLY_SET_LOCATION.replace("%location%",args[1].toString()).toString());
							}
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("set")) {
							if (player.hasPermission("coredefence.admin")) {
								if (args[1].equalsIgnoreCase("red")) {
									if (args[2].equalsIgnoreCase("spawn")) {
										CoreDefence.getConfiguration().getConfig().set(GameLocations.RED_TEAM_SPAWN.getLocation(),CoreDefence.getConfiguration().serialzeLocation(player.getLocation()));
										CoreDefence.getConfiguration().saveConfig();
										player.sendMessage(Message.ADMIN_SUCCSESSFULLY_SET_LOCATION.replace("%location%",args[1].toString()+ " "+ args[2].toString()).toString());
									} else {
										player.sendMessage(Message.PLAYER_WRONG_USEAGE.toString());
									}
								} else if (args[1].equalsIgnoreCase("blue")) {
									if (args[2].equalsIgnoreCase("spawn")) {
										CoreDefence.getConfiguration().getConfig().set(GameLocations.BLUE_TEAM_SPAWN.getLocation(), CoreDefence.getConfiguration().serialzeLocation(player.getLocation()));
										CoreDefence.getConfiguration().saveConfig();
										player.sendMessage(Message.ADMIN_SUCCSESSFULLY_SET_LOCATION.replace("%location%",args[1].toString()+ " "+ args[2].toString()).toString());
									} else {
										player.sendMessage(Message.PLAYER_WRONG_USEAGE.toString());
									}
								}
							} else {
								player.sendMessage(Message.PLAYER_NO_PERMISSION.toString());
							}
						}
					}
				} else {
					player.sendMessage(Message.PLAYER_NO_PERMISSION.toString());
				}
			}
		}
		return false;
	}

}