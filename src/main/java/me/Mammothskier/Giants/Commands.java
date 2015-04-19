package me.Mammothskier.Giants;

import me.Mammothskier.Giants.files.Files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
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
							Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants]" + ChatColor.GREEN + player + "has reloaded the giants config");
							_giants.log.info("Giants config file reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					}
					return true;
				}
				/*if (args[0].equalsIgnoreCase("spawn")){
					if((player.hasPermission("giants.spawn")) || (player.isOp()) || player.hasPermission("giants.*")){
						if (args.length >= 2){
							if(args[1].equalsIgnoreCase("zombie")){
								double health;
								
								String string = Giants.getProperty(Files., "Giant Configuration.Giant Stats.Health");
								try {
									health = Double.parseDouble(string);
								} catch (Exception e) {
									health = 100;
								}
								
								if(args.length ==  2){
									Location loc = (Location) player.getEyeLocation();
									Location location = loc;
									Giant entity = (Giant) loc.getWorld().spawnEntity(location, EntityType.GIANT);
									entity.setMaxHealth(health);
									entity.setHealth(health);
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
									
									Giant entity = (Giant) loc.getWorld().spawnEntity(location, EntityType.GIANT);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									
									player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + "A Giant has been spawned at x:" + locx + " y:" + locy + "z:" + locz);
								}
								return true;
							}
							if(args[1].equalsIgnoreCase("slime")){
								Location loc = (Location) player.getEyeLocation();
								Location location = loc;
								String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
								String string2 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Health");
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
								String string = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Size");
								String string2 = API.getFileHandler().getProperty(Files.SLIME, "Slime Configuration.Slime Stats.Health");
								String string3 = API.getFileHandler().getProperty(Files.GIANT, "Giant Configuration.Giant Stats.Health");
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
									Giant giant = (Giant) loc.getWorld().spawnEntity(location, EntityType.GIANT);
									giant.setMaxHealth(giantHealth);
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
								String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Size");
								String string2 = API.getFileHandler().getProperty(Files.MAGMACUBE, "Magma Cube Configuration.Magma Cube Stats.Health");
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
								player.sendMessage(ChatColor.RED + "Unknown Entity Type! I recognise giant, slime, magmacube and jockey.");
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "Not enough arguements!");
						}
					}
					return true;
				}*/
				if (args[0].equalsIgnoreCase("version")){
					if((player.hasPermission("giants.version")) || (player.isOp())){
						PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("Giants").getDescription();
						player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					}
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