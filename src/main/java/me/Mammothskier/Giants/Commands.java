package me.Mammothskier.Giants;

import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginDescriptionFile;

public class Commands implements CommandExecutor {
	private Giants _giants;

	public Commands(Giants giants) {
		_giants = giants;
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("giants")) {
			if (args.length == 0) {
				if (sender.hasPermission("giants.reload") || sender.hasPermission("giants.*") || sender.hasPermission("giants.debug") || sender.hasPermission("giants.spawn") || sender.isOp()) {
				sender.sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" + "/giants reload:  Reloads the config file.\n" + "/giants spawn [entitytype] <x> <y> <z>");
				} else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("reload")) {
				Player player = null;
				if (sender instanceof Player) {
					player = (Player) sender;
					if ((player.hasPermission("giants.reload")) || (player.isOp())) {
						API.getFileHandler().loadFiles();
						sender.sendMessage(ChatColor.GREEN + "Giants config file reloaded.");
						_giants.log.info(ChatColor.GREEN + "Giants config file reloaded.");
						Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants]" + ChatColor.GREEN + sender + "has reloaded the giants config");
						
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
				} else {
					API.getFileHandler().loadFiles();
					_giants.log.info(ChatColor.GREEN + "Giants config file reloaded.");					}
				}
			if (args[0].equalsIgnoreCase("spawn")){
				Player player = null;
				if (sender instanceof Player){
					player = (Player) sender;
					if((player.hasPermission("giants.spawn")) || (player.isOp())){
						if(args[1].equalsIgnoreCase("giant")){
							if(args.length ==  2){
								Location loc = (Location) player.getEyeLocation();
								Location location = loc;
								loc.getWorld().spawnEntity(location, EntityType.GIANT);
								player.sendMessage(ChatColor.GREEN + "A Giant has been spawned");
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
								loc.getWorld().spawnEntity(location, EntityType.GIANT);
								player.sendMessage(ChatColor.GREEN + "A Giant has been spawned at x:" + locx + " y:" + locy + "z:" + locz);
							}
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
								player.sendMessage(ChatColor.GREEN + "A Giant Slime has been spawned");
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
								player.sendMessage(ChatColor.GREEN + "A Giant Slime has been spawned");
							}
						}
						if(args[1].equalsIgnoreCase("magma") && args[2].equalsIgnoreCase("cube")){
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
							if(args.length == 3){
								MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
								entity.setSize(size);
								entity.setMaxHealth(health);
								player.sendMessage(ChatColor.GREEN + "A Giant Magma Cube has been spawned");
							}
							if(args.length == 6){
								double locx = player.getLocation().getX();
								double locy = player.getLocation().getY();
								double locz = player.getLocation().getZ();
									
								try {
									locx = Integer.parseInt(args[3]);
									locy = Integer.parseInt(args[4]);
									locz = Integer.parseInt(args[5]);
								} catch (Exception e) {
								}
								loc.setX(locx);
								loc.setY(locy);
								loc.setZ(locz);
								MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
								entity.setSize(size);
								entity.setMaxHealth(health);
								player.sendMessage(ChatColor.GREEN + "A Giant Magma Cube has been spawned");
							}
						}
					}
					else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
				}
			}
			if (args[0].equalsIgnoreCase("version")){
				Player player = null;
				PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("Giants").getDescription();
				if (sender instanceof Player){
					player = (Player) sender;
					if((player.hasPermission("giants.version")) || (player.isOp())){
						player.sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					}
					else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
				}
				else{
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Giants] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
				}
			}
		}
		return true;
	}
}