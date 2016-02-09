package com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.CoreDefence;

public class PlayerConfiguration {
	
	private final File profile;
	private final YamlConfiguration config;
	
	
	public PlayerConfiguration(CoreDefence plugin){
		this.profile = new File(plugin.getDataFolder(), "Profile.yml");
		this.config = YamlConfiguration.loadConfiguration(profile);
		if(!profile.exists()){
			try {
				profile.createNewFile();
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
			config.save(profile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void createPlayer(Player player){
		config.createSection(player.getName());
		config.getConfigurationSection(player.getName()).set("kills", 0);
		config.getConfigurationSection(player.getName()).set("deaths", 0);
		config.getConfigurationSection(player.getName()).set("cores_captured", 0);
		config.getConfigurationSection(player.getName()).set("buildings_distroyed", 0);
		config.getConfigurationSection(player.getName()).set("game_played", 0);
		config.getConfigurationSection(player.getName()).set("gems", 100);
		saveConfig();
	}
	
	public boolean playerExsist(Player player){
		if(config.getConfigurationSection(player.getName()) != null){
			return true;
		}
	return false;
	}
}
