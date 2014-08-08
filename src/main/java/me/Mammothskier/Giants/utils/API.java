package me.Mammothskier.Giants.utils;

import java.util.List;

import me.Mammothskier.Giants.Attacks;
import me.Mammothskier.Giants.Commands;
import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.GiantListeners;
import me.Mammothskier.Giants.events.MagmaCubeListeners;
import me.Mammothskier.Giants.events.SlimeListeners;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Files;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;

public class API {
	private static Giants _giants;
	private static Giants _slimes;
	private static Giants _magmacubes;
	private Commands commands;
	private static FileHandler fileHandler;
	private static Attacks Attacks;
	private static DropsManager drops;

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
		Attacks = new Attacks(_giants);
		drops = new DropsManager(_giants);
	}

	public static boolean isGiant(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		return entity instanceof Giant;
	}
	
	public static boolean isGiantSlime(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Slime");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		
		if (entity instanceof Slime){
			Slime slime = (Slime) entity;
			if ( slime.getSize() > 4){
				return entity instanceof Slime;
			}
		}
		return false;
	}
	
	public static boolean isGiantMagmaCube(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Magma Cube");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		
		if (entity instanceof MagmaCube){
			MagmaCube magmacube = (MagmaCube) entity;
			if (magmacube.getSize() > 4){
				return entity instanceof MagmaCube;
			}
		}
		return false;
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
	
	public static Attacks createAttack() {
		return Attacks;
	}
	
	public static DropsManager createDrop() {
		return drops;
	}
}