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
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MagmaCubeListeners implements Listener {
	private Giants _magmacubes;

	public MagmaCubeListeners(Giants magmacubes) {
		_magmacubes = magmacubes;
		_magmacubes.getServer().getPluginManager().registerEvents(this, magmacubes);
	}

	@EventHandler
	public void onMagmaCubeSpawn(MagmaCubeSpawnEvent event) {
		if (API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Enabled").equalsIgnoreCase("true")) {
			String message = API.getFileHandler().getProperty(Files.CONFIG, "Giants Configuration.Debug Mode.Debug Message");
			if (message != null) {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (player.hasPermission("giants.debug") || player.hasPermission("giants.*") || player.isOp()) {
						message = ChatColor.translateAlternateColorCodes('&', message);
						String x = String.valueOf(Math.round(event.getLocation().getX()));
						String y = String.valueOf(Math.round(event.getLocation().getY()));
						String z = String.valueOf(Math.round(event.getLocation().getZ()));
						player.sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Magma Cube"));
						Bukkit.getLogger().info(message.replace("%X", x).replace("%Y", y).replace("%Z", z).replace("{entity}", "Magma Cube"));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void MagmaCubeSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();
		if(event.isCancelled()){
			return;
		}
		
		if (!API.getMagmaCubeSpawnWorlds().contains(entity.getWorld().getName())) {
			return;
		}

		if ((spawnReason == SpawnReason.NATURAL)) {
			if ((type == EntityType.ZOMBIE) || (type == EntityType.COW) || (type == EntityType.MUSHROOM_COW) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.ENDERMAN) || (type == EntityType.MAGMA_CUBE)) {
				String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Spawn Settings.Chance");
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

					int spawnmagmacube  = 1;
					double checkcount = 0.01;
					while (checkcount < 10) {
						y2 += checkcount;

						if (entity.getWorld().getBlockTypeIdAt(x2, y2, z2) != 0) {
							spawnmagmacube = 0;
						}
						checkcount++;
					}
					if (spawnmagmacube == 1) {
						MagmaCubeSpawnEvent MCSE = new MagmaCubeSpawnEvent(location);
						Bukkit.getServer().getPluginManager().callEvent(MCSE);
					}
				}
			}
		}
	}
	
/*	@EventHandler
	public void MagmaCubeHealth(CreatureSpawnEvent event){
		String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Health");
		String string2 = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Size");
		double health;
		int size = 0;
		int s;
		try {
			size = Integer.parseInt(string2);
			health = Integer.parseInt(string);
		} catch (Exception e) {
			health = 100;
		}
		if(event.getEntityType() == EntityType.MAGMA_CUBE){
			 MagmaCube magmacube = (MagmaCube)event.getEntity();
			 s = magmacube.getSize();
			if(s == size){
				event.getEntity().setMaxHealth(health);
				event.getEntity().setHealth(health);
			}
		}
	}*/

	
	@EventHandler
	public void ArrowDamage(EntityDamageByEntityEvent event){
		Entity entity = event.getEntity();
		if((event.getDamager() instanceof Arrow) && (API.isGiantMagmaCube(entity))){
			int damage;
			String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Damage Settings.Arrows.Damage done by arrow");
			try {
				damage = Integer.parseInt(string);
			} catch (Exception e) {
				damage = 10;
			}
			event.setDamage(damage + 0.0);
		}
	}
	
	@EventHandler
	public void LavaAttack(EntityTargetEvent event){
		Random pick = new Random();
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int chance = 0;
		double time;
		String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Attack Mechanisms.Lava Attack.Warning Time");
		
		try{
			time = Double.parseDouble(string);
		} catch (Exception e){
			time = 3;
		}
		
		if((API.isGiantMagmaCube(entity)) && (target instanceof Player)){
			if (API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Attack Mechanisms.Lava Attack.Enabled").equalsIgnoreCase("true")) {
				for (int counter = 1; counter <= 1; counter++) {
					chance = 1 + pick.nextInt(100);
				}
				if(chance == 50){
					final Player player = (Player) event.getTarget();
					int time2 = (int) (time * 20);
					player.sendMessage(ChatColor.GOLD + "The magmacube will spawn lava under you in" + time + "seconds");
					Bukkit.getServer().getScheduler()
							.scheduleSyncDelayedTask((Plugin) this, new Runnable() {

						public void run() {
							player.getEyeLocation().getBlock().setType(Material.LAVA);
							player.sendMessage(ChatColor.GOLD + "The magma cube has now spawned lava under you");
						}
					}, time2*1L);
				}
			}
		}
	}

	@EventHandler
	public void GiantMagmaCubeDrops(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Experience");
		int exp;

		try {
			exp = Integer.parseInt(string);
		} catch (Exception e) {
			exp = 5;
		}

		if (API.isGiantMagmaCube(entity)) {
			if (API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Sounds.Death").equalsIgnoreCase("true")) {
				entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
			}
			event.setDroppedExp(exp);
			List<String> newDrop = API.getFileHandler().getPropertyList(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Drops");
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
