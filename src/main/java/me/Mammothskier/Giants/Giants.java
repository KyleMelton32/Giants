package me.Mammothskier.Giants;

import java.io.IOException;
import java.util.logging.Logger;

import me.Mammothskier.Giants.BarAPI.HealthBar;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;


public class Giants extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	/*
	 * CHANGE LOG:
	 * ======
	 * V1.1 :
	 * Giants no longer spawn from spawn eggs
	 * changed spawn chance to a float so it can store decimals
	 * Removed Damage modifier - it causes a player to kill a Giant instantly
	 * ======
	 * V1.2 :
	 * Fixed spelling error for Experience in config
	 * ======
	 * V2.0 :
	 * ======
	 * Biome Spawn settings
	 * ======
	 * V2.1 :
	 * Update to CB 1.4.6-R0.1
	 * Fixed Giants not targetting players if Fire Attack is set to False
	 * ======
	 * V3.0 :
	 * Added more Biome Spawn options
	 * Added Giant Spawn Egg
	 * ======
	 * V3.1 :
	 * Added 'Other' Biome option incase other plugins modify where mobs can spawn
	 * ======
	 * V3.2 :
	 * Temporarily Disabled Spout Features due to servers getting errors if they did not have the SpoutPlugin
	 * ======
	 * V3.3 :
	 * Added a check to see if the server has the SpoutPlugin before enabling Spout features
	 * ======
	 * V3.4 :
	 * Fixed errors when a player interacts while the server does not have the SpoutPlugin
	 * ======
	 * V4.0 :
	 * Update to craftbukkit-1.4.6-R0.3 and SpoutPlugin 1412
	 * Added Kick Attack
	 * Added Lightning Attack
	 * \giants reload can now be used in console
	 * ======
	 * V5.0 :
	 * Updated to Craftbukkit-1.4.7-R0.1 and SpoutPlugin 1.4.7-R0.2
	 * Added "Throw Boulder Attack"
	 * Added "Stomp Attack"
	 * Added "Spawn Zombies Attack"
	 * Added Sounds to some of the Attack Mechanisms (the ones that don't have sounds)
	 * Added Config to set the height the player is kicked (for the Kick Attack)
	 * Cleaned code
	 * Organized the config.yml
	 * Added option to use Spout features
	 * ======
	 * v6.0:
	 * Update to CB 1.7.2-R0.3
	 * Added 1.7 biome support
	 * Fixed zombie attack
	 * removed spoutcraft support due to being unupdated
	 * Added /giants spawn command
	 * Organized config.yml
	 * ======
	 * V6.1 :
	 * Added support for giant slimes and magmacubes
	 * Added attacks to slimes and magmacubes
	 * Fixed health issue
	 * Moved biome settings to another file
	 * Changed name of zombie attack
	 * Added damage settings
	 * Added /giants version command
	 * Added metrics support
	 * ======
	 * v6.2 :
	 * Added health settings to giant's shrapnel and spawn zombies attack
	 * Fixed error on magmacube spawns
	 * Added error message to commands
	 * ======
	 * v6.3 :
	 * Updated the drop system and format
	 * Added a poison attack to slimes
	 * added block settings for stomp attacks
	 * ======
	 * V6.4 :
	 *  Added support for lore and custom item names
	 *  Added file version to each file
	 *  Added Giant Jockey Entity
	 * ======
	 */
	
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
		
		if ((API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Dependencies.BarAPI").equalsIgnoreCase("true")) &&
				(Bukkit.getPluginManager().getPlugin("BarAPI") != null)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "Found BarAPI: Enabling BarAPI features.");
			new HealthBar(this);
		} else if (Bukkit.getPluginManager().getPlugin("BarAPI") != null) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "Found BarAPI: Config file set to ignore BarAPI features. \n" +
		"Disabling BarAPI features.");
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "Cannot Find BarAPI: Disabling BarAPI features.");
		}
		
		if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Enabled").equalsIgnoreCase("true")) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GOLD + "You have Giant Jockeys Enabled! \n" +
					"This entity of Giants is extremely experimental and does not have many features.");
		}
	}
}