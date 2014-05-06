package me.Mammothskier.Giants;

import java.io.IOException;
import java.util.logging.Logger;

import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;
import net.gravitydevelopment.updater.Updater;

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
/*		if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Updater.Enabled").equals(true)){
			if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Updater.Automatic Download").equals(true)){
				Updater updater = new Updater(this, 42143, this.getFile(), Updater.UpdateType.DEFAULT, true);
			}
			else {
				Updater updater = new Updater(this, 42143, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			}
		}*/
	}
	@Override
	public void onDisable(){
	}
}