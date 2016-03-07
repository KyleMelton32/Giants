package me.Mammothskier.Giants.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.Mammothskier.Giants.entity.Entities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class NMSUtils {
	
	public static String getBukkitVersion() {
		Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}
	
	public static String getMinecraftVersion() {
		Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());
		if (matcher.find()) {
			return matcher.group(2);
		} else {
			return null;
		}
	}
	
	public static void createGiant(Location location, SpawnReason reason) {
		String version = NMSUtils.getBukkitVersion();
		if (Entities.GiantZombie == true) {
			switch (version) {
			case("v1_7_R3"):
				me.Mammothskier.Giants.entity.nms.v1_7_R3.EntityCreator.createEntity(location, reason);
				break;
			case("v1_7_R4"):
				me.Mammothskier.Giants.entity.nms.v1_7_R4.EntityCreator.createEntity(location, reason);
				break;
			case("v1_8_R1"):
				me.Mammothskier.Giants.entity.nms.v1_8_R1.EntityCreator.createEntity(location, reason);
				break;
			case("v1_8_R2"):
				me.Mammothskier.Giants.entity.nms.v1_8_R2.EntityCreator.createEntity(location, reason);
				break;
			case("v1_8_R3"):
				me.Mammothskier.Giants.entity.nms.v1_8_R3.EntityCreator.createEntity(location, reason);
				break;
			case("v1_9_R1"):
				me.Mammothskier.Giants.entity.nms.v1_8_R3.EntityCreator.createEntity(location, reason);
				break;
			}
		}
	}
	
	public static void registerEntities() {
		String version = NMSUtils.getBukkitVersion();
		if ("v1_7_R3".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_7_R3 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_7_R3.CustomEntityType.registerEntities();
		} else if ("v1_7_R4".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_7_R4 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_7_R4.CustomEntityType.registerEntities();
		} else if ("v1_8_R1".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_8_R1 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_8_R1.CustomEntityType.registerEntities();
		} else if ("v1_8_R2".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_8_R2 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityType.registerEntities();
		} else if ("v1_8_R3".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_8_R3 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_8_R3.CustomEntityType.registerEntities();
		} else if ("v1_9_R1".equals(version)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + "Minecraft server version v1_9_R1 found. Enabling Giant Zombies.");
			Entities.GiantZombie = true;
			me.Mammothskier.Giants.entity.nms.v1_9_R1.CustomEntityType.registerEntities();
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.RED + "Minecraft server version " + version + "does not support Giant Zombies.Disabling Giant Zombies");
			Entities.GiantZombie = false;
		}
	}
	
	public static void unregisterEntities() {
		String version = NMSUtils.getBukkitVersion();
		if ("v1_7_R3".equals(version)) {
			me.Mammothskier.Giants.entity.nms.v1_7_R3.CustomEntityType.unregisterEntities();
		} else if ("v1_7_R4".equals(version)) {
			me.Mammothskier.Giants.entity.nms.v1_7_R4.CustomEntityType.unregisterEntities();
		} else if ("v1_8_R1".equals(version)) {
			me.Mammothskier.Giants.entity.nms.v1_8_R1.CustomEntityType.unregisterEntities();
		} else if ("v1_8_R2".equals(version)) {
			me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityType.unregisterEntities();
		} else if ("v1_8_R3".equals(version)) {
			me.Mammothskier.Giants.entity.nms.v1_8_R3.CustomEntityType.unregisterEntities();
		}
	}
}
