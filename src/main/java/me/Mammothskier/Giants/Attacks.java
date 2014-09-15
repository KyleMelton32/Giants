package me.Mammothskier.Giants;

import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Attacks {

	public Attacks(Giants attacks) {
		
	}
	
	public void fireAttack(Entity attacker, Entity target, int ticksAttacker, int ticksTarget, boolean sound){
		attacker.setFireTicks(ticksAttacker);
		target.setFireTicks(ticksTarget);
		if (sound == true){
			target.getLocation().getWorld().playSound(target.getLocation(), Sound.FIRE_IGNITE, 1, 0);
		}
	}

	public void jumpAttack(Entity attacker, Entity target, double height, float power, boolean blockDamage, boolean sound){
		attacker.setVelocity(new Vector(0,height, 0));
		Location location = attacker.getLocation();
		location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), power, false, blockDamage);
		if (attacker.getNearbyEntities(2, 4, 2).contains(target)){
	          Vector direction = target.getLocation().toVector().subtract(target.getLocation().toVector()).normalize();
              direction.setX( direction.getX()*2 );
              direction.setY( direction.getY()*2 );
              direction.setZ( direction.getZ()*2 );
              target.setVelocity(direction);
		}
		if (sound == true){
			
		}
	}
	
	public void kickAttack(Entity target, double height, boolean sound){
		target.setVelocity(new Vector(0, height, 0));
		if (sound == true){
			target.getLocation().getWorld().playSound(target.getLocation(), Sound.LAVA_POP, 1, 0);
		}
	}
	
	public void lavaAttack(Entity target, boolean sound){
		target.getLocation().getBlock().setType(Material.LAVA);
		if (sound == true){
			target.getLocation().getWorld().playSound(target.getLocation(), Sound.EXPLODE, 1, 0);
			target.getLocation().getWorld().playSound(target.getLocation(), Sound.LAVA_POP, 1, 0);
		}
	}
	
	public void lightningAttack(Entity target){
		try {
			Location location = target.getLocation();
			location.getWorld().strikeLightning(location);
		} catch (Exception e) {
		}
	}
	
	public void poisonAttack(Player player, Double length) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (length*20), 3));
	}
	
	public void spawnZombies(Location location, int amount, double health, boolean baby, boolean sound){
		for (int i = 1; i <= amount; i++){
			if (baby == true) {
				Entity e = location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
				((Zombie) e).setBaby(true);
				((Damageable) e).setMaxHealth(health);
				((Damageable) e).setHealth(health);
			}
			else{
				Entity e = location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
				((Damageable) e).setMaxHealth(health);
				((Damageable) e).setHealth(health);
			}
		}
		if (sound == true){
			location.getWorld().playSound(location, Sound.EXPLODE, 1, 0);
		}
	}
	
	public void stompAttack(Entity attacker, Entity target, double height, float power, boolean blockDamage, boolean sound){
		Location location = attacker.getLocation();
		location.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), power, false, blockDamage);
		if (sound == true){
			location.getWorld().playSound(location, Sound.ENDERDRAGON_HIT, 1, 0);
		}
	}
	
	public void throwBoulderAttack(LivingEntity attacker, boolean sound){
		Vector direction = ((LivingEntity) attacker).getEyeLocation().getDirection().multiply(2);
		Fireball fireball = attacker.getWorld().spawn(((LivingEntity) attacker).getEyeLocation().add(direction.getX(), direction.getY() - 5, direction.getZ()), Fireball.class);
		fireball.setShooter(attacker);
		if (sound == true){
			Location location = attacker.getLocation();
			location.getWorld().playSound(location, Sound.GHAST_FIREBALL, 1, 0);
		}
	}
}
