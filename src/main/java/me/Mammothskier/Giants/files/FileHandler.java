package main.java.me.Mammothskier.Giants.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import main.java.me.Mammothskier.Giants.Giants;

public class FileHandler {
	private final Giants _giants;
	private final HashMap<Config, YamlConfiguration> _configurations;
	private final HashMap<Biomes, YamlConfiguration> _biomeconfigurations;

	public FileHandler(Giants giants) {
		_giants = giants;
		_configurations = new HashMap<Config, YamlConfiguration>();
		_biomeconfigurations = new HashMap<Biomes, YamlConfiguration>();
		loadDefaultDrop();
		loadWorlds();
		loadConfig();
		loadBiomes();
	}

	private List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : _giants.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}

	private String[] loadDefaultDrop() {
		String[] drops = { "1:0:1" };
		return drops;
	}

	@SuppressWarnings("unused")
	private String[] loadDefaultDeathMessage() {
		String[] message = { "Player got stomped by a Giant" };
		return message;
	}

	public void loadConfig() {
		for (Config file : Config.values()) {
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
				this.createConfig(file, confFile);
			}
		}
	}
	
	public void loadBiomes() {
		for (Biomes file : Biomes.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_biomeconfigurations.containsKey(file)) {
					_biomeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_biomeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createBiomes(file, confFile);
			}
		}
	}

	private void createConfig(Config config, File file) {
		switch (config) {
			case CONFIG:
				YamlConfiguration Config = YamlConfiguration.loadConfiguration(file);
				Config.set("Giants Configuration.Giant Stats.Health", new Integer(100));
				Config.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
				Config.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop()));
				Config.set("Giants Configuration.Attack Mechanisms.Lightning Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Throw Boulder Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Stomp Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100));
				Config.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Baby Zombies", false);
				Config.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Zombies to Spawn", new Integer(3));
				Config.set("Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5));
				Config.set("Giants Configuration.Spawn Settings.Chance", new Integer(10));
				Config.set("Giants Configuration.Spawn Settings.Worlds", loadWorlds());
				Config.set("Giants Configuration.Sounds.Fire Attack", true);
				Config.set("Giants Configuration.Sounds.Throw Boulder Attack", true);
				Config.set("Giants Configuration.Sounds.Kick Attack", true);
				Config.set("Giants Configuration.Sounds.Death", true);
				Config.set("Giants Configuration.Debug Mode", false);
				Config.set("Giants Configuration.Language.Debug Message", "&2A Giant has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(config, Config);
				break;
		}
	}
	
	private void createBiomes(Biomes biome, File file) {
		switch (biome) {
			case BIOMES:
				YamlConfiguration Biomes = YamlConfiguration.loadConfiguration(file);
				Biomes.set("Giants Configuration.Biome Settings.Swampland.Swampland", true);
				Biomes.set("Giants Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Forest.Forest", true);
				Biomes.set("Giants Configuration.Biome Settings.Forest.Forest Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Taiga", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				Biomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Plains.Plains", true);
				Biomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains", true);
				Biomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				Biomes.set("Giants Configuration.Biome Settings.Plains.Sunflower Plains", true);
				Biomes.set("Giants Configuration.Biome Settings.Ocean.Ocean", false);
				Biomes.set("Giants Configuration.Biome Settings.Ocean.Deep Ocean", false);
				Biomes.set("Giants Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				Biomes.set("Giants Configuration.Biome Settings.River.River", true);
				Biomes.set("Giants Configuration.Biome Settings.River.Frozen River", true);
				Biomes.set("Giants Configuration.Biome Settings.Beach.Beach", true);
				Biomes.set("Giants Configuration.Biome Settings.Beach.Stone Beach", true);
				Biomes.set("Giants Configuration.Biome Settings.Beach.Cold Beach", true);
				Biomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				Biomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				Biomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				Biomes.set("Giants Configuration.Biome Settings.Desert.Desert", true);
				Biomes.set("Giants Configuration.Biome Settings.Desert.Desert Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Desert.Desert Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Jungle.Jungle", true);
				Biomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				Biomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				Biomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Savanna.Savanna", true);
				Biomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				Biomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				Biomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", true);
				Biomes.set("Giants Configuration.Biome Settings.Other.Hell", false);
				Biomes.set("Giants Configuration.Biome Settings.Other.Sky", false);
				try {
					Biomes.save(file);
				} catch (IOException e) {
				}
				_biomeconfigurations.put(biome, Biomes);
				break;
		}
	}

	public String getProperty(Config file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			String prop = conf.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
	
	public String getBiomesProperty(Biomes file, String path) {
		FileConfiguration biome = _biomeconfigurations.get(file);

		if (biome != null) {
			String prop = biome.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			biome.set(path, null);
		}
		return null;
	}

	public List<String> getPropertyList(Config file, String path) {
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
