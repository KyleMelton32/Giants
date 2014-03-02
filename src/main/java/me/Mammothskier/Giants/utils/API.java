package me.Mammothskier.Giants.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

import me.Mammothskier.Giants.Commands;
import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.GiantListeners;
import me.Mammothskier.Giants.events.MagmaCubeListeners;
import me.Mammothskier.Giants.events.SlimeListeners;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Giant;
import me.Mammothskier.Giants.files.MagmaCube;
import me.Mammothskier.Giants.files.Slime;

public class API {
	private static Giants _giants;
	private static Giants _slimes;
	private static Giants _magmacubes;
	private Commands commands;
	private static FileHandler fileHandler;

	public API(Giants giants) {
		_giants = giants;
		_slimes = giants;
		_magmacubes = giants;
		new GiantListeners(_giants);
		new SlimeListeners(_slimes);
		new MagmaCubeListeners(_magmacubes);
		commands = new Commands(_giants);
		_giants.getCommand("giants").setExecutor(commands);
		fileHandler = new FileHandler(_giants);
	}

	public static boolean isGiant(Entity entity) {
		return entity instanceof org.bukkit.entity.Giant;
	}

	public static boolean isGiant(LivingEntity livingEntity) {
		return livingEntity instanceof org.bukkit.entity.Giant;
	}
	
	public static boolean isGiantSlime(Entity entity) {
		return entity instanceof org.bukkit.entity.Slime;
	}

	public static boolean isGiantSlime(LivingEntity livingEntity) {
		return livingEntity instanceof org.bukkit.entity.Slime;
	}
	
	public static boolean isGiantMagmaCube(Entity entity) {
		return entity instanceof org.bukkit.entity.MagmaCube;
	}
	
	public static boolean isGiantMagmaCube(LivingEntity livingEntity) {
		return livingEntity instanceof org.bukkit.entity.MagmaCube;
	}

	public static List<String> getGiantSpawnWorlds() {
		return getFileHandler().getGiantPropertyList(Giant.GIANT, "Giants Configuration.Spawn Settings.Worlds");
	}
	
	public static List<String> getSlimeSpawnWorlds() {
		return getFileHandler().getSlimePropertyList(Slime.SLIME, "Giants Configuration.Spawn Settings.Worlds");
	}
	
	public static List<String> getMagmaCubeSpawnWorlds() {
		return getFileHandler().getMagmaCubePropertyList(MagmaCube.MAGMACUBE, "Giants Configuration.Spawn Settings.Worlds");
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
}
