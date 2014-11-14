package me.Mammothskier.Giants.BarAPI;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;
import me.confuser.barapi.BarAPI;

public class HealthBar implements Listener {
	private Giants _giants;
	
	public HealthBar(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}
	
	public void onHealthBar(EntityTargetLivingEntityEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		
		if (target instanceof Player) {
			Player player = (Player) target;
			if (API.isGiant(entity)) {
				BarAPI.setMessage(player, entity.getType() + "", (float) (((Damageable) entity).getHealth() / ((Damageable) entity).getMaxHealth()));
			} else if (API.isGiantSlime(entity)) {
				BarAPI.setMessage(player, "Giant" + entity.getType(), (float) (((Damageable) entity).getHealth() / ((Damageable) entity).getMaxHealth()));
			} else if (API.isGiantMagmaCube(entity)) {
				BarAPI.setMessage(player, "Giant" + entity.getType(), (float) (((Damageable) entity).getHealth() / ((Damageable) entity).getMaxHealth()));
			}
		}
	}

	
	public void changeHealthBar(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		Entity entity = event.getEntity();
		if (entity == null || damager == null) {
			return;
		}
		if (API.isGiant(entity) || API.isGiantMagmaCube(entity) || API.isGiantSlime(entity)) {
			if (damager instanceof Arrow) {
				damager = ((Arrow) damager).getShooter();
			}
			if (damager instanceof Player) {
				Player player = (Player) damager;
				if (BarAPI.hasBar(player)) {
					BarAPI.setHealth(player,  (float) (((Damageable) entity).getHealth() / ((Damageable) entity).getMaxHealth()));
				} else {
					BarAPI.setMessage(player, entity.getType() + "", (float) (((Damageable) entity).getHealth() / ((Damageable) entity).getMaxHealth()));
				}
			}
		}
	}
	
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
}
