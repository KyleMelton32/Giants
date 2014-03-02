package main.java.me.Mammothskier.Giants;

import main.java.me.Mammothskier.Giants.files.Files;
import main.java.me.Mammothskier.Giants.utils.API;

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
								} catch (Exception e) {
								}
								location.setX(locx);
								try {
									locy = Integer.parseInt(args[3]);
								} catch (Exception e) {
								}
								location.setY(locy);
								try {
									locz = Integer.parseInt(args[4]);
								} catch (Exception e) {
								}
								location.setZ(locz);
								Location loc = location;
								loc.getWorld().spawnEntity(location, EntityType.GIANT);
								player.sendMessage(ChatColor.GREEN + "A Giant has been spawned at x:" + locx + " y:" + locy + "z:" + locz);
							}
						}
						if(args[1].equalsIgnoreCase("slime")){
							Location loc = (Location) player.getEyeLocation();
							Location location = loc;
							String string = API.getFileHandler().getProperty(Files.SLIME, "Giants Configuration.Giant Stats.Size");
							int size;
							try {
								size = Integer.parseInt(string);
							} catch (Exception e) {
								size = 12;
							}
							if(args.length ==  2){
								Slime entity = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
								entity.setSize(size);
								player.sendMessage(ChatColor.GREEN + "A Giant Slime has been spawned");
							}
							if(args.length == 5){
								double locx = player.getLocation().getX();
								double locy = player.getLocation().getY();
								double locz = player.getLocation().getZ();
									
								try {
									locx = Integer.parseInt(args[2]);
								} catch (Exception e) {
								}
								loc.setX(locx);
								try {
									locy = Integer.parseInt(args[3]);
								} catch (Exception e) {
								}
								loc.setY(locy);
								try {
									locz = Integer.parseInt(args[4]);
								} catch (Exception e) {
								}
								loc.setZ(locz);
								Slime entity = (Slime) loc.getWorld().spawnEntity(location, EntityType.SLIME);
								entity.setSize(size);
								player.sendMessage(ChatColor.GREEN + "A Giant Slime has been spawned");
							}
						}
						if(args[1].equalsIgnoreCase("magma") && args[2].equalsIgnoreCase("cube")){
							Location loc = (Location) player.getEyeLocation();
							Location location = loc;
							String string = API.getFileHandler().getProperty(Files.MAGMACUBE, "Giants Configuration.Giant Stats.Size");
							int size;
							try {
								size = Integer.parseInt(string);
							} catch (Exception e) {
								size = 12;
							}
							if(args.length == 3){
								MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
								entity.setSize(size);
								player.sendMessage(ChatColor.GREEN + "A Giant Magma Cube has been spawned");
							}
							if(args.length == 6){
								double locx = player.getLocation().getX();
								double locy = player.getLocation().getY();
								double locz = player.getLocation().getZ();
									
								try {
									locx = Integer.parseInt(args[3]);
								} catch (Exception e) {
								}
								loc.setX(locx);
								try {
									locy = Integer.parseInt(args[4]);
								} catch (Exception e) {
								}
								loc.setY(locy);
								try {
									locz = Integer.parseInt(args[5]);
								} catch (Exception e) {
								}
								loc.setZ(locz);
								MagmaCube entity = (MagmaCube) loc.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
								entity.setSize(size);
								player.sendMessage(ChatColor.GREEN + "A Giant Magma Cube has been spawned");
							}
						}
					}
				}
			}
		}
		return true;
	}
}
