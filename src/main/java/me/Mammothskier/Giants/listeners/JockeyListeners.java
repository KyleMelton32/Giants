package me.Mammothskier.Giants.listeners;

import me.Mammothskier.Giants.Giants;
import org.bukkit.event.Listener;

public class JockeyListeners implements Listener {
	private Giants _jockeys;

	
	public JockeyListeners(Giants jockey) {
		_jockeys = jockey;
		_jockeys.getServer().getPluginManager().registerEvents(this, jockey);
	}
}
