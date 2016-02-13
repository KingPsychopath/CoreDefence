package com.ChronicNinjaz.CoreDefence.Managers.Enums;

public enum GameLocations {
	
	
	SPAWN("Location.Spawn"), RED_TEAM_SPAWN("Location.RedTeam.Spawn"), BLUE_TEAM_SPAWN("Location.BlueTeam.Spawn");
	
	private String location;
	
	GameLocations(String location){
		this.setLocation(location);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
