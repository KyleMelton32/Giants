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
	public void onSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = (Entity) event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		
		if(event.isCancelled()){
			return;
		}
		
		if ((spawnReason == SpawnReason.NATURAL)) {
			Random rand = new Random();

			EntityType spawn;
			int i = rand.nextInt(3);
			if (i== 0)
				spawn = EntityType.GIANT;
			else if (i == 1)
				spawn = EntityType.SLIME;
			else 
				spawn = EntityType.MAGMA_CUBE;
			
			if (!isEnabled(spawn))
				return;

			if (getEntitySpawnReplacements(spawn).contains(type)) {
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
				float chance = 100 - sRate;

				double choice = rand.nextInt(100) < chance ? 1 : 0;
				if (choice == 0) {
					Location location = event.getEntity().getLocation();
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();

					int x2 = (int) x;
					int y2 = (int) y;
					int z2 = (int) z;

					int spawngiant  = 1;
					double checkcount = 0.01;

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
	}
	
	public static void createGiant (Location location, SpawnReason reason) {
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
			}
		}
	}
	
	private boolean isEnabled(EntityType spawn) {
		boolean enabled = false;
		switch (spawn) {
		case GIANT:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Zombie").equalsIgnoreCase("true"))
				enabled = true;
			break;
		case SLIME:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Slime").equalsIgnoreCase("true"))
				enabled = true;
			break;
		case MAGMA_CUBE:
			if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Lava Slime").equalsIgnoreCase("true"))
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
		//TODO JOCKEY
		return (isGiantJockey(entity) && isGiantZombie(entity));
	}
	
	public static List<EntityType> getEntitySpawnReplacements(EntityType type) {

		List<EntityType> list = new ArrayList<EntityType>();
		list.add(EntityType.ZOMBIE);
		list.add(EntityType.ENDERMAN);
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
}
