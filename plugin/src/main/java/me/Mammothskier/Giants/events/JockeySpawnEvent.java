package me.Mammothskier.Giants.events;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.Files.ConfigValues;

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
			if (biome.toString().toLowerCase().contains("Swampland".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeSwampland).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Forest".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeForest).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Taiga".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeTaiga).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Plains".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomePlains).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Extreme_Hills".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeExtremeHills).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Mushroom".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeMushroomIsland).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Desert".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeDesert).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Jungle".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeJungle).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Birch".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeBirchForest).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Savanna".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeSavanna).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Roofed_Forest".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeRoofedForest).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Mesa".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeMesa).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Small_Mountains".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeSmallMountains).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Ice_Mountains".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeIceMountains).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Ocean".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeOcean).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("River".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeRiver).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Hell".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeHell).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
			}
			if (biome.toString().toLowerCase().contains("Sky".toLowerCase()) &&
					Giants.getProperty(ConfigValues.jockeyBiomeSky).equalsIgnoreCase("true")) {
				entity.setPassenger(passenger);
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
