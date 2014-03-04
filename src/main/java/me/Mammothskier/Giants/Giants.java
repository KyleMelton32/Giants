package me.Mammothskier.Giants;

import java.io.IOException;
import java.util.logging.Logger;

import me.Mammothskier.Giants.utils.API;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class Giants extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		new API(this);
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
	}
	@Override
	public void onDisable(){
	}
}
