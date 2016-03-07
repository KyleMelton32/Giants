package me.Mammothskier.Giants;

import me.Mammothskier.Giants.entity.Entities;
import me.Mammothskier.Giants.events.SpawnEvent;
import me.Mammothskier.Giants.files.ConfigValues;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

public class Commands implements CommandExecutor {
	private Giants _giants;

	public Commands(Giants giants) {
		_giants = giants;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("giants")) {
			if (sender instanceof Player){
				Player player = (Player) sender;
				if (args.length == 0){
					if (player.hasPermission("giants.reload") || player.hasPermission("giants.*") || player.hasPermission("giants.debug") || player.hasPermission("giants.spawn") || player.isOp()) {
						player.sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" +
							"/giants reload:  Reloads the config file.\n" + 
							"/giants spawn [entitytype] <x> <y> <z> : Spawns entity at the location given \n" +
							"/giants version:  Displays the version of the plugin running on the server");
					} else {
						player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					if ((player.hasPermission("giants.reload")) || (player.isOp()) || (player.hasPermission("giants.*"))) {
						if ((player.hasPermission("giants.reload")) || (player.isOp())) {
							Giants.getFileHandler().loadFiles();
							player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "Giants config file reloaded.");
							Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants]" + ChatColor.GREEN + player.getName() + "has reloaded the giants config");
							_giants.log.info("Giants config file reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("spawn")){
					if((player.hasPermission("giants.spawn")) || (player.isOp()) || player.hasPermission("giants.*")){
						if (args.length >= 2){
							if(args[1].equalsIgnoreCase("zombie")){
								Giant entity = null;
								double health;
								
								String string = Giants.getProperty(ConfigValues.zombieHealth);
								try {
									health = Double.parseDouble(string);
								} catch (Exception e) {
									health = 100;
								}
								
								if(args.length ==  2){
									Location loc = (Location) player.getEyeLocation();
									Entities.createGiant(loc, SpawnReason.NATURAL);
									 
									entity = (Giant) SpawnEvent.getGiantZombie(SpawnEvent.getNearbyEntities(loc, 10), loc);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant has been spawned");
								}
								if(args.length == 5){
									Location location = player.getLocation();
									double locx = player.getLocation().getX();
									double locy = player.getLocation().getY();
									double locz = player.getLocation().getZ();
									
									try {
										locx = Integer.parseInt(args[2]);
										locy = Integer.parseInt(args[3]);
										locz = Integer.parseInt(args[4]);
										
									} catch (Exception e) {
									}
									location.setX(locx);
									location.setY(locy);
									location.setZ(locz);
									Location loc = location;
									if (Entities.GiantZombie == true) {
										Entities.createGiant(loc, SpawnReason.NATURAL);
										entity = (Giant) SpawnEvent.getGiantZombie(SpawnEvent.getNearbyEntities(loc, 10), loc);
									} else {
										entity = (Giant) loc.getWorld().spawnEntity(location, EntityType.GIANT);
									}
									
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant has been spawned at x:" + locx + " y:" + locy + "z:" + locz);
								}
								
								if (entity != null) {
									((Damageable) entity).setMaxHealth(health);
									((Damageable) entity).setHealth(health);
									if (entity.getType() == EntityType.GIANT) {
										EntityEquipment armour = ((LivingEntity) entity).getEquipment();
										String config = Giants.getProperty(ConfigValues.zombieArmour);
										String[] s = config.split(":");
										
										float rate = 0f;
										try {
											rate = Float.parseFloat(Giants.getProperty(ConfigValues.armourDropRate));
										} catch (Exception e){
											rate = 0;
										}
										
										try {
											for (int i = 0; i < s.length; i++) {
												Material m = Material.getMaterial(s[i].toUpperCase());
												ItemStack item = new ItemStack(m);
												if (i == 0) {
													armour.setHelmet(item);
													armour.setHelmetDropChance(rate); 
												} else if (i == 1) {
													armour.setChestplate(item);
													armour.setChestplateDropChance(rate); 
												} else if (i == 2) {
													armour.setLeggings(item);
													armour.setLeggingsDropChance(rate); 
												} else if (i == 3) {
													armour.setBoots(item);
													armour.setBootsDropChance(rate); 
												} else if (i == 4) {
													armour.setItemInHand(item);
													armour.setItemInHandDropChance(rate);
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
								return true;
							}
							if(args[1].equalsIgnoreCase("slime")){
								Location loc = (Location) player.getEyeLocation();
								Location location = loc;
								String string = Giants.getProperty(ConfigValues.slimeSize);
								String string2 = Giants.getProperty(ConfigValues.slimeHealth);
								int size;
								double health;
								try {
									size = Integer.parseInt(string);
									health = Double.parseDouble(string2);
								} catch (Exception e) {
									size = 12;
									health = size^2;
								}
								if(args.length ==  2){
									Slime entity = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
									entity.setSize(size);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Slime has been spawned");
								}
								if(args.length == 5){
									double locx = player.getLocation().getX();
									double locy = player.getLocation().getY();
									double locz = player.getLocation().getZ();
										
									try {
										locx = Integer.parseInt(args[2]);
										locy = Integer.parseInt(args[3]);
										locz = Integer.parseInt(args[4]);
									} catch (Exception e) {
									}
									loc.setX(locx);
									loc.setY(locy);
									loc.setZ(locz);
									Slime entity = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
									entity.setSize(size);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Slime has been spawned");
								}
								return true;
							}
							
							if(args[1].equalsIgnoreCase("jockey")){
								Location loc = (Location) player.getEyeLocation();
								Location location = loc;
								String string = Giants.getProperty(ConfigValues.slimeSize);
								String string2 = Giants.getProperty(ConfigValues.slimeHealth);
								String string3 = Giants.getProperty(ConfigValues.zombieHealth);
								int size;
								double giantHealth;
								double slimeHealth;
								try {
									size = Integer.parseInt(string);
									slimeHealth = Double.parseDouble(string2);
									giantHealth = Double.parseDouble(string3);
								} catch (Exception e) {
									size = 12;
									slimeHealth = size^2;
									giantHealth = 100;
								}
								if(args.length ==  2){
									Slime slime = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
									slime.setSize(size);
									slime.setMaxHealth(slimeHealth);
									slime.setHealth(slimeHealth);
									Entities.createGiant(loc, SpawnReason.NATURAL);
									 
									Entity giant = SpawnEvent.getGiantZombie(SpawnEvent.getNearbyEntities(loc, 10), loc);
									((Damageable) giant).setMaxHealth(giantHealth);
									slime.setPassenger(giant);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Jockey has been spawned");
								}
								if(args.length == 5){
									double locx = player.getLocation().getX();
									double locy = player.getLocation().getY();
									double locz = player.getLocation().getZ();
										
									try {
										locx = Integer.parseInt(args[2]);
										locy = Integer.parseInt(args[3]);
										locz = Integer.parseInt(args[4]);
									} catch (Exception e) {
									}
									loc.setX(locx);
									loc.setY(locy);
									loc.setZ(locz);
									Slime slime = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
									slime.setSize(size);
									slime.setMaxHealth(slimeHealth);
									slime.setHealth(slimeHealth);
									Giant giant = (Giant) loc.getWorld().spawnEntity(location, EntityType.GIANT);
									giant.setMaxHealth(giantHealth);
									slime.setPassenger(giant);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Jockey has been spawned");
								}
								return true;
							}
							
							if((args[1].equalsIgnoreCase("lavaslime")) || (args[1].equalsIgnoreCase("magma_cube")) || (args[1].equalsIgnoreCase("magma"))|| (args[1].equalsIgnoreCase("magmacube"))){
								Location loc = (Location) player.getEyeLocation();
								Location location = loc;
								String string = Giants.getProperty(ConfigValues.lavaSlimeSize);
								String string2 = Giants.getProperty(ConfigValues.lavaSlimeHealth);
								int size;
								double health;
								try {
									size = Integer.parseInt(string);
									health = Double.parseDouble(string2);
								} catch (Exception e) {
									size = 12;
									health = size^2;
								}
								if(args.length ==  2){
									MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
									entity.setSize(size);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Magma Cube has been spawned");
								}
								if(args.length == 5){
									double locx = player.getLocation().getX();
									double locy = player.getLocation().getY();
									double locz = player.getLocation().getZ();
										
									try {
										locx = Integer.parseInt(args[2]);
										locy = Integer.parseInt(args[3]);
										locz = Integer.parseInt(args[4]);
									} catch (Exception e) {
									}
									loc.setX(locx);
									loc.setY(locy);
									loc.setZ(locz);
									MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
									entity.setSize(size);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant Magma Cube has been spawned");
								}
								return true;
							}
							else {
								player.sendMessage(ChatColor.RED + "Unknown Entity Type! I recognise zombie, slime, lavaslime and jockey.");
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "Not enough arguements!");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("version")){
					PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("Giants").getDescription();
					player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					return true;
				}
				else {
					if (player.hasPermission("giants.reload") || player.hasPermission("giants.*") || player.hasPermission("giants.debug") || player.hasPermission("giants.spawn") || player.isOp()) {
						player.sendMessage(ChatColor.RED + "Unknown Command!");
					}
					return true;
				}
			}
			else {
				if (args.length == 0){
					Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" +
						"/giants reload:  Reloads the config file.\n" + 
						"/giants version:  Displays the version of the plugin running on the server");
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] "+ ChatColor.GREEN + "Giants config files reloaded.");
					Giants.getFileHandler().loadFiles();
					_giants.log.info("Giants config file reloaded.");
					return true;
				}
				if (args[0].equalsIgnoreCase("version")){
					PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("Giants").getDescription();
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					return true;
				}
				else{
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unknown Command!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" +
						"/giants reload:  Reloads the config files.\n" + 
						"/giants version:  Displays the version of the plugin running on the server");
				}
			}
		}
		return true;
	}
}