package com.ChronicNinjaz.CoreDefence.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobSpawnEvent implements Listener{
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event){
		event.setCancelled(true);
	}

}
