package me.Mammothskier.Giants.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Config;
import me.Mammothskier.Giants.files.Giant;
import me.Mammothskier.Giants.utils.API;

public class GiantListeners implements Listener {
	private Giants _giants;

	public GiantListeners(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}

	@EventHandler
	public void onGiantSpawn(GiantSpawnEvent event) {
		if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Debug Mode").equalsIgnoreCase("true")) {
			String message = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Language.Debug Message");
			if (message != null) {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (player.hasPermission("giants.debug") || player.hasPermission("giants.*") || player.isOp()) {
						message = ChatColor.translateAlternateColorCodes('&', message);
						String x = String.valueOf(Math.round(event.getLocation().getX()));
						String y = String.valueOf(Math.round(event.getLocation().getY()));
						String z = String.valueOf(Math.round(event.getLocation().getZ()));
						player.sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Giant"));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void GiantSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		if(event.isCancelled()){
			return;
		}
		else{
			if (!API.getGiantSpawnWorlds().contains(entity.getWorld().getName())) {
				return;
			}

			if ((spawnReason == SpawnReason.NATURAL)) {
				if ((type == EntityType.ZOMBIE) || (type == EntityType.COW) || (type == EntityType.MUSHROOM_COW) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.ENDERMAN)) {
					String string = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Spawn Settings.Chance");
					float sRate;
					try {
						sRate = Float.parseFloat(string);
					} catch (NumberFormatException e) {
						sRate = 0;
					}
					float chance = 100 - sRate;

					Random rand = new Random();
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
						while (checkcount < 10) {
							y2 += checkcount;

							if (entity.getWorld().getBlockTypeIdAt(x2, y2, z2) != 0) {
								spawngiant = 0;
							}
							checkcount++;
						}
						if (spawngiant == 1) {
							GiantSpawnEvent
                                                            GSE = new GiantSpawnEvent(location);
							Bukkit.getServer().getPluginManager().callEvent(GSE);
						}
					}
				}
			}
		}
		String string = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Giant Stats.Health");
		double health;
		try {
			health = Integer.parseInt(string);
		} catch (Exception e) {
			health = 100;
		}
		if(event.getEntityType() == EntityType.GIANT){
			event.getEntity().setMaxHealth(health);
		}
	}
	
	@EventHandler
	public void ArrowDamage(EntityDamageByEntityEvent event){
		Entity entity = event.getEntity();
		if((event.getDamager() instanceof Arrow) && (API.isGiant(entity))){
			int damage;
			String string = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Damage Settings.Arrows.Damage done by arrow");
			try {
				damage = Integer.parseInt(string);
			} catch (Exception e) {
				damage = 10;
			}
			event.setDamage(damage + 0.0);
		}
	}

	@EventHandler
	public void FireAttack(EntityTargetEvent event) {
		String ticks1 = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Target");
		String ticks2 = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant");
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int ticksTarget;
		int ticksGiant;
		try {
			ticksTarget = Integer.parseInt(ticks1);
			ticksGiant = Integer.parseInt(ticks2);
		} catch (Exception e) {
			ticksTarget = 0;
			ticksGiant = 0;
		}

		if ((entity instanceof LivingEntity)) {
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Fire Attack.Enabled").equalsIgnoreCase("true")) {
					if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Sounds.Fire Attack").equalsIgnoreCase("true")) {
						target.getLocation().getWorld().playSound(target.getLocation(), Sound.FIRE, 1, 0);
					}
					try {
						event.getTarget().setFireTicks(ticksTarget);
						event.getEntity().setFireTicks(ticksGiant);
					} catch (Exception e) {
					}
				} else {
					event.setTarget(target);
				}
			}
		}
	}

	@EventHandler
	public void LightningAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();

		if ((entity instanceof LivingEntity)) {
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Lightning Attack").equalsIgnoreCase("true")) {
					try {
						target.getLocation().getWorld().strikeLightning(target.getLocation());
					} catch (Exception e) {
					}
				} else {
					event.setTarget(target);
				}
			}
		}
	}
	
	@EventHandler
	public void ZombieAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int Amt;
		if ((entity instanceof LivingEntity)) {
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Zombie Attack.Enabled").equalsIgnoreCase("true")) {
					String config = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Zombie Attack.Zombies to Spawn");
					try {
						Amt = Integer.parseInt(config);
					} catch (Exception e) {
						Amt = 3;
					}
					for (int i = 1; i <= Amt; i++){
						if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Zombie Attack.Baby Zombies").equalsIgnoreCase("true")) {
							((Zombie) event.getTarget().getLocation().getWorld().spawnEntity(target.getLocation(), EntityType.ZOMBIE)).setBaby(true);
						}
						else{
							event.getTarget().getLocation().getWorld().spawnEntity(target.getLocation(), EntityType.ZOMBIE);
						}
					}
				} else {
					event.setTarget(target);
				}
			}
		}
	}

	@EventHandler
	public void ThrownBoulderAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		boolean inRange = false;
		Random pick = new Random();
		int chance = 0;
		for (int counter = 1; counter <= 1; counter++) {
			chance = 1 + pick.nextInt(100);
		}

		for (Entity entity : player.getNearbyEntities(15, 12, 15)) {
			if (API.isGiant(entity)) {
				if (entity.getNearbyEntities(15, 12, 15).contains(player) && !entity.getNearbyEntities(5, 3, 5).contains(player)) {
					inRange = true;
				}
				if (inRange == true) {
					if (chance == 50) {
						if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Throw Boulder Attack").equalsIgnoreCase("true")) {
							Vector direction = ((LivingEntity) entity).getEyeLocation().getDirection().multiply(2);
							Fireball fireball = entity.getWorld().spawn(((LivingEntity) entity).getEyeLocation().add(direction.getX(), direction.getY() - 5, direction.getZ()), Fireball.class);
							fireball.setShooter((LivingEntity) entity);
							if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Sounds.Throw Boulder Attack").equalsIgnoreCase("true")) {
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.GHAST_FIREBALL, 1, 0);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void KickAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Kick Attack.Enabled").equalsIgnoreCase("true")) {
			String config = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Kick Attack.Kick Height");
			int height;

			try {
				height = Integer.parseInt(config);
			} catch (Exception e) {
				height = 1;
			}

			Random pick = new Random();
			int chance = 0;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
					if (API.isGiant(entity)) {
						if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
							player.setVelocity(new Vector(0, height, 0));
							if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Sounds.Kick Attack").equalsIgnoreCase("true")) {
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 1, 0);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void StompAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Stomp Attack").equalsIgnoreCase("true")) {
			Random pick = new Random();
			int chance = 0;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
					if (API.isGiant(entity)) {
						if (entity.getNearbyEntities(3, 2, 3).contains(player)) {
							player.getLocation().getWorld().createExplosion(player.getLocation(), 1.0F);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void GiantDrops(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Giant Stats.Experience");
		int exp;

		try {
			exp = Integer.parseInt(string);
		} catch (Exception e) {
			exp = 5;
		}

		if (API.isGiant(entity)) {
			if(API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled").equalsIgnoreCase("true")){
				Location spawnLocation = entity.getLocation();
				Location loc = spawnLocation;
				String config = API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn");
				int zombAmt;
				try {
					zombAmt = Integer.parseInt(config);
				}catch (Exception e) {
					zombAmt = 1;
				}
				for (int i = 1; i <= zombAmt; i++){
					loc.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
				}
			}
			if (API.getFileHandler().getGiantProperty(Giant.GIANT, "Giants Configuration.Sounds.Death").equalsIgnoreCase("true")) {
				entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
			}
			event.setDroppedExp(exp);
			List<String> newDrop = API.getFileHandler().getGiantPropertyList(Giant.GIANT, "Giants Configuration.Giant Stats.Drops");
			if (newDrop == null || newDrop.contains("") || newDrop.toString().equalsIgnoreCase("[]")) {
				return;
			}
			List<ItemStack> drops = new ArrayList<ItemStack>();
			for (String s : newDrop) {
				int id = 0;
				int amt = 0;
				short dmg = 0;
				try {
					String[] split = s.split(":");
					if (split.length == 2) {
						String idS = split[0];
						String amtS = split[1];
						id = Integer.parseInt(idS);
						if (amtS.contains("-")) {
							String[] newSplit = amtS.split("-");
							int range;
							int loc;
							Random rand = new Random();
							if (Double.valueOf(newSplit[0]) > Double.valueOf(newSplit[1])) {
								range = (int) ((Double.valueOf(newSplit[0]) * 100) - (Double.valueOf(newSplit[1]) * 100));
								loc = (int) (Double.valueOf(newSplit[1]) * 100);
							} else {
								range = (int) ((Double.valueOf(newSplit[1]) * 100) - (Double.valueOf(newSplit[0]) * 100));
								loc = (int) (Double.valueOf(newSplit[0]) * 100);
							}
							amt = ((int) (loc + rand.nextInt(range + 1))) / 100;
						} else {
							amt = Integer.parseInt(amtS);
						}
						dmg = 0;
					} else if (split.length == 3) {
						String idS = split[0];
						String dmgS = split[1];
						String amtS = split[2];
						id = Integer.parseInt(idS);
						if (amtS.contains("-")) {
							String[] newSplit = amtS.split("-");
							int range;
							int loc;
							Random rand = new Random();
							if (Double.valueOf(newSplit[0]) > Double.valueOf(newSplit[1])) {
								range = (int) ((Double.valueOf(newSplit[0]) * 100) - (Double.valueOf(newSplit[1]) * 100));
								loc = (int) (Double.valueOf(newSplit[1]) * 100);
							} else {
								range = (int) ((Double.valueOf(newSplit[1]) * 100) - (Double.valueOf(newSplit[0]) * 100));
								loc = (int) (Double.valueOf(newSplit[0]) * 100);
							}
							amt = ((int) (loc + rand.nextInt(range + 1))) / 100;
						} else {
							amt = Integer.parseInt(amtS);
						}
						dmg = Short.parseShort(dmgS);
					}
				} catch (Exception e) {
					id = 1;
					amt = 1;
					dmg = 0;
				}
				ItemStack newItem = new ItemStack(id, amt, dmg);
				drops.add(newItem);
			}
			event.getDrops().addAll(drops);
		}
	}
}
