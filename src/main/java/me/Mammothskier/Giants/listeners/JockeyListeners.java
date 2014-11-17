package me.Mammothskier.Giants.listeners;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

public class JockeyListeners implements Listener {
	private Giants _jockeys;

	
	public JockeyListeners(Giants jockey) {
		_jockeys = jockey;
		_jockeys.getServer().getPluginManager().registerEvents(this, jockey);
	}
	
	@EventHandler
	public void onJockeyDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		Double damage = event.getDamage();
		DamageCause cause = event.getCause();
		if (API.isJockeyRider(entity)) {
			
			Entity mount =  entity.getVehicle();
			EntityDamageEvent EDE = new EntityDamageEvent(mount, cause, damage);
			Bukkit.getServer().getPluginManager().callEvent(EDE);
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onJockeyDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (API.isGiantJockey(entity)) {
			if (API.isJockeyRider(entity)) {
				event.getDrops().clear();
				event.setDroppedExp(0);
				Entity mount = entity.getVehicle();
				((Damageable) mount).damage(((Damageable) mount).getHealth());
			}
		}
	}
}
