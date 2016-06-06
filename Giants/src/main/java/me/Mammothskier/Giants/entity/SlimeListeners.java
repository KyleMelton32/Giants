package me.Mammothskier.Giants.entity;

import java.util.Random;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.Files.ConfigValues;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SlimeListeners implements Listener {
	private Giants _slimes;

	public SlimeListeners(Giants slimes) {
		_slimes = slimes;
		_slimes.getServer().getPluginManager().registerEvents(this, slimes);
	}
	
	@EventHandler
	public void onLightningAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int s;

		if ((entity instanceof LivingEntity)) {
			if (Entities.isGiantSlime(entity)) {
				Slime slime = (Slime) event.getEntity();
				s = slime.getSize();
				if (s > 4){
					if (Giants.getPropertyList(ConfigValues.lightningAttack).contains("Giant Slime")) {
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
	}
	
	@EventHandler
	public void onFireAttack(EntityTargetEvent event) {
		String ticks1 = Giants.getProperty(ConfigValues.fireAttackTargetTicks);
		String ticks2 = Giants.getProperty(ConfigValues.fireAttackGiantTicks);
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int ticksTarget;
		int ticksGiant;
		int s;
		try {
			ticksTarget = Integer.parseInt(ticks1);
			ticksGiant = Integer.parseInt(ticks2);
		} catch (Exception e) {
			ticksTarget = 0;
			ticksGiant = 0;
		}

		if ((entity instanceof LivingEntity)) {
			if (Entities.isGiantSlime(entity)) {
				Slime slime = (Slime) event.getEntity();
				s = slime.getSize();
				if (s > 4){
					if(!(target == null)){
						if (Giants.getPropertyList(ConfigValues.fireAttack).contains("Giant Slime")) {
							if (Giants.getProperty(ConfigValues.soundsBoolean).equalsIgnoreCase("true")) {
								target.getLocation().getWorld().playSound(target.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 1, 0);
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
	}
	
	@EventHandler
	public void ThrownBoulderAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		boolean inRange = false;
		Random pick = new Random();
		int chance = 0;
		int bDamage;
		int s;
		for (int counter = 1; counter <= 1; counter++) {
			chance = 1 + pick.nextInt(100);
		}

		for (Entity entity : player.getNearbyEntities(15, 12, 15)) {
			if (Entities.isGiantSlime(entity)) {
				Slime slime = (Slime) entity;
				s = slime.getSize();
				if (s > 4){
					if (entity.getNearbyEntities(15, 12, 15).contains(player) && !entity.getNearbyEntities(5, 3, 5).contains(player)) {
						inRange = true;
					}
					if (inRange == true) {
						if (chance == 50) {
							if (Giants.getPropertyList(ConfigValues.boulderAttack).contains("Giant Slime")) {
								String config = Giants.getProperty(ConfigValues.bouldAttackBlockDamage);
								try {
									bDamage = Integer.parseInt(config);
								} catch (Exception e) {
									bDamage = 1;
								}
								Vector direction = ((LivingEntity) entity).getEyeLocation().getDirection().multiply(2);
								Fireball fireball = entity.getWorld().spawn(((LivingEntity) entity).getEyeLocation().add(direction.getX(), direction.getY() - 5, direction.getZ()), Fireball.class);
								fireball.setShooter((LivingEntity) entity);
								fireball.setYield(bDamage);
								if (Giants.getProperty(ConfigValues.soundsBoolean).equalsIgnoreCase("true")) {
									player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1, 0);
								}
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
		if (Giants.getPropertyList(ConfigValues.kickAttack).contains("Giant Slime")) {
			String config = Giants.getProperty(ConfigValues.kickAttackHeight);
			double height;
			int s;

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
					if (Entities.isGiantSlime(entity)) {
						Slime slime = (Slime) entity;
						s = slime.getSize();
						if (s > 4){
							if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
								player.setVelocity(new Vector(0, height, 0));
								if (Giants.getProperty(ConfigValues.soundsBoolean).equalsIgnoreCase("true")) {
									player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 0);
								}
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
		if (Giants.getPropertyList(ConfigValues.stompAttack).contains("Giant Slime")) {
			Random pick = new Random();
			int chance = 0;
			int s;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
					if (Entities.isGiantSlime(entity)) {
						Slime slime = (Slime) entity;
						s = slime.getSize();
						if (s > 4){
							if (entity.getNearbyEntities(3, 2, 3).contains(player)) {
								String config = Giants.getProperty(ConfigValues.stompAttackPower);
								if (Giants.getProperty(ConfigValues.stompAttackFire).equalsIgnoreCase("true")) {
									fire = true;
								}
								if (Giants.getProperty(ConfigValues.soundsBoolean).equalsIgnoreCase("true")) {
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
									location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_LARGE_BLAST, 1, 0);
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void poisonAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Giants.getPropertyList(ConfigValues.poisonAttack).contains("Giant Slime")){
			Random pick = new Random();
			int chance = 0;
			double length;
			String config = Giants.getProperty(ConfigValues.poisonAttackLength);
			try {
				length = Double.parseDouble(config);
			} catch (Exception e) {
				length = 5;
			}
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
					if (Entities.isGiantSlime(entity)) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (length*20), 3));
					}
				}
			}
		}
	}
}