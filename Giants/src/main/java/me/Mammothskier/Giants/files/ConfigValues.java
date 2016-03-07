package me.Mammothskier.Giants.files;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

public enum ConfigValues {
	/** 
	 * value commons (any using ConfigValues(String key, String value) as a constructor should be here).
	 * These values will be put at the top of each file.
	 */
	fileVersion("Giants Configuration.File Version", loadVersion()),
	
	/**
	 * These file values will go in the config.yml file.
	 */
	dependencies(Files.CONFIG, "Giants Configuration.Dependencies.BarAPI", FileHandler.checkDependencies("BarAPI")),
	zombieBoolean(Files.CONFIG,"Giants Configuration.Entities.Giant Zombie", true),
	slimeBoolean(Files.CONFIG, "Giants Configuration.Entities.Giant Slime", false),
	lavaSlimeBoolean(Files.CONFIG, "Giants Configuration.Entities.Giant Lava Slime", false),
	jockeyWarningBugs(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Bugs", "This entity of Giants is extremely experimental and does not have many features. Bugs may be present."),
	jockeyWarningFiles(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Files", "Config files for this entity will NOT load unless enabled."),
	jockeyBoolean(Files.CONFIG, "Giants Configuration.Entities.Giant Jockey.Warning.Enabled", false),
	soundsBoolean(Files.CONFIG, "Giants Configuration.Sounds", true),
	debugBoolean(Files.CONFIG, "Giants Configuration.Debug Mode.Enabled", false),
	debugMessage(Files.CONFIG, "Giants Configuration.Debug Mode.Debug Message", "&2A {entity} has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z"),
	
	/**
	 * These file values will go in the biome.yml file.
	 */
	biomeSwampland(Files.BIOMES,"Giants Configuration.Biome Settings.Swampland", FileHandler.loadEntities()),
	biomeForest(Files.BIOMES, "Giants Configuration.Biome Settings.Forest", FileHandler.loadEntities()),
	biomeTaiga(Files.BIOMES, "Giants Configuration.Biome Settings.Taiga", FileHandler.loadEntities()),
	biomePlains(Files.BIOMES, "Giants Configuration.Biome Settings.Plains", FileHandler.loadEntities()),
	biomeExtremeHills(Files.BIOMES, "Giants Configuration.Biome Settings.Extreme Hills", FileHandler.loadEntities()),
	biomeMushroomIsland(Files.BIOMES, "Giants Configuration.Biome Settings.Mushroom Island", FileHandler.loadEntities()),
	biomeDesert(Files.BIOMES, "Giants Configuration.Biome Settings.Desert", FileHandler.loadEntities()),
	biomeJungle(Files.BIOMES, "Giants Configuration.Biome Settings.Jungle", FileHandler.loadEntities()),
	biomeBirchForest(Files.BIOMES, "Giants Configuration.Biome Settings.Birch Forest", FileHandler.loadEntities()),
	biomeSavanna(Files.BIOMES, "Giants Configuration.Biome Settings.Savanna", FileHandler.loadEntities()),
	biomeRoofedForest(Files.BIOMES, "Giants Configuration.Biome Settings.Roofed Forest",FileHandler.loadEntities()),
	biomeMesa(Files.BIOMES, "Giants Configuration.Biome Settings.Mesa",FileHandler.loadEntities()),
	biomeSmallMountains(Files.BIOMES,"Giants Configuration.Biome Settings.Other.Small Mountains",FileHandler.loadEntities()),
	biomeIceMountains(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Ice Mountains",FileHandler.loadEntities()),
	biomeOcean(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Ocean", ""),
	biomeRiver(Files.BIOMES, "Giants Configuration.Biome Settings.Other.River", ""),
	biomeHell(Files.BIOMES,"Giants Configuration.Biome Settings.Other.Hell", "- Giant Lava Slime"),
	biomeSky(Files.BIOMES, "Giants Configuration.Biome Settings.Other.Sky", ""),
	
	/**
	 * These file values will go in the entities.yml file.
	 */
//Worlds
	zombieWorld(Files.ENTITIES, "Entities Configuration.Spawn Settings.Worlds.Giant Zombie", FileHandler.loadWorlds()),
	slimeWorld(Files.ENTITIES, "Entities Configuration.Spawn Settings.Chance.Giant Slime", FileHandler.loadWorlds()),
	lavaslimeWorld(Files.ENTITIES, "Entities Configuration.Spawn Settings.Chance.Giant Lava Slime", FileHandler.loadWorlds()),
//Size
	slimeSize(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Slime", new Integer(12)),
	lavaSlimeSize(Files.ENTITIES, "Entities Configuration.Spawn Settings.Size.Giant Lava Slime", new Integer(12)),
//Speed
	zombieSpeed(Files.ENTITIES, "Entities Configuration.Stats.Speed.Giant Zombie", new Integer(3)),
//Health
	zombieHealth(Files.ENTITIES, "Entities Configuration.Stats.Health.Giant Zombie", new Integer(100)),
	slimeHealth(Files.ENTITIES, "Entities Configuration.Stats.Health.Giant Slime", new Integer(100)),
	lavaSlimeHealth(Files.ENTITIES, "Entities Configuration.Stats.Health.Giant Lava Slime", new Integer(100)),
//Experience
	zombieExperience(Files.ENTITIES, "Entities Configuration.Stats.Experience.Giant Zombie", new Integer(5)),
	slimeExperience(Files.ENTITIES, "Entities Configuration.Stats.Experience.Giant Slime", new Integer(5)),
	lavaSlimeExperience(Files.ENTITIES, "Entities Configuration.Stats.Experience.Giant Lava Slime", new Integer(5)),
//Armour
	zombieArmour(Files.ENTITIES, "Entities Configuration.Stats.Equipped Armour.Giant Zombie.Items", 
			new String("chainmail_helmet:chainmail_chestplate:chainmail_leggings:chainmail_boots:diamond_sword")),
	armourDropRate(Files.ENTITIES, "Entities Configuration.Stats.Equipped Armour.Giant Zombie.Equipped Item Drop Rate", new Float(8.5)),
//Drops
	dropManager(Files.ENTITIES, "Entities Configuration.Stats.Drops.Enable Drop Manager", true),
	zombieDrops(Files.ENTITIES, "Entities Configuration.Stats.Drops.Giant Zombie", Arrays.asList(FileHandler.loadDefaultDrop("Giant Zombie"))),
	slimeDrops(Files.ENTITIES, "Entities Configuration.Stats.Drops.Giant Slime", Arrays.asList(FileHandler.loadDefaultDrop("Giant Slime"))),
	lavaSlimeDrops(Files.ENTITIES, "Entities Configuration.Stats.Drops.Giant Lava Slime", Arrays.asList(FileHandler.loadDefaultDrop("Giant Lava Slime"))),
//BarAPI
	zombieBarAPI(Files.ENTITIES, "Entities Configuration.Stats.BarAPI.Display Name.Giant Zombie", "&2Giant Zombie"),
	slimeBarAPI(Files.ENTITIES, "Entities Configuration.Stats.BarAPI.Display Name.Giant Slime", "&2Giant Slime Size {size}"),
	lavaSlimeBarAPI(Files.ENTITIES, "Entities Configuration.Stats.BarAPI.Display Name.Giant Lava Slime", "&2Giant Lava Slime Size {size}"),
//Damage
	zombieArrowDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Zombie", new Integer(10)),
	slimeArrowDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Slime", new Integer(10)),
	lavaSlimeArrowDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Arrows.Damage done by arrow.Giant Lava Slime", new Integer(10)),
	zombieAllowFireDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Fire.Allow Fire Damage.Giant Zombie", true),
	slimeAllowFireDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Fire.Allow Fire Damage.Giant Slime", true),
	zombieAllowSuffocation(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Zombie",false),
	slimeAllowSuffocation(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Slime",false),
	lavaSlimeAllowSuffocation(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Suffocation.Giant Lava Slime",false),
	zombieAllowCactiDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Zombie", false),
	slimeAllowCactiDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Slime", false),
	lavaSlimeAllowCactiDamage(Files.ENTITIES, "Entities Configuration.Damage Settings.Block Damage.Allow Cacti Damage.Giant Lava Slime", false),
//Targets
	zombieAggressiveToVillage(Files.ENTITIES, "Entities Configuration.Target Settings.Target Villagers", false),
	
	/**
	 * These file values will go in attacks.yml
	 */
	lightningAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Lightning Attack", FileHandler.loadEntities()),
	stompAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Stomp Attack.Enabled", FileHandler.loadEntities()),
	stompAttackPower(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1)),
	stompAttackFire(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Stomp Attack.Light Fire", FileHandler.loadEntities()),
	lavaAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Lava Attack", FileHandler.loadEntities()),
	kickAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Kick Attack.Enabled", FileHandler.loadEntities()),
	kickAttackHeight(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1)),
	fireAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Enabled", FileHandler.loadEntities()),
	fireAttackTargetTicks(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100)),
	fireAttackGiantTicks(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100)),
	bouldAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", FileHandler.loadEntities()),
	bouldAttackBlockDamage(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1)),
	shrapnelAttack(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Enabled", Arrays.asList("Giant Zombie")),
	shrapnelAttackBabies(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies", false),
	shrapnelAttackZombies(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn", new Integer(3)),
	shrapnelAttackHealth(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Shrapnel Attack.Health", new Integer(20)),
	zombiesOnDeath(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false),
	zombiesOnDeathBabies(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies", false),
	zombiesOnDeathZombies(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5)),
	zombiesOnDeathHealth(Files.ATTACKS, "Attacks Configuration.Attack Mechanisms.Spawn Zombies On Death.Health", new Integer(20)),
	
	/**
	 * These file values will go in jockey.yml
	 */
	jockeyWarning(Files.JOCKEY, "Jockey Configuration", new String("This entity of Giants is extremely experimental and does not have many features")),
	jockeyWorlds(Files.JOCKEY, "Jockey Configuration.Spawn Settings.Worlds", FileHandler.loadWorlds()),
	
	/**
	 * The file values will go in jockey/biomes.yml
	 */
	jockeyBiomeSwampland(Files.JOCKEYBIOMES,"Giants Configuration.Biome Settings.Swampland", FileHandler.loadEntities()),
	jockeyBiomeForest(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Forest", FileHandler.loadEntities()),
	jockeyBiomeTaiga(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Taiga", FileHandler.loadEntities()),
	jockeyBiomePlains(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Plains", FileHandler.loadEntities()),
	jockeyBiomeExtremeHills(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Extreme Hills", FileHandler.loadEntities()),
	jockeyBiomeMushroomIsland(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Mushroom Island", FileHandler.loadEntities()),
	jockeyBiomeDesert(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Desert", FileHandler.loadEntities()),
	jockeyBiomeJungle(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Jungle", FileHandler.loadEntities()),
	jockeyBiomeBirchForest(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Birch Forest", FileHandler.loadEntities()),
	jockeyBiomeSavanna(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Savanna", FileHandler.loadEntities()),
	jockeyBiomeRoofedForest(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Roofed Forest",FileHandler.loadEntities()),
	jockeyBiomeMesa(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Mesa",FileHandler.loadEntities()),
	jockeyBiomeSmallMountains(Files.JOCKEYBIOMES,"Giants Configuration.Biome Settings.Other.Small Mountains",FileHandler.loadEntities()),
	jockeyBiomeIceMountains(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Other.Ice Mountains",FileHandler.loadEntities()),
	jockeyBiomeOcean(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Other.Ocean", ""),
	jockeyBiomeRiver(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Other.River", ""),
	jockeyBiomeHell(Files.JOCKEYBIOMES,"Giants Configuration.Biome Settings.Other.Hell", "- Giant Lava Slime"),
	jockeyBiomeSky(Files.JOCKEYBIOMES, "Giants Configuration.Biome Settings.Other.Sky", ""),
	;
	private final String _key;
	private final Files _files;
	private final String _value;
	private final List<String> _values;
	
	private ConfigValues(Files file, String key, String value) {
		_files = file;
		_key = key;
		_value= value;
		_values = null;
	}
	
	private ConfigValues(Files file, String key, int value) {
		_files = file;
		_key = key;
		_value= value + "";
		_values = null;
	}
	
	private ConfigValues(Files file, String key, float value) {
		_files = file;
		_key = key;
		_value= value + "";
		_values = null;
	}
	
	private ConfigValues(Files file, String key, boolean value) {
		_files = file;
		_key = key;
		_value = value == true ? "true" : "false";
		_values = null;
	}
	
	private ConfigValues(String key, String value) {
		_files = null;
		_key = key;
		_value= value;
		_values = null;
	}
	
	private ConfigValues(Files file, String key, List<String> value) {
		_files = file;
		_key = key;
		_values= value;
		_value = _values.get(0);
	}
	
	public String getKey() {
		return _key;
	}
	
	public Files getFile() {
		return _files;
	}

	public String getValue() {
		return _value;
	}
	
	public List<String> getValues() {
		return _values;
	}
	
	private static String loadVersion() {		
		PluginDescriptionFile pdf = 
				Bukkit.getPluginManager().getPlugin("Giants").getDescription();
		String version = pdf.getVersion();
		if (version == null) {
			return null;
		}
		return version;
	}

}
