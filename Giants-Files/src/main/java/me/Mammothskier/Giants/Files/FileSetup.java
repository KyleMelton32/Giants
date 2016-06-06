package me.Mammothskier.Giants.Files;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;

public class FileSetup {
	
	public static String loadVersion() {		
		PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("Giants").getDescription();
		String version = pdf.getVersion();
		if (version == null) {
			return null;
		}
		return version;
	}

	public static List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : Bukkit.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}
	

	public static String[] loadDefaultDrop(String arg) {
		String[] drops = null;
		switch(arg){
			case "Giant Zombie":
				drops = new String[]{ "1-0-0;1;100/100;GIANT STONE;Dropped by a Giant Zombie" };
				break;
			case "Giant Slime":
				drops = new String[]{ "1-0-0;1;100/100;4-12;GIANT STONE;Dropped by a Giant Slime" };
				break;
			case "Giant Lava Slime":
				drops = new String[]{ "1-0-0;1;100/100;4-12;GIANT STONE;Dropped by a Giant Lava Slime" };
				break;
			default:
				break;
		}
		return drops;
	}
	
	public static List<String> loadEntities() {
		List<String> list = new ArrayList<String>();
		list.add("Giant Zombie");
		list.add("Giant Slime");
		list.add("Giant Lava Slime");
		return list;
	}
	
	public static boolean checkDependencies(String plugin) {
		if (Bukkit.getPluginManager().getPlugin(plugin) != null) {
			return true;
		}
		return false;
	}
}
