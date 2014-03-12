package me.Mammothskier.Giants;

import java.io.IOException;
import java.util.logging.Logger;

import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class Giants extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		new API(this);
		PluginDescriptionFile pdf = this.getDescription();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " Has Been Enabled!");
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
