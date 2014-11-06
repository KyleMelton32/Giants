package me.Mammothskier.Giants.events;

import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JockeySpawnEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	
	public JockeySpawnEvent (Entity entity, Entity passenger) {
		Location location = entity.getLocation();
		
		Biome biome = location.getWorld().getBiome(location.getBlockX(), location.getBlockZ());
		
		if (!isCancelled()) {
			if (biome == Biome.SWAMPLAND) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Swampland.Swampland").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SWAMPLAND_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Swampland.Swampland Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.FOREST) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Forest.Forest").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.FOREST_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Forest.Forest Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.TAIGA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Taiga").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.TAIGA_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Taiga Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.TAIGA_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Taiga Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.COLD_TAIGA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Cold Taiga").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.COLD_TAIGA_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Cold Taiga Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.COLD_TAIGA_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Cold Taiga Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MEGA_TAIGA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Mega Taiga").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MEGA_TAIGA_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Mega Taiga Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MEGA_SPRUCE_TAIGA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Mega Spruce Taiga").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MEGA_SPRUCE_TAIGA_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.PLAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Plains.Plains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.ICE_PLAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Plains.Ice Plains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.ICE_PLAINS_SPIKES) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Plains.Ice Plains Spikes").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SUNFLOWER_PLAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Plains.Sunflower Plains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.OCEAN) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Ocean.Ocean").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.DEEP_OCEAN) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Ocean.Deep Ocean").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.FROZEN_OCEAN) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Ocean.Frozen Ocean").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.RIVER) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.River.River").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.FROZEN_RIVER) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.River.Frozen River").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.BEACH) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Beach.Beach").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.STONE_BEACH) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Beach.Stone Beach").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.COLD_BEACH) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Beach.Cold Beach").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.EXTREME_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.EXTREME_HILLS_PLUS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.EXTREME_HILLS_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.EXTREME_HILLS_PLUS_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MUSHROOM_ISLAND) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mushroom Island.Mushroom Island").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MUSHROOM_SHORE) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mushroom Island.Mushroom Shore").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.DESERT) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Desert.Desert").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.DESERT_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Desert.Desert Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.DESERT_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Desert.Desert Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if ((biome == Biome.JUNGLE) || (biome == Biome.JUNGLE_EDGE)) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Jungle.Jungle").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if ((biome == Biome.JUNGLE_HILLS) || (biome == Biome.JUNGLE_EDGE)) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Jungle.Jungle Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if ((biome == Biome.JUNGLE_MOUNTAINS) || (biome == Biome.JUNGLE_EDGE) || (biome == Biome.JUNGLE_EDGE_MOUNTAINS)) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Jungle.Jungle Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.BIRCH_FOREST) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Birch Forest.Birch Forest").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.BIRCH_FOREST_HILLS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Hills").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.BIRCH_FOREST_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.BIRCH_FOREST_HILLS_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SAVANNA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Savanna.Savanna").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SAVANNA_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Savanna.Savanna Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SAVANNA_PLATEAU) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Savanna.Savanna Plateau").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SAVANNA_PLATEAU_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Savanna.Savanna Plateau Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.ROOFED_FOREST) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Roofed Forest.Roofed Forest").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.ROOFED_FOREST_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA_BRYCE) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa Bryce").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA_PLATEAU) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa Plateau").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA_PLATEAU_FOREST) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Forest").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA_PLATEAU_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.MESA_PLATEAU_FOREST_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SMALL_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Other.Small Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.ICE_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Other.Ice Mountains").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.HELL) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Other.Hell").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
			if (biome == Biome.SKY) {
				if (API.getFileHandler().getProperty(Files.JOCKEYBIOMES, "Jockey Configuration.Biome Settings.Other.Sky").equalsIgnoreCase("true")) {
					entity.setPassenger(passenger);
				}
			}
		}
	}
	

	public boolean isCancelled() {
		return cancel;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
