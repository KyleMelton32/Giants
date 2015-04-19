package me.Mammothskier.Giants.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class GiantListeners implements Listener {
	private Giants _giants;

	public GiantListeners(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}
	
	@EventHandler
	public void onFireAttack(EntityTargetEvent event) {
		String ticks1 = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Target");
		String ticks2 = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant");
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
			if (Entities.isGiantZombie(entity)) {
				if(!(target == null)){
					if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Enabled").contains("Giant Zombie")) {
						if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Sounds").equalsIgnoreCase("true")) {
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
	}

	@EventHandler
	public void onLightningAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();

		if ((entity instanceof LivingEntity)) {
			if (Entities.isGiantZombie(entity)) {
				if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Lightning Attack").contains("Giant Zombie")) {
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
	public void onShrapnelAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int Amt;
		double Health;
		if ((entity instanceof LivingEntity)) {
			if (Entities.isGiantZombie(entity)) {
				Location spawnLocation = entity.getLocation();
				Location loc = spawnLocation;
				if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Enabled").contains("Giant Zombie")) {
					String config = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn");
					String config2 = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Health");
					try {
						Amt = Integer.parseInt(config);
						Health = Double.parseDouble(config2);
					} catch (Exception e) {
						Amt = 3;
						Health = 20;
					}
					if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Sounds").equalsIgnoreCase("true")){
						if (target instanceof LivingEntity){
							target.getLocation().getWorld().playSound(target.getLocation(), Sound.EXPLODE, 1, 0);
						}
					}
					for (int i = 1; i <= Amt; i++){
						if (Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies").equalsIgnoreCase("true")) {
							Entity e = loc.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
							((Zombie) e).setBaby(true);
							((Damageable) e).setMaxHealth(Health);
							((Damageable) e).setHealth(Health);
						}
						else{
							Entity e = loc.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
							((Damageable) e).setMaxHealth(Health);
							((Damageable) e).setHealth(Health);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onThrownBoulderAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		boolean inRange = false;
		Random pick = new Random();
		int chance = 0;
		int bDamage;
		for (int counter = 1; counter <= 1; counter++) {
			chance = 1 + pick.nextInt(100);
		}

		for (Entity entity : player.getNearbyEntities(15, 12, 15)) {
			if (Entities.isGiantZombie(entity)) {
				if (entity.getNearbyEntities(15, 12, 15).contains(player) && !entity.getNearbyEntities(5, 3, 5).contains(player)) {
					inRange = true;
				}
				if (inRange == true) {
					if (chance == 50) {
						if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled").contains("Giant Zombie")) {
							String config = Giants.getProperty(Files.ATTACKS,  "Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage");
							try {
								bDamage = Integer.parseInt(config);
							} catch (Exception e) {
								bDamage = 1;
							}
							Vector direction = ((LivingEntity) entity).getEyeLocation().getDirection().multiply(2);
							Fireball fireball = entity.getWorld().spawn(((LivingEntity) entity).getEyeLocation().add(direction.getX(), direction.getY() - 5, direction.getZ()), Fireball.class);
							fireball.setShooter((LivingEntity) entity);
							fireball.setYield(bDamage);
							if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Sounds").equalsIgnoreCase("true")) {
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.GHAST_FIREBALL, 1, 0);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onKickAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Kick Attack.Enabled").contains("Giant Zombie")) {
			String config = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Kick Attack.Kick Height");
			double height;

			try {
				height = Double.parseDouble(config);
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
					if (Entities.isGiantZombie(entity)) {
						if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
							player.setVelocity(new Vector(0, height, 0));
							if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Sounds").equalsIgnoreCase("true")) {
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 1, 0);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onStompAttack(PlayerMoveEvent event) {
		boolean sound = false;
		boolean fire = false;
		float power = 1.0f;
		Player player = event.getPlayer();
		if (Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Stomp Attack.Enabled").contains("Giant Zombie")) {
			Random pick = new Random();
			int chance = 0;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
					if (Entities.isGiantZombie(entity)) {
						if (entity.getNearbyEntities(3, 2, 3).contains(player)) {
							String config = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Stomp Attack.Explosion Power");
							if (Giants.getProperty(Files.ATTACKS,
									"Attacks Configuration.Attack Mechanisms.Stomp Attack.Light Fire").equalsIgnoreCase("true")) {
								fire = true;
							}
							if (Giants.getProperty(Files.CONFIG, "Giants Configuration.Sounds").equalsIgnoreCase("true")) {
								sound = true;
							}
							
							try {
								power = Float.parseFloat(config);
							} catch (Exception e) {
								power = 0.0f;
							}
							Location location = player.getLocation();
							location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), power, fire);
							if (sound == true){
								location.getWorld().playSound(location, Sound.FIREWORK_LARGE_BLAST, 1, 0);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void zombiesOnDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (Entities.isGiantZombie(entity)) {
			if(Giants.getPropertyList(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled").contains("Giant Zombie")){
				Location spawnLocation = entity.getLocation();
				Location loc = spawnLocation;
				String config = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn");
				String config2 = Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Health");
				int zombAmt;
				double zombHealth;
				
				try {
					zombAmt = Integer.parseInt(config);
					zombHealth = Double.parseDouble(config2);
				}catch (Exception e) {
					zombAmt = 1;
					zombHealth = 20;
				}
				for (int i = 1; i <= zombAmt; i++){
					if (Giants.getProperty(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies").equalsIgnoreCase("true")) {
						Entity e = loc.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
						((Zombie) e).setBaby(true);
						((Damageable) e).setMaxHealth(zombHealth);
						((Damageable) e).setHealth(zombHealth);
					}
					else{
						Entity e = loc.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
						((Damageable) e).setMaxHealth(zombHealth);
						((Damageable) e).setHealth(zombHealth);
					}
				}
				
			}
		}
	}
}