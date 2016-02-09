package com.ChronicNinjaz.CoreDefence;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ChronicNinjaz.CoreDefence.Commands.CommandManager;
import com.ChronicNinjaz.CoreDefence.Listeners.InteractEvent;
import com.ChronicNinjaz.CoreDefence.Listeners.JoinEvent;
import com.ChronicNinjaz.CoreDefence.Listeners.MobSpawnEvent;
import com.ChronicNinjaz.CoreDefence.Listeners.QuitEvent;
import com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers.ConfigurationManager;
import com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers.PlayerConfiguration;
import com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers.PlayerDataManager;
import com.ChronicNinjaz.CoreDefence.Managers.Enums.GameState;
import com.ChronicNinjaz.CoreDefence.Managers.Game.Countdown;
import com.ChronicNinjaz.CoreDefence.Managers.Game.StatsManager;
import com.ChronicNinjaz.CoreDefence.Managers.Menus.MenuManager;
import com.ChronicNinjaz.CoreDefence.Managers.SQL.MySQL;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.Team;
import com.ChronicNinjaz.CoreDefence.Managers.Teams.TeamManager;
import com.ChronicNinjaz.CoreDefence.Menus.KitMenu;
import com.ChronicNinjaz.CoreDefence.Menus.StatsMenu;

public class CoreDefence extends JavaPlugin{
	
	private static CoreDefence plugin;
	private static GameState gameState;
	private static ConfigurationManager configurationManager;
	private static PlayerConfiguration playerConfiguration;
	private static PlayerDataManager playerDataManager;
	private static MenuManager menuManager;
	private static StatsMenu stats;
	private static TeamManager tManager;
	private static boolean load = true;
	private static MySQL mySQL;
	private Team red;
	private Team blue;
	
	public void onEnable(){
		setPlugin(this);
		if(!plugin.getDataFolder().exists()){plugin.getDataFolder().mkdir();}
		playerConfiguration = new PlayerConfiguration(getPlugin());
		configurationManager = new ConfigurationManager(getPlugin());
		playerDataManager = new PlayerDataManager();
		menuManager = new MenuManager(getPlugin());
		tManager = new TeamManager();
		load = getConfiguration().getConfig().getBoolean("LoadFromFile");
		setGameState(GameState.WAITING);
		if(load){}else{/**mySQL = new MySQL(this, "31.170.160.102", "80", "minecraft", "root", ""); mySQL.openConnection(); */}
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinEvent(), this);
		pm.registerEvents(new QuitEvent(), this);
		pm.registerEvents(new InteractEvent(), this);
		pm.registerEvents(new MenuManager(this), this);
		pm.registerEvents(new MobSpawnEvent(), this);
		pm.registerEvents(new StatsManager(), this);
		getCommand("CoreDefence").setExecutor(new CommandManager());
		this.setRed(new Team("RED"));
		this.setBlue(new Team("BLUE"));
		menuManager.addMenu("kit", new KitMenu("&6Kit Menu", 9));
		//menuManager.addMenu("stats", new StatsMenu("&6Stats", 54));
	
	}
	
	public void onDisable(){}

	public static CoreDefence getPlugin() {
		return plugin;
	}

	public static void setPlugin(CoreDefence plugin) {
		CoreDefence.plugin = plugin;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		CoreDefence.gameState = gameState;
	}

	public static ConfigurationManager getConfiguration() {
		return configurationManager;
	}

	public void setConfiguration(ConfigurationManager configuration) {
		CoreDefence.configurationManager = configuration;
	}
	
	public void connect(){}

	public static MySQL getMySQL() {
		return mySQL;
	}

	public static void setMySQL(MySQL mySQL) {
		CoreDefence.mySQL = mySQL;
	}

	public static boolean hasLoaded() {
		return load;
	}

	public static void setHasLoaded(boolean load) {
		CoreDefence.load = load;
	}

	public  Team getRed() {
		return red;
	}

	public void setRed(Team red) {
		this.red = red;
	}

	public Team getBlue() {
		return blue;
	}

	public void setBlue(Team blue) {
		this.blue = blue;
	}

	public PlayerConfiguration getPlayerConfiguration() {
		return playerConfiguration;
	}

	public void setPlayerConfiguration(PlayerConfiguration playerConfiguration) {
		CoreDefence.playerConfiguration = playerConfiguration;
	}

	public static MenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(MenuManager menuManager) {
		CoreDefence.menuManager = menuManager;
	}

	public static TeamManager getTeamManager() {
		return tManager;
	}

	public void settManager(TeamManager tManager) {
		CoreDefence.tManager = tManager;
	}

	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}

	public void setPlayerDataManager(PlayerDataManager playerDataManager) {
		CoreDefence.playerDataManager = playerDataManager;
	}

	public static StatsMenu getStats() {
		return stats;
	}

	public static void setStats(StatsMenu stats) {
		CoreDefence.stats = stats;
	}
}
