package me.Mammothskier.Giants.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class SlimeListeners implements Listener {
	private Giants _slimes;

	public SlimeListeners(Giants slimes) {
		_slimes = slimes;
		_slimes.getServer().getPluginManager().registerEvents(this, slimes);
	}

	@EventHandler
	public void onSlimeSpawn(SlimeSpawnEvent event) {
		if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Enabled").equalsIgnoreCase("true")) {
			String message = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Debug Message");
			if (message != null) {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (player.hasPermission("giants.debug") || player.hasPermission("giants.*") || player.isOp()) {
						message = ChatColor.translateAlternateColorCodes('&', message);
						String x = String.valueOf(Math.round(event.getLocation().getX()));
						String y = String.valueOf(Math.round(event.getLocation().getY()));
						String z = String.valueOf(Math.round(event.getLocation().getZ()));
						player.sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Giant Slime"));
						Bukkit.getConsoleSender().sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Giant Slime"));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void SlimeSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		if(event.isCancelled()){
			return;
		}
		if (!API.getSlimeSpawnWorlds().contains(entity.getWorld().getName())) {
			return;
		}

		if ((spawnReason == SpawnReason.NATURAL)) {
			if ((type == EntityType.ZOMBIE) || (type == EntityType.COW) || (type == EntityType.MUSHROOM_COW) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.ENDERMAN) || (type == EntityType.SLIME)) {
				String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Spawn Settings.Chance");
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

					int spawnslime  = 1;
					double checkcount = 0.01;
					while (checkcount < 10) {
						y2 += checkcount;
						
						if (entity.getWorld().getBlockTypeIdAt(x2, y2, z2) != 0) {
							spawnslime = 0;
						}
						checkcount++;
					}
					if (spawnslime == 1) {
						SlimeSpawnEvent SSE = new SlimeSpawnEvent(location);
						Bukkit.getServer().getPluginManager().callEvent(SSE);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void ArrowDamage(EntityDamageByEntityEvent event){
		String string2 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
		Entity entity = event.getEntity();
		if((event.getDamager() instanceof Arrow) && (API.isGiantSlime(entity))){
			int damage;
			int size = 1;
			int s;
			String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Damage Settings.Arrows.Damage done by arrow");
			try {
				damage = Integer.parseInt(string);
				size = Integer.parseInt(string2);
			} catch (Exception e) {
				damage = 10;
			}
			Slime slime = (Slime) event.getEntity();
			s = slime.getSize();
			if (s == size){
				if(damage == 0){
					event.setCancelled(true);
					return;
				}
				event.setDamage(damage + 0.0);
			}
		}
	}
	
	@EventHandler
	public void fireDamage(EntityDamageEvent event){
		String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
		Entity entity = event.getEntity();
		int size = 1;
		int s;
		if (API.isGiantSlime(entity)){
			try {
				size = Integer.parseInt(string);
			} catch (Exception e) {
			}
			Slime slime = (Slime) event.getEntity();
			s = slime.getSize();
			if (s == size){
				if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Damage Settings.Fire.Allow Fire Damage").equalsIgnoreCase("false")){	
					if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK){
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void suffocationDamage(EntityDamageEvent event){
		String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
		Entity entity = event.getEntity();
		int size = 1;
		int s;
		if (API.isGiantSlime(entity)){
			try {
				size = Integer.parseInt(string);
			} catch (Exception e) {
			}
			Slime slime = (Slime) event.getEntity();
			s = slime.getSize();
			if (s == size){
				if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Damage Settings.Block Damage.Allow Suffocation").equalsIgnoreCase("false")){
					if (event.getCause() == DamageCause.SUFFOCATION || event.getCause() == DamageCause.FALLING_BLOCK){
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void cactiDamage(EntityDamageEvent event){
		String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
		Entity entity = event.getEntity();
		int size = 1;
		int s;
		if (API.isGiantSlime(entity)){
			try {
				size = Integer.parseInt(string);
			} catch (Exception e) {
			}
			Slime slime = (Slime) event.getEntity();
			s = slime.getSize();
			if (s == size){
				if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Damage Settings.Block Damage.Allow Cacti Damage").equalsIgnoreCase("false")){
					if (event.getCause() == DamageCause.THORNS){
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onLightningAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int size = 1;
		int s;
		String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");

		if ((entity instanceof LivingEntity)) {
			if (API.isGiantSlime(entity)) {
				try {
					size = Integer.parseInt(string);
				} catch (Exception e) {
				}
				Slime slime = (Slime) event.getEntity();
				s = slime.getSize();
				if (s == size){
					if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Lightning Attack").equalsIgnoreCase("true")) {
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
		String ticks1 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Fire Attack.Ticks for Target");
		String ticks2 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant");
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
			if (API.isGiantSlime(entity)) {
				Slime slime = (Slime) event.getEntity();
				s = slime.getSize();
				if (s > 4){
					if(!(target == null)){
						if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Fire Attack.Enabled").equalsIgnoreCase("true")) {
							if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Sounds.Fire Attack").equalsIgnoreCase("true")) {
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
	}
	
	@EventHandler
	public void onKickAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Kick Attack.Enabled").equalsIgnoreCase("true")) {
			String config = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Kick Attack.Kick Height");
			String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
			double height;
			int size = 1;
			int s;

			try {
				height = Double.parseDouble(config);
				size = Integer.parseInt(string);
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
					if (API.isGiantSlime(entity)) {
						Slime slime = (Slime) entity;
						s = slime.getSize();
						if (s == size){
							if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
								player.setVelocity(new Vector(0, height, 0));
								if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Sounds.Kick Attack").equalsIgnoreCase("true")) {
									player.getLocation().getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 1, 0);
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
		Player player = event.getPlayer();
		if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Attack Mechanisms.Stomp Attack").equalsIgnoreCase("true")) {
			Random pick = new Random();
			int chance = 0;
			int s;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(3, 2, 3)) {
					if (API.isGiantSlime(entity)) {
						Slime slime = (Slime) entity;
						s = slime.getSize();
						if (s > 4){
							if (entity.getNearbyEntities(3, 2, 3).contains(player)) {
								player.getLocation().getWorld().createExplosion(player.getLocation(), 1.0F);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void GiantSlimeDrops(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Experience");
		String string2 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
		int exp;
		int size = 1;
		int s2;

		try {
			exp = Integer.parseInt(string);
			size = Integer.parseInt(string2);
		} catch (Exception e) {
			exp = 5;
		}

		if (API.isGiantSlime(entity)) {
			if (API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Sounds.Death").equalsIgnoreCase("true")) {
				entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 0);
			}
			event.setDroppedExp(exp);
			List<String> newDrop = API.getFileHandler().getPropertyList(Files.SLIME, "Slime Configuration.Slime Stats.Drops");
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