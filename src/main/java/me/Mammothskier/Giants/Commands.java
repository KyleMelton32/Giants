package main.java.me.Mammothskier.Giants;

import main.java.me.Mammothskier.Giants.utils.API;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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
				sender.sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" + "/giants reload:  Reloads the config file.\n" + "/giants spawn <x> <y> <z>");
				} else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				}
				return true;
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					Player player = null;
					if (sender instanceof Player) {
						player = (Player) sender;
						if ((player.hasPermission("giants.reload")) || (player.isOp())) {
							API.getFileHandler().loadConfig();
							sender.sendMessage(ChatColor.GREEN + "Giants config file reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					} else {
						API.getFileHandler().loadConfig();
						_giants.log.info("Giants config file reloaded.");
					}
				}
				if (args[0].equalsIgnoreCase("spawn")){
					Player player = null;
					if (sender instanceof Player){
						player = (Player) sender;
						if((player.hasPermission("giants.spawn")) || (player.isOp())){
							if (args.length == 1){
								Location loc = (Location) player.getLocation();
								Location location = loc;
								loc.getWorld().spawnEntity(location, EntityType.GIANT);
								player.sendMessage(ChatColor.GREEN + "A Giant has been spawned");
							}
							if (args.length == 4){
								Location location = player.getLocation();
								double locx = player.getLocation().getX();
								double locy = player.getLocation().getY();
								double locz = player.getLocation().getZ();
								
								try {
									locx = Integer.parseInt(args[1]);
								} catch (Exception e) {
									player.sendMessage("Invalid arguements");
								}
								location.setX(locx);
								try {
									locy = Integer.parseInt(args[2]);
								} catch (Exception e) {
									player.sendMessage("Invalid arguements");
								}
								location.setY(locy);
								try {
									locz = Integer.parseInt(args[3]);
								} catch (Exception e) {
									player.sendMessage("Invalid arguements");
								}
								location.setZ(locz);
								Location loc = location;
								loc.getWorld().spawnEntity(location, EntityType.GIANT);
								player.sendMessage(ChatColor.GREEN + "A Giant has been spawned");
							}
						}
						else{
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					}
				}
			}
		}
		return true;
	}
}
