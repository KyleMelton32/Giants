package me.Mammothskier.Giants.BarAPI;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.utils.API;
import me.confuser.barapi.BarAPI;

public class HealthBar implements Listener {
	private Giants _giants;
	
	public HealthBar(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}
	
	@EventHandler
	public void onHealthBar(EntityTargetLivingEntityEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		
		if (target instanceof Player) {
			Player player = (Player) target;
			if (API.isGiant(entity) || API.isGiantMagmaCube(entity) || API.isGiantSlime(entity)) {
				setupBar(player, entity);
			}
		}
	}

	@EventHandler
	public void changeHealthBar(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		Entity damager = event.getDamager();
		if (entity == null) {
			return;
		}
		if (API.isGiant(entity) || API.isGiantMagmaCube(entity) || API.isGiantSlime(entity)) {
			if (damager instanceof Projectile) {
				damager =  ((Projectile) damager).getShooter();
			}
			if (damager instanceof Player) {
				Player player = (Player) damager;
				setupBar(player, entity);
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (API.isGiant(entity) || API.isGiantMagmaCube(entity) || API.isGiantSlime(entity)) {
			Entity killer = event.getEntity().getKiller();
			if (killer instanceof Player) {
				Player player = (Player) killer;
				if(BarAPI.hasBar(player)) {
					BarAPI.removeBar(player);
				}
			}
		}
	}
	
	public void setupBar(Player player, Entity entity) {
		EntityType entityType = entity.getType();
		Double health = ((Damageable) entity).getHealth();
		Double maxHealth = ((Damageable) entity).getMaxHealth();
		float percent = (float) (health/maxHealth);
		
		switch (entityType) {
		case GIANT:
			BarAPI.setMessage(player, "Giant", percent);
			break;
		case SLIME:
			BarAPI.setMessage(player, "Giant Slime", percent);
			break;
		case MAGMA_CUBE:
			BarAPI.setMessage(player, "Giant Magma Cube", percent);
			break;
		default:
			break;
		
		}
	}
}
