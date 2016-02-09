package com.ChronicNinjaz.CoreDefence.Managers.Game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.ChronicNinjaz.CoreDefence.CoreDefence;
import com.ChronicNinjaz.CoreDefence.Utils.Utils;

public class Countdown extends BukkitRunnable{
	
	private int mins;
	private int seconds;
	
	public Countdown(int mins, int seconds){
		this.mins = mins;
		this.seconds = seconds;
		
		run();
	}

	@Override
	public void run() {
		if(this.mins >= 1){
			if(!(this.seconds >=1)){
				this.seconds--;
			}else{
				this.mins--;
				this.seconds = 60;
			}
		}
		new Countdown(this.mins, this.seconds).runTaskLater(CoreDefence.getPlugin(), 20L);
		if(mins == 0 && seconds == 0){
			cancel();
		}
		this.Broadcast();
	}
	
	public void Broadcast(){
		int totalSecs = ((60 * this.mins) + this.seconds);
		int fminutes =(totalSecs %3600)/60;
		int fseconds = totalSecs %60;

		String timeString = String.format("%02d:%02d", fminutes, fseconds);
		
		Bukkit.broadcastMessage(Utils.color("&5" + timeString));
	}
	
	public void cancelCountdown(){
		this.cancel();
	}
	
	public void RunTask(){
		
	}

	public int getMins() {
		return mins;
	}

	public void setMins(int mins) {
		this.mins = mins;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
