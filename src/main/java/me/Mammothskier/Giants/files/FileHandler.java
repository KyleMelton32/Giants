package me.Mammothskier.Giants.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import me.Mammothskier.Giants.files.Files;
import me.Mammothskier.Giants.Giants;

public class FileHandler {
	private final Giants _giants;
	private final HashMap<Files, YamlConfiguration> _configurations;
	
	public FileHandler(Giants giants) {
		_giants = giants;
		_configurations = new HashMap<Files, YamlConfiguration>();
		loadWorlds();
		loadFiles();
		loadVersion();
		loadDefaultDrop("Giant");
		loadDefaultDrop("Slime");
		loadDefaultDrop("MagmaCube");
	}
	
	private String loadVersion() {		
		PluginDescriptionFile pdf = _giants.getDescription();
		String version = pdf.getVersion();
		if (version == null) {
			return null;
		}
		return version;
	}

	public List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : _giants.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}

	private String[] loadDefaultDrop(String arg) {
		String[] drops = null;
		switch(arg){
			case "Giant":
				drops = new String[]{ "1-0-0;1;100/100;GIANT STONE;Dropped by a Giant" };
				break;
			case "Slime":
				drops = new String[]{ "1-0-0;1;100/100;4-12;GIANT STONE;Dropped by a Giant Slime" };
				break;
			case "MagmaCube":
				drops = new String[]{ "1-0-0;1;100/100;4-12;GIANT STONE;Dropped by a Giant Magma Cube" };
				break;
			default:
				break;
		}
		return drops;
	}
	
	public void loadFiles() {
		for (Files file : Files.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_configurations.containsKey(file)) {
					_configurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_configurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createFiles(file, confFile);
			}
		}
	}

	private void createFiles(Files files, File file) {
		switch (files) {
			case CONFIG:
				YamlConfiguration Config = YamlConfiguration.loadConfiguration(file);
				Config.set("Giants Configuration.File Version", loadVersion());
				Config.set("Giants Configuration.Entities.Giant", true);
				Config.set("Giants Configuration.Entities.Giant Slime", false);
				Config.set("Giants Configuration.Entities.Giant Magma Cube", false);
				Config.set("Giants Configuration.Entities.Giant Jockey", false);
				Config.set("Giants Configuration.Debug Mode.Enabled", false);
				Config.set("Giants Configuration.Debug Mode.Debug Message", "&2A {entity} has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Config);
				break;
			case GIANT:
				YamlConfiguration Giant = YamlConfiguration.loadConfiguration(file);
				Giant.set("Giants Configuration.File Version", loadVersion());
				Giant.set("Giant Configuration.Spawn Settings.Chance", new Integer(10));
				Giant.set("Giant Configuration.Spawn Settings.Worlds", loadWorlds());
				Giant.set("Giant Configuration.Giant Stats.Health", new Integer(100));
				Giant.set("Giant Configuration.Giant Stats.Experience", new Integer(5));
				Giant.set("Giant Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop("Giant")));
				Giant.set("Giant Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				Giant.set("Giant Configuration.Damage Settings.Fire.Allow Fire Damage", true);
				Giant.set("Giant Configuration.Damage Settings.Block Damage.Allow Suffocation", false);
				Giant.set("Giant Configuration.Damage Settings.Block Damage.Allow Cacti Damage", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Lightning Attack", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Stomp Attack.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1));
				Giant.set("Giant Configuration.Attack Mechanisms.Stomp Attack.Light Fire", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Giant.set("Giant Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Giant.set("Giant Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100));
				Giant.set("Giant Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1));
				Giant.set("Giant Configuration.Attack Mechanisms.Shrapnel Attack.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn", new Integer(3));
				Giant.set("Giant Configuration.Attack Mechanisms.Shrapnel Attack.Health", new Integer(20));
				Giant.set("Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies", false);
				Giant.set("Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5));
				Giant.set("Giant Configuration.Attack Mechanisms.Spawn Zombies On Death.Health", new Integer(20));
				Giant.set("Giant Configuration.Sounds.Stomp Attack", true);
				Giant.set("Giant Configuration.Sounds.Fire Attack", true);
				Giant.set("Giant Configuration.Sounds.Throw Boulder Attack", true);
				Giant.set("Giant Configuration.Sounds.Kick Attack", true);
				Giant.set("Giant Configuration.Sounds.Shrapnel Attack", true);
				Giant.set("Giant Configuration.Sounds.Death", true);
				try {
					Giant.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Giant);
				break;
			case GIANTBIOMES:
				YamlConfiguration GiantBiomes = YamlConfiguration.loadConfiguration(file);
				GiantBiomes.set("Giants Configuration.File Version", loadVersion());
				GiantBiomes.set("Giant Configuration.Biome Settings.Swampland.Swampland", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Forest.Forest", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Forest.Forest Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Taiga", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Taiga Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Cold Taiga", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Mega Taiga", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Plains.Plains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Plains.Ice Plains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Plains.Sunflower Plains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Ocean.Ocean", false);
				GiantBiomes.set("Giant Configuration.Biome Settings.Ocean.Deep Ocean", false);
				GiantBiomes.set("Giant Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.River.River", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.River.Frozen River", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Beach.Beach", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Beach.Stone Beach", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Beach.Cold Beach", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Desert.Desert", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Desert.Desert Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Desert.Desert Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Jungle.Jungle", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Jungle.Jungle Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Savanna.Savanna", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Other.Small Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Other.Ice Mountains", true);
				GiantBiomes.set("Giant Configuration.Biome Settings.Other.Hell", false);
				GiantBiomes.set("Giant Configuration.Biome Settings.Other.Sky", false);
				try {
					GiantBiomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, GiantBiomes);
				break;
			case SLIME:
				YamlConfiguration Slime = YamlConfiguration.loadConfiguration(file);
				Slime.set("Slime Configuration.File Version", loadVersion());
				Slime.set("Slime Configuration.Spawn Settings.Chance", new Integer(10));
				Slime.set("Slime Configuration.Spawn Settings.Worlds", loadWorlds());
				Slime.set("Slime Configuration.Spawn Settings.Worlds", loadWorlds());	
				Slime.set("Slime Configuration.Slime Stats.Size", new Integer(12));
				Slime.set("Slime Configuration.Slime Stats.Health", new Integer(100));
				Slime.set("Slime Configuration.Slime Stats.Experience", new Integer(5));
				Slime.set("Slime Configuration.Slime Stats.Drops", Arrays.asList(loadDefaultDrop("Slime")));
				Slime.set("Slime Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				Slime.set("Slime Configuration.Damage Settings.Fire.Allow Fire Damage", true);
				Slime.set("Slime Configuration.Damage Settings.Block Damage.Allow Suffocation",false);
				Slime.set("Slime Configuration.Damage Settings.Block Damage.Allow Cacti Damage", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Lightning Attack", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Stomp Attack.Enabled", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1));
				Slime.set("Slime Configuration.Attack Mechanisms.Stomp Attack.Light Fire", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Slime.set("Slime Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Slime.set("Slime Configuration.Attack Mechanisms.Fire Attack.Ticks for Slime", new Integer(100));
				Slime.set("Slime Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", false);
				Slime.set("Slime Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1));
				Slime.set("Slime Configuration.Attack Mechanisms.Poison Attack.Enabled", true);
				Slime.set("Slime Configuration.Attack Mechanisms.Poison Attack.length", new Integer(5));
				Slime.set("Slime Configuration.Sounds.Stomp Attack", true);
				Slime.set("Slime Configuration.Sounds.Fire Attack", true);
				Slime.set("Slime Configuration.Sounds.Throw Boulder Attack", true);
				Slime.set("Slime Configuration.Sounds.Kick Attack", true);
				Slime.set("Slime Configuration.Sounds.Death", true);
				try {
					Slime.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Slime);
				break;
			case SLIMEBIOMES:
				YamlConfiguration SlimeBiomes = YamlConfiguration.loadConfiguration(file);
				SlimeBiomes.set("Slime Configuration.File Version", loadVersion());
				SlimeBiomes.set("Slime Configuration.Biome Settings.Swampland.Swampland", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Forest.Forest", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Forest.Forest Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Taiga", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Taiga Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Cold Taiga", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Mega Taiga", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Plains.Plains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Plains.Ice Plains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Plains.Sunflower Plains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Ocean.Ocean", false);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Ocean.Deep Ocean", false);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.River.River", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.River.Frozen River", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Beach.Beach", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Beach.Stone Beach", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Beach.Cold Beach", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Desert.Desert", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Desert.Desert Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Desert.Desert Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Jungle.Jungle", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Jungle.Jungle Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Savanna.Savanna", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Other.Small Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Other.Ice Mountains", true);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Other.Hell", false);
				SlimeBiomes.set("Slime Configuration.Biome Settings.Other.Sky", false);
				try {
					SlimeBiomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, SlimeBiomes);
				break;
			case MAGMACUBE:
				YamlConfiguration MagmaCube = YamlConfiguration.loadConfiguration(file);
				MagmaCube.set("Magma Cube Configuration.File Version", loadVersion());
				MagmaCube.set("Magma Cube Configuration.Spawn Settings.Chance", new Integer(10));
				MagmaCube.set("Magma Cube Configuration.Spawn Settings.Worlds", loadWorlds());
				MagmaCube.set("Magma Cube Configuration.Magma Cube Stats.Size", new Integer(12));
				MagmaCube.set("Magma Cube Configuration.Magma Cube Stats.Health", new Integer(100));
				MagmaCube.set("Magma Cube Configuration.Magma Cube Stats.Experience", new Integer(5));
				MagmaCube.set("Magma Cube Configuration.Magma Cube Stats.Drops",  Arrays.asList(loadDefaultDrop("MagmaCube")));
				MagmaCube.set("Magma Cube Configuration.Damage Settings.Arrows.Damage done by arrow", true);
				MagmaCube.set("Magma Cube Configuration.Damage Settings.Block Damage.Allow Suffocation", false);
				MagmaCube.set("Magma Cube Configuration.Damage Settings.Block Damage.Allow Cacti Damage", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Lightning Attack", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Stomp Attack.Enabled", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1));
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Stomp Attack.Light Fire", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Lava Attack", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Fire Attack.Ticks for Magma Cube", new Integer(100));
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", false);
				MagmaCube.set("Magma Cube Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1));
				MagmaCube.set("Magma Cube Configuration.Sounds.Fire Attack", true);
				MagmaCube.set("Magma Cube Configuration.Sounds.Throw Boulder Attack", true);
				MagmaCube.set("Magma Cube Configuration.Sounds.Kick Attack", true);
				MagmaCube.set("Magma Cube Configuration.Sounds.Lava Attack", true);
				MagmaCube.set("Magma Cube Configuration.Sounds.Death", true);
				try {
					MagmaCube.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, MagmaCube);
				break;
			case MAGMACUBEBIOMES:
				YamlConfiguration MagmaCubeBiomes = YamlConfiguration.loadConfiguration(file);
				MagmaCubeBiomes.set("Magma Cube Configuration.File Version", loadVersion());
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Swampland.Swampland", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Swampland.Swampland Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Forest.Forest", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Forest.Forest Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Taiga", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Taiga Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Taiga Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Cold Taiga", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Cold Taiga Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Cold Taiga Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Mega Taiga", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Mega Taiga Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Mega Spruce Taiga", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Plains.Plains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Plains.Ice Plains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Plains.Ice Plains Spikes", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Plains.Sunflower Plains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Ocean.Ocean", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Ocean.Deep Ocean", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Ocean.Frozen Ocean", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.River.River", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.River.Frozen River", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Beach.Beach", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Beach.Stone Beach", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Beach.Cold Beach", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Extreme Hills.Extreme Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mushroom Island.Mushroom Island", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mushroom Island.Mushroom Shore", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Desert.Desert", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Desert.Desert Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Desert.Desert Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Jungle.Jungle", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Jungle.Jungle Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Jungle.Jungle Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Birch Forest.Birch Forest", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Birch Forest.Birch Forest Hills", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Savanna.Savanna", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Savanna.Savanna Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Savanna.Savanna Plateau", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Roofed Forest.Roofed Forest", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa Bryce", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa Plateau", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa Plateau Forest", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Other.Small Mountains", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Other.Ice Mountains", true);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Other.Hell", false);
				MagmaCubeBiomes.set("Magma Cube Configuration.Biome Settings.Other.Sky", false);
				try {
					MagmaCubeBiomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, MagmaCubeBiomes);
				break;
			case JOCKEYBIOMES:
				YamlConfiguration JockeyBiomes = YamlConfiguration.loadConfiguration(file);
				JockeyBiomes.set("Jockey Configuration.File Version", loadVersion());
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Swampland.Swampland", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Swampland.Swampland Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Forest.Forest", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Forest.Forest Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Taiga", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Taiga Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Taiga Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Cold Taiga", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Cold Taiga Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Cold Taiga Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Mega Taiga", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Mega Taiga Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Mega Spruce Taiga", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Plains.Plains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Plains.Ice Plains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Plains.Ice Plains Spikes", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Plains.Sunflower Plains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Ocean.Ocean", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Ocean.Deep Ocean", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Ocean.Frozen Ocean", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.River.River", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.River.Frozen River", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Beach.Beach", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Beach.Stone Beach", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Beach.Cold Beach", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mushroom Island.Mushroom Island", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mushroom Island.Mushroom Shore", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Desert.Desert", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Desert.Desert Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Desert.Desert Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Jungle.Jungle", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Jungle.Jungle Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Jungle.Jungle Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Birch Forest.Birch Forest", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Hills", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Savanna.Savanna", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Savanna.Savanna Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Savanna.Savanna Plateau", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Roofed Forest.Roofed Forest", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa Bryce", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa Plateau", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Forest", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Other.Small Mountains", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Other.Ice Mountains", true);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Other.Hell", false);
				JockeyBiomes.set("Jockey Configuration.Biome Settings.Other.Sky", false);
				try {
					JockeyBiomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, JockeyBiomes);
				break;
		default:
			break;
		}
	}

	public String getProperty(Files file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			String prop = conf.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}

	public List<String> getPropertyList(Files file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
}