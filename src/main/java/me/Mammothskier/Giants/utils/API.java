package main.java.me.Mammothskier.Giants.utils;

import java.util.List;
import main.java.me.Mammothskier.Giants.Commands;
import main.java.me.Mammothskier.Giants.Giants;
import main.java.me.Mammothskier.Giants.events.Listeners;
import main.java.me.Mammothskier.Giants.files.Config;
import main.java.me.Mammothskier.Giants.files.FileHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;

public class API {
	private static Giants _giants;
	private Commands commands;
	private static FileHandler fileHandler;

	public API(Giants giants) {
		_giants = giants;
		new Listeners(_giants);
		commands = new Commands(_giants);
		_giants.getCommand("giants").setExecutor(commands);
		fileHandler = new FileHandler(_giants);
	}

	public static boolean isGiant(Entity entity) {
		return entity instanceof Giant;
	}

	public static boolean isGiant(LivingEntity livingEntity) {
		return livingEntity instanceof Giant;
	}

	public static List<String> getGiantSpawnWorlds() {
		return getFileHandler().getPropertyList(Config.CONFIG, "Giants Configuration.Spawn Settings.Worlds");
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
}
