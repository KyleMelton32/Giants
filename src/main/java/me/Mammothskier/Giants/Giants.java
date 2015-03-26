package me.Mammothskier.Giants;

import java.util.List;
import java.util.logging.Logger;

import me.Mammothskier.Giants.entity.Entities;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.util.NMSUtils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin {
	private static FileHandler fileHandler;
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {
		
		String version = NMSUtils.getBukkitVersion();
		if ("v1_8_R1".equals(version)) {

			Bukkit.getConsoleSender().sendMessage("Minecraft server version v1_8_R1 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_8_R1.CustomEntityType.registerEntities();
		} else if ("v1_8_R2".equals(version)) {
			Bukkit.getConsoleSender().sendMessage("Minecraft server version v1_8_R2 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityType.registerEntities();
		} else {
			Bukkit.getConsoleSender().sendMessage("Minecraft server version " + version + "does not support Giant Zombies.Disabling Giant Zombies");
			Entities.GiantZombie = false;
		}
		
		this.getCommand("giants").setExecutor(new Commands(this));
		new Entities(this);
		fileHandler = new FileHandler(this);
	}
	
	@Override
	public void onDisable() {
		me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityType.unregisterEntities();
	}

	public static String getProperty(Files file, String path) {
		return fileHandler.getProperty(file, path);
	}
	
	public static List<String> getPropertyList(Files file, String path) {
		return fileHandler.getPropertyList(file, path);
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
}
