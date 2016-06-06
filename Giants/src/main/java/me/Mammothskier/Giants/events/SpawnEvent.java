package me.Mammothskier.Giants.events;

import java.util.ArrayList;
import java.util.List;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.Files.Files;
import me.Mammothskier.Giants.entity.Entities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class SpawnEvent extends Event{
	private static boolean cancelled = false;
	private Entity entity;
	private Location location;
	private static final HandlerList handlers = new HandlerList();
	
public SpawnEvent(Location loc, EntityType entityType) {
		location = loc;
		Biome biome = loc.getWorld().getBiome(loc.getBlockX(), loc.getBlockZ());
		double health = 100;
		int size = 0;
		
		if (entityType.equals(EntityType.SLIME) || entityType.equals(EntityType.MAGMA_CUBE)) {
			String s;
			if (entityType.equals(EntityType.SLIME)) 
				s = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Slime");
			else s = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Lava Slime");
			
			try {
				size = Integer.parseInt(s);
			} catch (Exception e) {
				size = 12;
			}
		}
		
		if (entityType == EntityType.GIANT && !Entities.GiantZombie)
			setCancelled(true);
		if (entityType == EntityType.SLIME && !Entities.GiantSlime) {
			setCancelled(true);
		}
		if (entityType == EntityType.MAGMA_CUBE && !Entities.GiantLavaSlime) {
			setCancelled(true);
		}
		
		if ((!isCancelled()) && (Entities.getGiantSpawnWorlds(entityType).contains(loc.getWorld().getName()))) {
			Entity entity = null;
			
			if (biome.toString().toLowerCase().contains("Swampland".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Swampland");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Forest".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Forest");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Taiga".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Taiga");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Plains".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Plains");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Extreme_Hills".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Extreme Hills");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Mushroom".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Mushroom Island");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Desert".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Desert");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Jungle".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Jungle");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Birch".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Birch Forest");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Savanna".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Savanna");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Roofed_Forest".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Roofed Forest");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Mesa".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Mesa");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Small_Mountains".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Small Mountains");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Ice_Mountains".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Ice Mountains");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Ocean".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Ocean");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("River".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.River");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Hell".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Hell");
				entity = spawnMob(entityType, l, loc, size);
			}
			if (biome.toString().toLowerCase().contains("Sky".toLowerCase())) {
				List<String> l = Giants.getPropertyList(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Sky");
				entity = spawnMob(entityType, l, loc, size);
			}

			Entities.callSpawnDebug(entity);
			
			if (entity != null) {
				((Damageable) entity).setMaxHealth(health);
				((Damageable) entity).setHealth(health);
				if (entity.getType() == EntityType.GIANT) {
					EntityEquipment armour = ((LivingEntity) entity).getEquipment();
					String config = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Stats.Equipped Armour.Giant Zombie.Items");
					String[] s = config.split(":");
					
					float rate = 0f;
					try {
						rate = Float.parseFloat(Giants.getProperty(Files.ENTITIES, "Entities Configuration.Stats.Equipped Armour.Giant Zombie.Equipped Item Drop Rate"));
					} catch (Exception e){
						rate = 0;
					}
					
					try {
						for (int i = 0; i < s.length; i++) {
							Material m = Material.getMaterial(s[i].toUpperCase());
							ItemStack item = new ItemStack(m);
							if (i == 0) {
								armour.setHelmet(item);
								armour.setHelmetDropChance(rate); 
							} else if (i == 1) {
								armour.setChestplate(item);
								armour.setChestplateDropChance(rate); 
							} else if (i == 2) {
								armour.setLeggings(item);
								armour.setLeggingsDropChance(rate); 
							} else if (i == 3) {
								armour.setBoots(item);
								armour.setBootsDropChance(rate); 
							} else if (i == 4) {
								armour.setItemInHand(item);
								armour.setItemInHandDropChance(rate);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private Entity spawnMob(EntityType entityType, List<String> l, Location loc, int size) {
		if ((entityType.equals(EntityType.GIANT) && l.contains("Giant Zombie")) ||
				(entityType.equals(EntityType.SLIME) && l.contains("Giant Slime")) ||
						(entityType.equals(EntityType.MAGMA_CUBE) && l.contains("Giant Lava Slime"))) {
			if (entityType.equals(EntityType.GIANT)) {
				Entities.createGiant(loc, SpawnReason.NATURAL);
				 
					entity = getGiantZombie(getNearbyEntities(loc, 10), loc);
						
			} else {
				entity = (Slime) loc.getWorld().spawnEntity(location, entityType);
				((Slime) entity).setSize(size);
			}
		}
		return entity;
	}
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public static boolean isCancelled() {
		return cancelled;
	}

	public Entity getEntity() {
		return entity;
	}

	public Location getLocation() {
		return location;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public static List<Entity> getNearbyEntities(Location loc, int size) {
		List<Entity> entities = new ArrayList<Entity>();
		
		for (Entity e : loc.getWorld().getEntities()) 
			if (loc.distance(e.getLocation()) <= size)
				entities.add(e);
		return entities;
	}
	
	public static Entity getGiantZombie(List<Entity> entities, Location loc) {
		Entity e2 = null;
		for (Entity e : getNearbyEntities(loc, 5)) {
			if (Entities.isGiantZombie(e)) {
				e2 = e;
				break;
			}
		}
		return e2;
	}
}
