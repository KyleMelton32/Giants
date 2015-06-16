package me.Mammothskier.Giants.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitScheduler;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.events.JockeySpawnEvent;
import me.Mammothskier.Giants.events.SpawnEvent;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.util.NMSUtils;

public class Entities implements Listener {
	private static Giants _giants;
	public static boolean GiantZombie = false;
	public static boolean GiantSlime = false;
	public static boolean GiantLavaSlime = false;
	public static boolean GiantJockey = false;
	
	public static final EntityType[] zombieReplacements = {EntityType.ZOMBIE, EntityType.MUSHROOM_COW, EntityType.PIG_ZOMBIE, EntityType.ENDERMAN};
	public static final EntityType[] slimeReplacements = {EntityType.ZOMBIE,EntityType.MUSHROOM_COW, EntityType.PIG_ZOMBIE, EntityType.SLIME, EntityType.ENDERMAN};
	public static final EntityType[] lavaSlimeReplacements = {EntityType.PIG_ZOMBIE, EntityType.ZOMBIE, EntityType.MAGMA_CUBE, EntityType.ENDERMAN, EntityType.BLAZE};
	
	public Entities(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
		new DamageListener(_giants);
		new DropsManager(_giants);
		new GiantListeners(_giants);
		new SlimeListeners(_giants);
		new MagmaCubeListeners(_giants);
		jockeySpawner();
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onSpawnEvent (CreatureSpawnEvent event) {
		Entity entity = (Entity) event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		
		if(event.isCancelled()){
			return;
		}
		
		if (type == EntityType.SLIME || type == EntityType.MAGMA_CUBE && ((Slime) entity).getSize() == 4) return;
		
		if ((spawnReason == SpawnReason.NATURAL || spawnReason == SpawnReason.SPAWNER) && isASpawnReplacement(type)) {
			Random rand = new Random();

			EntityType spawn = null;
			int i = -1;
			while (spawn == null) {
				i = rand.nextInt(3);
				if (i== 0 && isASpawnReplacementFor(type, EntityType.GIANT)) {
					spawn = EntityType.GIANT;
				} else if (i == 1 && isASpawnReplacementFor(type, EntityType.SLIME)) {
					spawn = EntityType.SLIME;
				} else if (i == 2 && isASpawnReplacementFor(type, EntityType.MAGMA_CUBE)){ 
					spawn = EntityType.MAGMA_CUBE;
				}
			}
			if (!isEnabled(spawn)) {
				return;
			}
			
			String string = "";
			if (spawn.equals(EntityType.GIANT)) {
				string = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Chance.Giant Zombie");
			} else if (spawn.equals(EntityType.SLIME)) {
				string = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Chance.Giant Slime");
			} else if (spawn.equals(EntityType.MAGMA_CUBE)) {
				string = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Chance.Giant Lava Slime");
			}
			
			Float sRate;
			try {
				sRate = Float.parseFloat(string);
			} catch (NumberFormatException e) {
				sRate = 10f;
			}
			
			if (rand.nextFloat()*100 < sRate) {
				Location location = entity.getLocation();
				double x = location.getX();
				double y = location.getY();
				double z = location.getZ();

				int x2 = (int) x;
				int y2 = (int) y;
				int z2 = (int) z;

				int spawngiant  = 1;
				double checkcount = 1;

				String s;
				int size;
				
				//Comment out this next line to allow random spawning.
				//spawn = EntityType.GIANT;
				
				switch (spawn) {
				case GIANT:
					while (checkcount <= 15) {
						if (entity.getWorld().getBlockAt(new Location(entity.getWorld(), x2, y2 + checkcount, z2)).getType() != Material.AIR) {
							spawngiant = 0;
						}
						checkcount++;
					}
					break;
				case SLIME:
					s = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Slime");
					try {
						size = Integer.parseInt(s);
					} catch (Exception e) {
						size = 12;
					}
					while (checkcount <= size) {
						if (entity.getWorld().getBlockAt(new Location(entity.getWorld(), x2, y2 + checkcount, z2)).getType() != Material.AIR) {
							spawngiant = 0;
						}
						checkcount++;
					}
					break;
				case MAGMA_CUBE:
					s = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Lava Slime");
					try {
						size = Integer.parseInt(s);
					} catch (Exception e) {
						size = 12;
					}
					while (checkcount <= size) {
						if (entity.getWorld().getBlockAt(new Location(entity.getWorld(), x2, y2 + checkcount, z2)).getType() != Material.AIR) {
							spawngiant = 0;
						}
						checkcount++;
					}
					break;
				default:
					break;
				}
				//Commment out the following line to enable chance of spawning.
				//spawngiant = 1;
				if (spawngiant == 1) {
					SpawnEvent SE = new SpawnEvent(location, spawn);
					Bukkit.getServer().getPluginManager().callEvent(SE);
					event.setCancelled(true);
				}
			}
		}
	}
	
	public static void createGiant (Location location, SpawnReason reason) {
		NMSUtils.createGiant(location, reason);
	}
	
	private boolean isEnabled(EntityType spawn) {
		boolean enabled = false;
		if (spawn == null) {
			return false;
		}
		switch (spawn) {
		case GIANT:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Zombie").equalsIgnoreCase("true") && GiantZombie == true)
				enabled = true;
			break;
		case SLIME:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Slime").equalsIgnoreCase("true") && GiantSlime == true)
				enabled = true;
			break;
		case MAGMA_CUBE:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Lava Slime").equalsIgnoreCase("true") && GiantLavaSlime == true)
				
				enabled = true;
			break;
		default:
			break;
		}
		return enabled;
	}

	public static boolean isGiantZombie(Entity entity) {
		return entity.getType() == EntityType.GIANT ? true : false;
	}
	
	public static boolean isGiantSlime(Entity entity) {
		if (entity.getType() == EntityType.SLIME) {
			Slime slime = (Slime) entity;
			if (slime.getSize() > 4)
				return true;
		}
		return false;
	}
	
	public static boolean isGiantLavaSlime(Entity entity) {
		if (entity.getType() == EntityType.MAGMA_CUBE) {
			Slime slime = (Slime) entity;
			if (slime.getSize() > 4)
				return true;
		}
		return false;
	}
	
	public static boolean isGiantJockey(Entity entity) {
		switch (entity.getType()) {
		case GIANT:
			if ((isGiantSlime(entity.getVehicle())) || (isGiantLavaSlime(entity.getVehicle())) || (entity.getVehicle().getType() == EntityType.GHAST)) {
				return true;
			}
			break;
		case SLIME:
			if ((isGiantSlime(entity)) && (entity.getPassenger().getType() == EntityType.GIANT)) {
				return true;
			}
			break;
		case MAGMA_CUBE:
			if ((isGiantLavaSlime(entity)) && (entity.getPassenger().getType() == EntityType.GIANT)) {
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
	
	public static boolean isGiantJockeyMount(Entity entity) {
		return (isGiantJockey(entity) && isGiantZombie(entity.getPassenger()));
	}
	
	public static boolean isGiantJockeyRider(Entity entity) {
		return (isGiantJockey(entity) && isGiantZombie(entity));
	}
	
	public static List<EntityType> getEntitySpawnReplacements(EntityType type) {
		List<EntityType> list = new ArrayList<EntityType>();
		switch (type) {
		case GIANT:
			for (EntityType t : zombieReplacements) list.add(t);
			break;
		case SLIME:
			for (EntityType t : slimeReplacements) list.add(t);
			break;
		case MAGMA_CUBE:
			for (EntityType t : lavaSlimeReplacements) list.add(t);
			break;
		default:
			break;
		}
		
		return list;
	}
	
	public static List<String> getGiantSpawnWorlds(EntityType entityType) {
		switch (entityType) {
		case GIANT:
			return Giants.getPropertyList(Files.ENTITIES, "Entities Configuration.Spawn Settings.Worlds.Giant Zombie");
		case SLIME:
			return Giants.getPropertyList(Files.ENTITIES, "Entities Configuration.Spawn Settings.Worlds.Giant Slime");
		case MAGMA_CUBE:
			return Giants.getPropertyList(Files.ENTITIES, "Entities Configuration.Spawn Settings.Worlds.Giant Lava Slime");
		default:
			return null;
		}
	}
	
	public static List<String> getJockeySpawnWorlds() {
		return Giants.getPropertyList(Files.JOCKEY, "Jockey Configuration.Spawn Settings.Worlds");
	}
	
	public static void callSpawnDebug(Entity entity) {
		if (entity == null) 
			return;
		if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Enabled").equalsIgnoreCase("true")) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				if (player.hasPermission("giants.debug") || player.hasPermission("giants.*") || player.isOp()) {
					String x = String.valueOf(Math.round(entity.getLocation().getX()));
					String y = String.valueOf(Math.round(entity.getLocation().getY()));
					String z = String.valueOf(Math.round(entity.getLocation().getZ()));
					switch (entity.getType()) {
					case GIANT:
						player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant zombie has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant zombie has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						break;
					case SLIME:
						player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant slime has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant slime has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						break;
					case MAGMA_CUBE:
						player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant lava slime has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A giant lava slime has spawned at X:" + x +", Y:" + y + ", Z:" + z +".");
						break;
					default:
						break;
					}
				}
			}
		}
	}

	public static boolean isGiant(Entity entity) {
		if (isGiantZombie(entity))
			return true;
		if (isGiantSlime(entity))
			return true;
		if (isGiantLavaSlime(entity))
			return true;
		return false;
	}
	
	private void jockeySpawner() {
		if (GiantJockey == true) {
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		    scheduler.scheduleSyncRepeatingTask(_giants, new Runnable() {
		    	
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
	
	private boolean isASpawnReplacement(EntityType entityType) {
		for (EntityType e : zombieReplacements) 
			if (e == entityType) return true;
		for (EntityType e : slimeReplacements) 
			if (e == entityType) return true;
		for (EntityType e : lavaSlimeReplacements) 
			if (e == entityType) return true;
		return false;
	}
	
	private boolean isASpawnReplacementFor(EntityType replacement, EntityType entityType) {
		if (entityType == null) return false;
		switch(entityType) {
		case GIANT:
			for (EntityType e : zombieReplacements) 
				if (e == replacement) return true;
		case SLIME:
			for (EntityType e : slimeReplacements) 
				if (e == replacement) return true;
		case MAGMA_CUBE:
			for (EntityType e : lavaSlimeReplacements) 
				if (e == replacement) return true;
		
		default:
			break;
		}
		return false;
	}
}
