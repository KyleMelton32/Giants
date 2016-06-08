package me.Mammothskier.Giants.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Mammothskier.Giants.Files.Files;

public class FileHandler {
	public static FileHandler instance;
	private final HashMap<Files, YamlConfiguration> _configurations;
	
	public FileHandler() {
		instance = this;
		_configurations = new HashMap<Files, YamlConfiguration>();
		loadFiles();
	}

	public static List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : Bukkit.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}
	
	public static String getInstanceProperty(ConfigValues value) {
		return instance != null ? instance.getProperty(value) : null;
	}
	
/*	public static boolean checkDependencies(String plugin) {
		if (Bukkit.getPluginManager().getPlugin(plugin) != null) {
			return true;
		}
		return false;
	}*/
	
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
				/*Config.set("Giants Configuration.File Version", loadVersion());
				Config.set("Giants Configuration.Dependencies.BarAPI", checkDependencies("BarAPI"));
				Config.set("Giants Configuration.Entities.Giant Zombie", true);
				Config.set("Giants Configuration.Entities.Giant Slime", false);
				Config.set("Giants Configuration.Entities.Giant Lava Slime", false);
				Config.set("Giants Configuration.Entities.Giant Jockey.Warning.Bugs", new String("This entity of Giants is extremely experimental and does not have many features. Bugs may be present."));
				Config.set("Giants Configuration.Entities.Giant Jockey.Warning.Files", new String("Config files for this entity will NOT load unless enabled."));
				Config.set("Giants Configuration.Entities.Giant Jockey.Warning.Enabled", false);
				Config.set("Giants Configuration.Sounds", true);
				Config.set("Giants Configuration.Debug Mode.Enabled", false);
				Config.set("Giants Configuration.Debug Mode.Debug Message", "&2A {entity} has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");*/
				for (ConfigValues value : ConfigValues.values()) {
					if (value.getFile() == Files.CONFIG) {
						if (!value.isList())
							Config.set(value.getKey(), value.getValue());
						else 
							Config.set(value.getKey(), value.getValues());
					}
				}
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Config);
				break;
			case BIOMES:
				YamlConfiguration Biomes = YamlConfiguration.loadConfiguration(file);
				/*Biomes.set("Giants Configuration.File Version", loadVersion());
				Biomes.set("Giants Configuration.Biome Settings.Swampland", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Forest", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Taiga", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Plains", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Extreme Hills", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Mushroom Island", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Desert", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Jungle", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Birch Forest", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Savanna", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Roofed Forest", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Mesa", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", loadEntities());
				Biomes.set("Giants Configuration.Biome Settings.Other.Ocean", "");
				Biomes.set("Giants Configuration.Biome Settings.Other.River", "");
				Biomes.set("Giants Configuration.Biome Settings.Other.Hell", "- Giant Lava Slime");
				Biomes.set("Giants Configuration.Biome Settings.Other.Sky", "");*/
				for (ConfigValues value : ConfigValues.values()) {
					if (value.getFile() == Files.BIOMES) {
						if (!value.isList())
							Biomes.set(value.getKey(), value.getValue());
						else 
							Biomes.set(value.getKey(), value.getValues());
					}
				}
				try {
					Biomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Biomes);
				break;
			case ENTITIES:
				YamlConfiguration entities = YamlConfiguration.loadConfiguration(file);
/*				entities.set("Entities Configuration.File Version", loadVersion());
// Worlds
				entities.set("Entities Configuration.Spawn Settings.Worlds.Giant Zombie", loadWorlds());
				entities.set("Entities Configuration.Spawn Settings.Worlds.Giant Slime", loadWorlds());
				entities.set("Entities Configuration.Spawn Settings.Worlds.Giant Lava Slime", loadWorlds());
// Spawn Chance
				entities.set("Entities Configuration.Spawn Settings.Chance.Giant Zombie", new Integer(10));
				entities.set("Entities Configuration.Spawn Settings.Chance.Giant Slime", new Integer(10));
				entities.set("Entities Configuration.Spawn Settings.Chance.Giant Lava Slime", new Integer(10));
// Size
				entities.set("Entities Configuration.Spawn Settings.Size.Giant Slime", new Integer(12));
				entities.set("Entities Configuration.Spawn Settings.Size.Giant Lava Slime", new Integer(12));
// Speed
				entities.set("Entities Configuration.Stats.Speed.Giant Zombie", new Integer(3));
// Health
				entities.set("Entities Configuration.Stats.Health.Giant Zombie", new Integer(100));
				entities.set("Entities Configuration.Stats.Health.Giant Slime", new Integer(100));
				entities.set("Entities Configuration.Stats.Health.Giant Lava Slime", new Integer(100));
// Experience
				entities.set("Entities Configuration.Stats.Experience.Giant Zombie", new Integer(5));
				entities.set("Entities Configuration.Stats.Experience.Giant Slime", new Integer(5));
				entities.set("Entities Configuration.Stats.Experience.Giant Lava Slime", new Integer(5));
// Armour
				entities.set("Entities Configuration.Stats.Equipped Armour.Giant Zombie.Items", 
						new String("chainmail_helmet:chainmail_chestplate:chainmail_leggings:chainmail_boots:diamond_sword"));
				entities.set("Entities Configuration.Stats.Equipped Armour.Giant Zombie.Equipped Item Drop Rate", new Float(8.5));
// Drops
				entities.set("Entities Configuration.Stats.Drops.Enable Drop Manager", true);
				entities.set("Entities Configuration.Stats.Drops.Giant Zombie", Arrays.asList(loadDefaultDrop("Giant Zombie")));
				entities.set("Entities Configuration.Stats.Drops.Giant Slime", Arrays.asList(loadDefaultDrop("Giant Slime")));
				entities.set("Entities Configuration.Stats.Drops.Giant Lava Slime", Arrays.asList(loadDefaultDrop("Giant Lava Slime")));
// BarAPI
				entities.set("Entities Configuration.Stats.BarAPI.Display Name.Giant Zombie", "&2Giant Zombie");
				entities.set("Entities Configuration.Stats.BarAPI.Display Name.Giant Slime", "&2Giant Slime Size {size}");
				entities.set("Entities Configuration.Stats.BarAPI.Display Name.Giant Lava Slime", "&2Giant Lava Slime Size {size}");
// Damage
				entities.set("Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Zombie", new Integer(10));
				entities.set("Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Slime", new Integer(10));
				entities.set("Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Lava Slime", new Integer(10));
				entities.set("Entities Configuration.Damage Settings.Fire.Allow Fire Damage.Giant Zombie", true);
				entities.set("Entities Configuration.Damage Settings.Fire.Allow Fire Damage.Giant Slime", true);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Zombie",false);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Slime",false);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Lava Slime",false);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Zombie", false);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Slime", false);
				entities.set("Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Lava Slime", false);*/
				for (ConfigValues value : ConfigValues.values()) {
					if (value.getFile() == Files.ENTITIES) {
						if (!value.isList())
							entities.set(value.getKey(), value.getValue());
						else 
							entities.set(value.getKey(), value.getValues());
					}
				}
				try {
					entities.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, entities);
				break;
			
			case ATTACKS:
				YamlConfiguration attacks = YamlConfiguration.loadConfiguration(file);
				/*attacks.set("Attacks Configuration.File Version", loadVersion());
				attacks.set("Attacks Configuration.Attack Mechanisms.Lightning Attack", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Stomp Attack.Enabled", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1));
				attacks.set("Attacks Configuration.Attack Mechanisms.Stomp Attack.Light Fire", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Lava Attack", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Kick Attack.Enabled", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				attacks.set("Attacks Configuration.Attack Mechanisms.Fire Attack.Enabled", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				attacks.set("Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100));
				attacks.set("Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", loadEntities());
				attacks.set("Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1));
				attacks.set("Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Enabled", Arrays.asList("Giant Zombie"));
				attacks.set("Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies", false);
				attacks.set("Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn", new Integer(3));
				attacks.set("Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Health", new Integer(20));
				attacks.set("Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false);
				attacks.set("Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies", false);
				attacks.set("Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5));
				attacks.set("Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Health", new Integer(20));*/
				for (ConfigValues value : ConfigValues.values()) {
					if (value.getFile() == Files.ATTACKS) {
						if (!value.isList())
							attacks.set(value.getKey(), value.getValue());
						else 
							attacks.set(value.getKey(), value.getValues());
					}
				}
				try {
					attacks.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, attacks);
				break;
			case JOCKEY:
				if (getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Enabled").equalsIgnoreCase("true")) {
					YamlConfiguration Jockey = YamlConfiguration.loadConfiguration(file);
					Jockey.set("Jockey Configuration.File Version", FileSetup.loadVersion());
					Jockey.set("Jockey Configuration", new String("This entity of Giants is extremely experimental and does not have many features"));
					Jockey.set("Jockey Configuration.Spawn Settings.Worlds", loadWorlds());
					for (ConfigValues value : ConfigValues.values()) {
						if (value.getFile() == Files.JOCKEY) {
							if (!value.isList())
								Jockey.set(value.getKey(), value.getValue());
							else 
								Jockey.set(value.getKey(), value.getValues());
						}
					}
					try {
						Jockey.save(file);
					} catch (IOException e) {
					}
					_configurations.put(files, Jockey);
				}
				break;
			case JOCKEYBIOMES:
				if (getProperty(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Enabled").equalsIgnoreCase("true")) {
					YamlConfiguration JockeyBiomes = YamlConfiguration.loadConfiguration(file);
/*					JockeyBiomes.set("Giants Configuration.File Version", FileSetup.loadVersion());
					JockeyBiomes.set("Giants Configuration.Biome Settings.Swampland", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Forest", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Taiga", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Plains", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Extreme Hills", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Mushroom Island", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Desert", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Jungle", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Birch Forest", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Savanna", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Roofed Forest", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Mesa", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.Small Mountains", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.Ice Mountains", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.Ocean", false);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.River", false);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.Hell", true);
					JockeyBiomes.set("Giants Configuration.Biome Settings.Other.Sky", true);*/
					for (ConfigValues value : ConfigValues.values()) {
						if (value.getFile() == Files.JOCKEYBIOMES) {
							if (!value.isList())
								JockeyBiomes.set(value.getKey(), value.getValue());
							else 
								JockeyBiomes.set(value.getKey(), value.getValues());
						}
					}
					try {
						JockeyBiomes.save(file);
					} catch (IOException e) {
					}
					_configurations.put(files, JockeyBiomes);
				}
				break;
		default:
			break;
		}
	}

	@Deprecated
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
	@Deprecated
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
	
	/**
	 * Gets the property that is saved at the value location.
	 * @param ConfigValues value.
	 * @return String property from the config file the value is saved in.
	 */
	public String getProperty(ConfigValues value) {
		if (value == ConfigValues.soundsBoolean)
			return "false";
		FileConfiguration config = _configurations.get(value.getFile());
		if (config != null) {
			String property = config.getString(value.getKey(), "NULL");
			
			if (!property.equalsIgnoreCase("NULL"))
				return property;
			config.set(value.getKey(), value.getValue());
		}
		return null;
	}
	
	/**
	 * Gets the property list that is saved at the value location.
	 * @param ConfigValues value.
	 * @return List<String> propertylist from the config file the value is saved in.
	 */
	public List<String> getPropertyList(ConfigValues value) {
		FileConfiguration config = _configurations.get(value.getFile());
		if (config != null) {
			List<String> property = config.getStringList(value.getKey());
			
			if (!property.contains("NULL"))
				return property;
			config.set(value.getKey(), value.getValue());
		}
		return null;
	}
	
	/**
	 * Changes the property value at the value location.
	 * @param value - the value to change
	 * @param newValue the value to put in place of the current value
	 */
	public void setProperty(ConfigValues value, Object newValue) {
		FileConfiguration config = _configurations.get(value.getFile());
		if (config != null) {
			config.set(value.getKey(), newValue);
			try {
				config.save(new File(value.getFile().getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}