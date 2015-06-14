package me.Mammothskier.Giants.BarAPI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.entity.Entities;
import me.Mammothskier.Giants.files.Files;
import me.confuser.barapi.BarAPI;

public class HealthBar implements Listener {
	private Giants _giants;
	
	public HealthBar(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}

	@EventHandler
	public void changeHealthBar(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		Entity damager = event.getDamager();
		if (entity == null || damager == null) {
			return;
		}
		if (Entities.isGiantZombie(entity) || Entities.isGiantLavaSlime(entity) || Entities.isGiantSlime(entity)) {
			if (damager instanceof Projectile) {
				damager =  (Entity) ((Projectile) damager).getShooter();
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
		if (Entities.isGiantZombie(entity) || Entities.isGiantLavaSlime(entity) || Entities.isGiantSlime(entity)) {
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
		String size = null;
		
		switch (entityType) {
		case GIANT:
			String giantName = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Stats.BarAPI.Display Name.Giant Zombie");
			giantName = ChatColor.translateAlternateColorCodes('&', giantName);
			if (player.hasPermission("giants.barAPI") || player.hasPermission("giants.*") || player.isOp()) {
				BarAPI.setMessage(player, giantName, percent * 100);
			}
			break;
		case SLIME:
			String slimeName = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Stats.BarAPI.Display Name.Giant Slime");
			slimeName = ChatColor.translateAlternateColorCodes('&', slimeName);
			size = Integer.toString(((Slime) entity).getSize());
			if (player.hasPermission("giants.barAPI") || player.hasPermission("giants.*") || player.isOp()) {
				BarAPI.setMessage(player, slimeName.replace("{size}", size), percent* 100);
			}
			break;
		case MAGMA_CUBE:
			String magmacubeName = Giants.getProperty(Files.ENTITIES, "Magma Cube Configuration.Magma Cube Stats.BarAPI.Display Name.Giant Lava Slime");
			magmacubeName = ChatColor.translateAlternateColorCodes('&', magmacubeName);
			size = Integer.toString(((MagmaCube) entity).getSize());
			if (player.hasPermission("giants.barAPI") || player.hasPermission("giants.*") || player.isOp()) {
				BarAPI.setMessage(player, magmacubeName.replace("{size}", size), percent* 100);
			}
			break;
		default:
			break;
		
		}
	}
}
