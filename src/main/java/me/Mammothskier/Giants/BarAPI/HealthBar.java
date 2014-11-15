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
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;
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
		String size = null;
		
		switch (entityType) {
		case GIANT:
			String giantName = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Giant Stats.BarAPI.Display Name");
			giantName = ChatColor.translateAlternateColorCodes('&', giantName);
			BarAPI.setMessage(player, giantName, percent * 100);
			break;
		case SLIME:
			String slimeName = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.BarAPI.Display Name");
			slimeName = ChatColor.translateAlternateColorCodes('&', slimeName);
			size = Integer.toString(((Slime) entity).getSize());
			BarAPI.setMessage(player, slimeName.replace("{size}", size), percent* 100);
			break;
		case MAGMA_CUBE:
			String magmacubeName = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.BarAPI.Display Name");
			magmacubeName = ChatColor.translateAlternateColorCodes('&', magmacubeName);
			size = Integer.toString(((MagmaCube) entity).getSize());
			BarAPI.setMessage(player, magmacubeName.replace("{size}", size), percent* 100);
			break;
		default:
			break;
		
		}
	}
}
