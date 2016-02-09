package com.ChronicNinjaz.CoreDefence.Managers.Game.Buildings;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Region {
	
	private Cuboid cubiod;
	
	private static Location point1;
	private static Location point2;
	
	public Region(Location p1, Location p2){
		Region.point1 = p1;
		Region.point2 = p2;
		cubiod = new Cuboid(p1, p2);
		
		for(Block b: cubiod){
			b.setType(Material.GLASS);
		}
	}

	public Cuboid getCubiod() {
		return cubiod;
	}

	public void setCubiod(Cuboid cubiod) {
		this.cubiod = cubiod;
	}

	public Location getPoint2() {
		return point2;
	}

	public void setPoint2(Location point2) {
		Region.point2 = point2;
	}

	public Location getPoint1() {
		return point1;
	}

	public void setPoint1(Location point1) {
		Region.point1 = point1;
	}

}
