package me.Mammothskier.Giants.utils;

import java.util.List;

import me.Mammothskier.Giants.Attacks;
import me.Mammothskier.Giants.Commands;
import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.JockeySpawnEvent;
import me.Mammothskier.Giants.files.FileHandler;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.listeners.GiantListeners;
import me.Mammothskier.Giants.listeners.JockeyListeners;
import me.Mammothskier.Giants.listeners.MagmaCubeListeners;
import me.Mammothskier.Giants.listeners.SlimeListeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.scheduler.BukkitScheduler;

public class API {
	private static Giants _giants;
	private Commands commands;
	private static FileHandler fileHandler;
	private static Attacks Attacks;
	private static DropsManager drops;

	public API(Giants giants) {
		_giants = giants;
		new GiantListeners(_giants);
		new SlimeListeners(_giants);
		new MagmaCubeListeners(_giants);
		new JockeyListeners(_giants);
		commands = new Commands(_giants);
		_giants.getCommand("giants").setExecutor(commands);
		fileHandler = new FileHandler(_giants);
		Attacks = new Attacks(_giants);
		drops = new DropsManager(_giants);
		
		if (getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Enabled").equalsIgnoreCase("true")) {
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		    scheduler.scheduleSyncRepeatingTask(_giants, new Runnable() {
		    	
		        @Override
		        public void run() {
		            for (World world : _giants.getServer().getWorlds()) {
		            	for (Entity entity : world.getEntities()) {
		            		if ((entity instanceof Slime) || (entity instanceof MagmaCube) || (entity instanceof Ghast)) {
		            			for (Entity entity2 : entity.getNearbyEntities(15, 12, 15)) {
			            			if ((entity2 instanceof Giant) && (entity.getPassenger() == null) && (entity2.getVehicle() == null)) {
			            				Entity passenger = entity2;
			            				JockeySpawnEvent JSE = new JockeySpawnEvent(entity, passenger);
			    						Bukkit.getServer().getPluginManager().callEvent(JSE);
			            			}
			            		}
		            		}
		            	}
		            }
		        }
		    }, 0L, 20L);
		}
	}

	public static boolean isGiant(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		if (entity.getVehicle() != null) {
			return false;
		}
		
		return entity instanceof Giant;
	}
	
	public static boolean isGiantSlime(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Slime");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		if (entity.getPassenger() != null) {
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
		if (entity.getPassenger() != null) {
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
	
	public static boolean isGiantJockey(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		switch (entity.getType()) {
		case GIANT:
			if((entity.getVehicle() instanceof Slime && ((Slime) entity).getSize() > 4) ||
					(entity.getVehicle() instanceof MagmaCube && ((MagmaCube) entity).getSize() > 4) ||
					(entity.getVehicle().getType() == EntityType.GHAST)) {
				return true;
			}
			break;
		case SLIME:
			if ((((Slime) entity).getSize() > 4) && (entity.getPassenger().getType() == EntityType.GIANT)) {
				return true;
			}
			break;
		case MAGMA_CUBE:
			if ((((MagmaCube) entity).getSize() > 4) && (entity.getPassenger().getType() == EntityType.GIANT)) {
				return true;
			}
			break;
		case GHAST:	
			if (entity.getPassenger().getType() == EntityType.GIANT) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	public static boolean isJockeyMount(Entity entity) {
		if (isGiantJockey(entity)) {
			switch (entity.getType()) {
			case SLIME:
				if ((((Slime) entity).getSize() > 4) && (entity.getPassenger().getType() == EntityType.GIANT)) {
					return true;
				}
				break;
			case MAGMA_CUBE:
				if ((((MagmaCube) entity).getSize() > 4) && (entity.getPassenger().getType() == EntityType.GIANT)) {
					return true;
				}
				break;
			case GHAST:	
				if (entity.getPassenger().getType() == EntityType.GIANT) {
					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}
	
	public static boolean isJockeyRider(Entity entity) {
		if(isGiant(entity)) { 
			if ((isGiantSlime(entity.getVehicle())) || (isGiantMagmaCube(entity.getVehicle())) || (entity.getVehicle() instanceof Ghast)) {
				return true;
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
	
	public static List<String> getJockeySpawnWorlds() {
		return getFileHandler().getPropertyList(Files.JOCKEY, "Jockey Configuration.Spawn Settings.Worlds");
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