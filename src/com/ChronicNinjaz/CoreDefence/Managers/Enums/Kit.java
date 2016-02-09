package com.ChronicNinjaz.CoreDefence.Managers.Enums;

import com.ChronicNinjaz.CoreDefence.Managers.Game.KitManager;
import com.ChronicNinjaz.CoreDefence.Utils.Kits.Archer;
import com.ChronicNinjaz.CoreDefence.Utils.Kits.Wizard;

public enum Kit {

	ARCHER("Archer", new Archer()),
	WIZARD("Wizard", new Wizard());
	
	private String name;
	private KitManager kitManager;
	
	Kit(String name, KitManager kitManager){
		this.setName(name);
		this.setKitManager(kitManager);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KitManager getKitManager() {
		return kitManager;
	}

	public void setKitManager(KitManager kitManager) {
		this.kitManager = kitManager;
	}
}
