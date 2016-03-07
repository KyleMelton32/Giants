package me.Mammothskier.Giants;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import me.Mammothskier.Giants.entity.Entities;
import me.Mammothskier.Giants.files.ConfigValues;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.util.Metrics;
import me.Mammothskier.Giants.util.NMSUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin {
	private static FileHandler fileHandler;
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		NMSUtils.registerEntities();
		
		PluginDescriptionFile pdf = this.getDescription();
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " Has Been Enabled!");
		
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		
		this.getCommand("giants").setExecutor(new Commands(this));
		new Entities(this);
		fileHandler = new FileHandler(this);
		updateEntities();
	}
	
	@Override
	public void onDisable() {
		NMSUtils.unregisterEntities();
	}

	@Deprecated
	public static String getProperty(Files file, String path) {
		return fileHandler.getProperty(file, path);
	}
	
	@Deprecated
	public static List<String> getPropertyList(Files file, String path) {
		return fileHandler.getPropertyList(file, path);
	}

	public static String getProperty(ConfigValues value) {
		return fileHandler.getProperty(value);
	}
	
	public static List<String> getPropertyList(ConfigValues values) {
		return fileHandler.getPropertyList(values);
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
	
	public static void updateEntities() {
		if (Entities.GiantZombie == true && getProperty(ConfigValues.zombieBoolean).equalsIgnoreCase("false")) 
			Entities.GiantZombie = false;
		
		if (getProperty(ConfigValues.slimeBoolean).equalsIgnoreCase("true")) {
			Entities.GiantSlime = true; 
		} else {
			Entities.GiantSlime = false;
		}
		
		if (getProperty(ConfigValues.lavaSlimeBoolean).equalsIgnoreCase("true")) {
			Entities.GiantLavaSlime = true;
		} else {
			Entities.GiantLavaSlime = false;
		}
		
		if (getProperty(ConfigValues.jockeyBoolean).equalsIgnoreCase("true")) {
			Entities.GiantJockey = true;
		} else {
			Entities.GiantJockey = false;
		}		
		
	}
}
