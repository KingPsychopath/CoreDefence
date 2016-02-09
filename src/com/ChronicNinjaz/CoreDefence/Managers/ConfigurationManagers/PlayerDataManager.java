package com.ChronicNinjaz.CoreDefence.Managers.ConfigurationManagers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Managers.Players.Players;
import com.ChronicNinjaz.CoreDefence.Managers.SQL.MySQL;

public class PlayerDataManager {
	
	private static MySQL sql = CoreDefence.getMySQL();
	
	private static final String INSERT = "INSERT INTO `coredefefence`(`NAME`, `UUID`, `KILLS`, `DEATHS`, `CORES_CAPTURED`, `BUILDINGS_DISTROYED`, `GAMES_PLAYED`, `GEMS`) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SELECT = "SELECT `KILLS`, `DEATHS`, `CORES_CAPTURED`, `BUILDINGS_DISTROYED`, `GAMES_PLAYED`, `GEMS` FROM `coredefefence` WHERE UUID=?";
	
	public void insertPlayerData(Player player){
		sql.openConnection();
		try {
			PreparedStatement preparedStatement = ((Connection) sql).prepareStatement(INSERT);
			preparedStatement.setString(1, player.getName());
			preparedStatement.setString(2, player.getUniqueId().toString());
			preparedStatement.setInt(3, 0);
			preparedStatement.setInt(4, 0);
			preparedStatement.setInt(5, 0);
			preparedStatement.setInt(6, 0);
			preparedStatement.setInt(7, 0);
			preparedStatement.setInt(8, 0);
			
			preparedStatement.execute();
			preparedStatement.close();
			sql.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getPlayerData(Player player) throws SQLException{
		sql.openConnection();
		try {
			PreparedStatement preparedStatement = ((Connection) sql).prepareStatement(SELECT);
			preparedStatement.setString(1, player.getUniqueId().toString());
			
			ResultSet res = preparedStatement.executeQuery();
			
			Players profile = Players.getPlayer(player);
			if(res.next()){
				profile.setPlayer(player);
				profile.setUUID(player.getUniqueId());
				
				profile.setKills(res.getInt("KIILS"));
				profile.setDeaths(res.getInt("DEATHS"));
				profile.setCores_captured(res.getInt("CORES_CAPTURED"));
				profile.setBuildings_distroyed(res.getInt("BUILDINGS_DISTROYED"));
				profile.setGame_played(res.getInt("GAMES_PLAYED"));
				profile.setGems(res.getInt("GEMS"));
				
				profile.setLoaded(true);
			}
			preparedStatement.close();
			res.close();
			sql.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(sql.getConnection() != null){
				sql.closeConnection();
			}
		}
	}
	
	public boolean playerExsits(Player player){
		sql.openConnection();
		try {
			PreparedStatement preparedStatement = ((Connection) sql).prepareStatement("SELECT `NAME` FROM `coredefefence` WHERE UUID=?;");
			preparedStatement.setString(1, player.getUniqueId().toString());
			ResultSet res = preparedStatement.executeQuery();
			boolean containsPlayer = res.next();
			preparedStatement.close();
			res.close();
			return containsPlayer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
