package me.Mammothskier.Giants;

import java.util.logging.Logger;

import me.Mammothskier.Giants.utils.API;

import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		new API(this);
	}
}
