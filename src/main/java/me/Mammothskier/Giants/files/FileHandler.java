package me.Mammothskier.Giants.files;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.Mammothskier.Giants.Giants;

public class FileHandler {
	private final Giants _giants;
	private final HashMap<Config, YamlConfiguration> _configurations;
	private final HashMap<GiantBiomes, YamlConfiguration> _giantbiomeconfigurations;
	private final HashMap<Giant, YamlConfiguration> _giantconfigurations;
	private final HashMap<SlimeBiomes, YamlConfiguration> _slimebiomeconfigurations;
	private final HashMap<Slime, YamlConfiguration> _slimeconfigurations;
	private final HashMap<MagmaCubeBiomes, YamlConfiguration> _magmacubebiomeconfigurations;
	private final HashMap<MagmaCube, YamlConfiguration> _magmacubeconfigurations;
	public FileHandler(Giants giants) {
		_giants = giants;
		_configurations = new HashMap<Config, YamlConfiguration>();
		_giantbiomeconfigurations = new HashMap<GiantBiomes, YamlConfiguration>();
		_giantconfigurations = new HashMap<Giant, YamlConfiguration>();
		_slimebiomeconfigurations = new HashMap<SlimeBiomes, YamlConfiguration>();
		_slimeconfigurations = new HashMap<Slime, YamlConfiguration>();
		_magmacubebiomeconfigurations = new HashMap<MagmaCubeBiomes, YamlConfiguration>();
		_magmacubeconfigurations = new HashMap<MagmaCube, YamlConfiguration>();
		loadDefaultDrop();
		loadWorlds();
		loadFiles();
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
	public void loadFiles(){
		loadConfig();
		loadGiant();
		loadGiantBiomes();
		loadSlime();			
		loadSlimeBiomes();
		loadMagmaCube();
		loadMagmaCubeBiomes();
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
	
	public void loadGiantBiomes() {
		for (GiantBiomes file : GiantBiomes.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_giantbiomeconfigurations.containsKey(file)) {
					_giantbiomeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_giantbiomeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createGiantBiomes(file, confFile);
			}
		}
	}
	
	public void loadGiant() {
		for (Giant file : Giant.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_giantconfigurations.containsKey(file)) {
					_giantconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_giantconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createGiant(file, confFile);
			}
		}
	}
	
	public void loadSlimeBiomes() {
		for (SlimeBiomes file : SlimeBiomes.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_slimebiomeconfigurations.containsKey(file)) {
					_slimebiomeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_slimebiomeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createSlimeBiomes(file, confFile);
			}
		}
	}
	
	public void loadSlime() {
		for (Slime file : Slime.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_slimeconfigurations.containsKey(file)) {
					_slimeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_slimeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createSlime(file, confFile);
			}
		}
	}
	
	public void loadMagmaCubeBiomes() {
		for (MagmaCubeBiomes file : MagmaCubeBiomes.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_magmacubebiomeconfigurations.containsKey(file)) {
					_magmacubebiomeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_magmacubebiomeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createMagmaCubeBiomes(file, confFile);
			}
		}
	}
	
	public void loadMagmaCube() {
		for (MagmaCube file : MagmaCube.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_magmacubeconfigurations.containsKey(file)) {
					_magmacubeconfigurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_magmacubeconfigurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createMagmaCube(file, confFile);
			}
		}
	}

	private void createConfig(Config config, File file) {
		switch (config) {
			case CONFIG:
				YamlConfiguration Config = YamlConfiguration.loadConfiguration(file);
				Config.set("Giants Configuration.Entities.Giant", false);
				Config.set("Giants Configuration.Entities.Giant Slime", false);
				Config.set("Giants Configuration.Entities.Giant Magma Cube", false);
				Config.set("Giants Configuration.Debug Mode", false);
				Config.set("Giants Configuration.Language.Debug Message", "&2A {entity} has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(config, Config);
				break;
		}
	}
	
	private void createGiant(Giant giant, File file) {
		switch (giant) {
			case GIANT:
				YamlConfiguration Giant = YamlConfiguration.loadConfiguration(file);
				Giant.set("Giants Configuration.Giant Stats.Health", new Integer(100));
				Giant.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
				Giant.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop()));
				Giant.set("Giants Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				Giant.set("Giants Configuration.Attack Mechanisms.Lightning Attack", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Throw Boulder Attack", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Stomp Attack", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Giant.set("Giants Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Giant.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100));
				Giant.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Enabled", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Baby Zombies", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Zombie Attack.Zombies to Spawn", new Integer(3));
				Giant.set("Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false);
				Giant.set("Giants Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5));
				Giant.set("Giants Configuration.Spawn Settings.Chance", new Integer(10));
				Giant.set("Giants Configuration.Spawn Settings.Worlds", loadWorlds());
				Giant.set("Giants Configuration.Sounds.Fire Attack", true);
				Giant.set("Giants Configuration.Sounds.Throw Boulder Attack", true);
				Giant.set("Giants Configuration.Sounds.Kick Attack", true);
				Giant.set("Giants Configuration.Sounds.Death", true);
				try {
					Giant.save(file);
				} catch (IOException e) {
				}
				_giantconfigurations.put(giant, Giant);
				break;
		}
	}
	
	private void createGiantBiomes(GiantBiomes giantbiome, File file) {
		switch (giantbiome) {
			case GIANTBIOMES:
				YamlConfiguration GiantBiomes = YamlConfiguration.loadConfiguration(file);
				GiantBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Forest.Forest", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Forest.Forest Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Plains.Plains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Plains.Sunflower Plains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Ocean.Ocean", false);
				GiantBiomes.set("Giants Configuration.Biome Settings.Ocean.Deep Ocean", false);
				GiantBiomes.set("Giants Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.River.River", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.River.Frozen River", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Beach.Beach", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Beach.Stone Beach", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Beach.Cold Beach", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Desert.Desert", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", true);
				GiantBiomes.set("Giants Configuration.Biome Settings.Other.Hell", false);
				GiantBiomes.set("Giants Configuration.Biome Settings.Other.Sky", false);
				try {
					GiantBiomes.save(file);
				} catch (IOException e) {
				}
				_giantbiomeconfigurations.put(giantbiome, GiantBiomes);
				break;
		}
	}	
	
	private void createSlime(Slime slime, File file) {
		switch (slime) {
			case SLIME:
				YamlConfiguration Slime = YamlConfiguration.loadConfiguration(file);
				Slime.set("Giants Configuration.Spawn Settings.Chance", new Integer(10));
				Slime.set("Giants Configuration.Spawn Settings.Worlds", loadWorlds());				
				Slime.set("Giants Configuration.Giant Stats.Size", new Integer(12));
				Slime.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
				Slime.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop()));
				Slime.set("Giants Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				Slime.set("Giants Configuration.Sounds.Death", true);
				try {
					Slime.save(file);
				} catch (IOException e) {
				}
				_slimeconfigurations.put(slime, Slime);
				break;
		}
	}
	
	private void createSlimeBiomes(SlimeBiomes slimebiome, File file) {
		switch (slimebiome) {
			case SLIMEBIOMES:
				YamlConfiguration SlimeBiomes = YamlConfiguration.loadConfiguration(file);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Forest.Forest", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Forest.Forest Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Plains.Plains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Plains.Sunflower Plains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Ocean.Ocean", false);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Ocean.Deep Ocean", false);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.River.River", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.River.Frozen River", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Beach.Beach", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Beach.Stone Beach", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Beach.Cold Beach", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", true);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Other.Hell", false);
				SlimeBiomes.set("Giants Configuration.Biome Settings.Other.Sky", false);
				try {
					SlimeBiomes.save(file);
				} catch (IOException e) {
				}
				_slimebiomeconfigurations.put(slimebiome, SlimeBiomes);
				break;
		}
	}
	
	private void createMagmaCube(MagmaCube magmacube, File file) {
		switch (magmacube) {
			case MAGMACUBE:
				YamlConfiguration MagmaCube = YamlConfiguration.loadConfiguration(file);
				MagmaCube.set("Giants Configuration.Spawn Settings.Chance", new Integer(10));
				MagmaCube.set("Giants Configuration.Spawn Settings.Worlds", loadWorlds());	
				MagmaCube.set("Giants Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				MagmaCube.set("Giants Configuration.Giant Stats.Size", new Integer(12));
				MagmaCube.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
				MagmaCube.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop()));
				MagmaCube.set("Giants Configuration.Sounds.Death", true);
				try {
					MagmaCube.save(file);
				} catch (IOException e) {
				}
				_magmacubeconfigurations.put(magmacube, MagmaCube);
				break;
		}
	}
	
	private void createMagmaCubeBiomes(MagmaCubeBiomes magmacubebiome, File file) {
		switch (magmacubebiome) {
			case MAGMACUBEBIOMES:
				YamlConfiguration MagmaCubeBiomes = YamlConfiguration.loadConfiguration(file);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Swampland.Swampland Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Forest.Forest", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Forest.Forest Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Taiga Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Cold Taiga Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Taiga Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Plains.Plains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Plains.Ice Plains Spikes", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Plains.Sunflower Plains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Ocean.Ocean", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Ocean.Deep Ocean", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Ocean.Frozen Ocean", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.River.River", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.River.Frozen River", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Beach.Beach", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Beach.Stone Beach", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Beach.Cold Beach", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Island", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mushroom Island.Mushroom Shore", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Desert.Desert Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Jungle.Jungle Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Bryce", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", true);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Other.Hell", false);
				MagmaCubeBiomes.set("Giants Configuration.Biome Settings.Other.Sky", false);
				try {
					MagmaCubeBiomes.save(file);
				} catch (IOException e) {
				}
				_magmacubebiomeconfigurations.put(magmacubebiome, MagmaCubeBiomes);
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
	
	public String getGiantBiomesProperty(GiantBiomes file, String path) {
		FileConfiguration biome = _giantbiomeconfigurations.get(file);

		if (biome != null) {
			String prop = biome.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			biome.set(path, null);
		}
		return null;
	}	
	
	public String getGiantProperty(Giant file, String path) {
		FileConfiguration giant = _giantconfigurations.get(file);

		if (giant != null) {
			String prop = giant.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			giant.set(path, null);
		}
		return null;
	}
	
	public String getSlimeBiomesProperty(SlimeBiomes file, String path) {
		FileConfiguration biome = _slimebiomeconfigurations.get(file);

		if (biome != null) {
			String prop = biome.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			biome.set(path, null);
		}
		return null;
	}
	
	public String getSlimeProperty(Slime file, String path) {
		FileConfiguration slime = _slimeconfigurations.get(file);

		if (slime != null) {
			String prop = slime.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			slime.set(path, null);
		}
		return null;
	}
	
	public String getMagmaCubeBiomesProperty(MagmaCubeBiomes file, String path) {
		FileConfiguration biome = _magmacubebiomeconfigurations.get(file);

		if (biome != null) {
			String prop = biome.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			biome.set(path, null);
		}
		return null;
	}
	
	public String getMagmaCubeProperty(MagmaCube file, String path) {
		FileConfiguration magmacube = _magmacubeconfigurations.get(file);

		if (magmacube != null) {
			String prop = magmacube.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			magmacube.set(path, null);
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
	
	
	public List<String> getGiantPropertyList(Giant file, String path) {
		FileConfiguration conf = _giantconfigurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
	
	public List<String> getSlimePropertyList(Slime file, String path) {
		FileConfiguration conf = _slimeconfigurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
	
	public List<String> getMagmaCubePropertyList(MagmaCube file, String path) {
		FileConfiguration conf = _magmacubeconfigurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
}
