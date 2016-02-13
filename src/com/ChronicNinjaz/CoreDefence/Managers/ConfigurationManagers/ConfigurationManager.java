package com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameLocations;

public class ConfigurationManager {
	
	private final File settings;
	private final YamlConfiguration config;
	
	
	public ConfigurationManager(CoreDefence plugin){
		this.settings = new File(plugin.getDataFolder(), "CoreDefence.yml");
		this.config = YamlConfiguration.loadConfiguration(settings);
		if(!plugin.getDataFolder().exists()){
			plugin.getDataFolder().mkdir();
		}
		if(!settings.exists()){
			try {
				settings.createNewFile();
				
				config.set("Database.Address", "localhost:3306");
				config.set("Database.Schema", "example");
				config.set("Database.Username", "root");
				config.set("Database.Password", "root");
            
				config.set("LoadFromFile", true);
				
				Location placeHolder = Bukkit.getWorlds().get(0).getSpawnLocation();
				config.set(GameLocations.SPAWN.getLocation(), serialzeLocation(placeHolder));
				config.set(GameLocations.RED_TEAM_SPAWN.getLocation(), serialzeLocation(placeHolder));
				config.set(GameLocations.BLUE_TEAM_SPAWN.getLocation(), serialzeLocation(placeHolder));
				
			//	Bukkit.broadcastMessage(getLocation(GameLocations.SPAWN.getLocation()) + " <><>");
				
				saveConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
	public void saveConfig(){
		try{
			config.save(settings);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
    public Location getLocation(String location) {
        String[] parts = location.split(" ");
        return new Location(Bukkit.getWorlds().get(0), Double.valueOf(parts[0]), Double.valueOf(parts[1]), 
        		Double.valueOf(parts[2]), Float.valueOf(parts[3]), Float.valueOf(parts[4]));
    }
	
	public String serialzeLocation(Location location){
		  return location.getX() + " " + location.getY() + " " + location.getZ() + " " + location.getYaw() + " " + location.getPitch();
	}
	
	
}
