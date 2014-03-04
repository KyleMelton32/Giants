package me.Mammothskier.Giants.utils;

import java.util.List;

import me.Mammothskier.Giants.Commands;
import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.GiantListeners;
import me.Mammothskier.Giants.events.MagmaCubeListeners;
import me.Mammothskier.Giants.events.SlimeListeners;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Files;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;

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
		return entity instanceof Giant;
	}

	public static boolean isGiant(LivingEntity livingEntity) {
		return livingEntity instanceof Giant;
	}
	
	public static boolean isGiantSlime(Entity entity) {
		return entity instanceof Slime;
	}

	public static boolean isGiantSlime(LivingEntity livingEntity) {
		return livingEntity instanceof Slime;
	}
	
	public static boolean isGiantMagmaCube(Entity entity) {
		return entity instanceof MagmaCube;
	}
	
	public static boolean isGiantMagmaCube(LivingEntity livingEntity) {
		return livingEntity instanceof MagmaCube;
	}

	public static List<String> getGiantSpawnWorlds() {
		return getFileHandler().getPropertyList(Files.GIANT, "Giant Configuration.Spawn Settings.Worlds");
	}
	
	public static List<String> getSlimeSpawnWorlds() {
		return getFileHandler().getPropertyList(Files.SLIME, "Slime Configuration.Spawn Settings.Worlds");
	}
	
	public static List<String> getMagmaCubeSpawnWorlds() {
		return getFileHandler().getPropertyList(Files.MAGMACUBE, "Magma Cube Configuration.Spawn Settings.Worlds");
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
}
