package me.Mammothskier.Giants.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
	public void onGiantSpawn(GiantSpawnEvent event) {
		if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Enabled").equalsIgnoreCase("true")) {
			String message = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Debug Message");
			if (message != null) {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (player.hasPermission("giants.debug") || player.hasPermission("giants.*") || player.isOp()) {
						message = ChatColor.translateAlternateColorCodes('&', message);
						String x = String.valueOf(Math.round(event.getLocation().getX()));
						String y = String.valueOf(Math.round(event.getLocation().getY()));
						String z = String.valueOf(Math.round(event.getLocation().getZ()));
						player.sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Giant"));
						Bukkit.getConsoleSender().sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Giant"));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void GiantsSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		if(event.isCancelled()){
			return;
		}
		
		if (!API.getGiantSpawnWorlds().contains(entity.getWorld().getName())) {
			return;
		}
		
		if ((spawnReason == SpawnReason.NATURAL)) {
			if ((type == EntityType.ZOMBIE) || (type == EntityType.COW) || (type == EntityType.MUSHROOM_COW) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.ENDERMAN)) {
				String string = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Spawn Settings.Chance");
				float sRate;
				try {
					sRate = Float.parseFloat(string);
				} catch (NumberFormatException e) {
					sRate = 10;
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
						GiantSpawnEvent GSE = new GiantSpawnEvent(location);
						Bukkit.getServer().getPluginManager().callEvent(GSE);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void arrowDamage(EntityDamageByEntityEvent event){
		Entity entity = event.getEntity();
		if((event.getDamager() instanceof Arrow) && (API.isGiant(entity))){
			int damage;
			String string = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Damage Settings.Arrows.Damage done by arrow");
			try {
				damage = Integer.parseInt(string);
			} catch (Exception e) {
				damage = 10;
			}
			if(damage == 0){
				event.setCancelled(true);
				return;
			}
			event.setDamage(damage + 0.0);
		}
	}
	
	@EventHandler
	public void fireDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if (API.isGiant(entity)){
			if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Damage Settings.Fire.Allow Fire Damage").equalsIgnoreCase("false")){	
				if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK){
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void suffocationDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if (API.isGiant(entity)){
			if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Damage Settings.Block Damage.Allow Suffocation").equalsIgnoreCase("false")){
				if (event.getCause() == DamageCause.SUFFOCATION || event.getCause() == DamageCause.FALLING_BLOCK){
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void cactiDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if (API.isGiant(entity)){
			if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Damage Settings.Block Damage.Allow Cacti Damage").equalsIgnoreCase("false")){
				if (event.getCause() == DamageCause.THORNS){
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onFireAttack(EntityTargetEvent event) {
		String ticks1 = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Fire Attack.Ticks for Target");
		String ticks2 = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant");
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
				if(!(target == null)){
					if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Fire Attack.Enabled").equalsIgnoreCase("true")) {
						if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Sounds.Fire Attack").equalsIgnoreCase("true")) {
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
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Lightning Attack").equalsIgnoreCase("true")) {
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
			if (API.isGiant(entity)) {
				Location spawnLocation = entity.getLocation();
				Location loc = spawnLocation;
				if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Shrapnel Attack.Enabled").equalsIgnoreCase("true")) {
					String config = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn");
					String config2 = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Shrapnel Attack.Health");
					try {
						Amt = Integer.parseInt(config);
						Health = Double.parseDouble(config2);
					} catch (Exception e) {
						Amt = 3;
						Health = 20;
					}
					if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Sounds.Shrapnel Attack").equalsIgnoreCase("true")){
						if (target instanceof LivingEntity){
							target.getLocation().getWorld().playSound(target.getLocation(), Sound.EXPLODE, 1, 0);
						}
					}
					for (int i = 1; i <= Amt; i++){
						if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies").equalsIgnoreCase("true")) {
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
						if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Throw Boulder Attack").equalsIgnoreCase("true")) {
							Vector direction = ((LivingEntity) entity).getEyeLocation().getDirection().multiply(2);
							Fireball fireball = entity.getWorld().spawn(((LivingEntity) entity).getEyeLocation().add(direction.getX(), direction.getY() - 5, direction.getZ()), Fireball.class);
							fireball.setShooter((LivingEntity) entity);
							if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Sounds.Throw Boulder Attack").equalsIgnoreCase("true")) {
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
		if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Kick Attack.Enabled").equalsIgnoreCase("true")) {
			String config = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Kick Attack.Kick Height");
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
					if (API.isGiant(entity)) {
						if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
							player.setVelocity(new Vector(0, height, 0));
							if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Sounds.Kick Attack").equalsIgnoreCase("true")) {
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
		Player player = event.getPlayer();
		if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Stomp Attack").equalsIgnoreCase("true")) {
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
	public void zombiesOnDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (API.isGiant(entity)) {
			if(API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled").equalsIgnoreCase("true")){
				Location spawnLocation = entity.getLocation();
				Location loc = spawnLocation;
				String config = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn");
				String config2 = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Health");
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
					if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies").equalsIgnoreCase("true")) {
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

	@EventHandler
	public void onGiantDrops(EntityDeathEvent event) throws IOException{
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Giant Stats.Experience");
		int exp;

		try {
			exp = Integer.parseInt(string);
		} catch (Exception e) {
			exp = 5;
		}

		if (API.isGiant(entity)) {
			if (API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Sounds.Death").equalsIgnoreCase("true")) {
				entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
			}
			event.setDroppedExp(exp);
			List<String> newDrop = API.getFileHandler().getPropertyList(Files.GIANT, "Giant Configuration.Giant Stats.Drops");
			if (newDrop == null || newDrop.contains("") || newDrop.toString().equalsIgnoreCase("[]")) {
				return;
			}

			List<ItemStack> drops = new ArrayList<ItemStack>();
			for (String dropList : newDrop) {
				Random rand = new Random();
				String[] s = dropList.split(";");			
				
				if (s.length == 3) {
					String item = s[0];
					String style= "";
					String effect = "";
					String effectLevel= "";
					String amount = s[1];
					String rate = s[2];
					int id = 0;
					int num = 100;
					int den = 100;
					short color = 0;
					int effectID = 0;
					int effectLevelID = 0;
				
					if (item.contains("-")){
						String[] split = item.split("-");
						if (split.length == 3){
							item = split[0];
							effect = split[1];
							effectLevel = split[2];
						}
					} 
					if (item.contains(":")){
						String[] split = item.split(":");
						if (split.length == 2){
							item = split[0];
							style = split[1];
						}
					}
					if (amount.contains("-")){
						int lowerAmount;
						int upperAmount;
						int amt;
						String[] split = amount.split("-");
						String lAmount = split[0];
						String uAmount = split[1];
						try {
							lowerAmount = Integer.parseInt(lAmount);
							upperAmount = Integer.parseInt(uAmount);
						} catch (Exception e) {
							lowerAmount = 1;
							upperAmount = 1;
						}
						amt = rand.nextInt(upperAmount - lowerAmount + 1) + lowerAmount - 1;
						amount = String.valueOf(amt);
					}
					if (rate.contains("/")){
						String[] split = rate.split("/");
						if (split.length == 2){
							try {
								num = Integer.parseInt(split[0]);
								den = Integer.parseInt(split[1]);
							} catch (Exception e) {
								num = 100;
								den = 100;
							}
						}
					}

					int amt = 1;

					try {
						id = Integer.parseInt(item);
						effectID = Integer.parseInt(effect);
						effectLevelID = Integer.parseInt(effectLevel);
						color = Short.parseShort(style);
						amt = Integer.parseInt(amount);
					} catch (Exception e) {
						
					}
					int randNum = rand.nextInt(den);
					
					if (num >= randNum){
						ItemStack newItem = new ItemStack(id, amt, color);
						newItem.setDurability(color);
						if ((effectID == 0) || (effectLevelID == 0)) {
							
						} else if ((effectLevelID >= Enchantment.getById(effectID).getStartLevel()) && (effectLevelID <= Enchantment.getById(effectID).getMaxLevel())) {
							Enchantment enchantment = new EnchantmentWrapper(effectID);
							newItem.addEnchantment(enchantment, effectLevelID);
						} else {
							
						}
						
						drops.add(newItem);
					}
				}
				else {
					return;
				}
			}
			event.getDrops().addAll(drops);
		}
	}
}