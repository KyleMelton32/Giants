package me.Mammothskier.Giants.entity.nms.v1_9_R1;

import net.minecraft.server.v1_9_R1.World;

import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;

public class EntityCreator {
	
	public static void createEntity (Location location, SpawnReason reason) {
		World world = ((CraftWorld) location.getWorld()).getHandle();
		CustomEntityGiantZombie giant = new CustomEntityGiantZombie(world);
		giant.setPosition(location.getX(), location.getY(), location.getZ());
		world.addEntity(giant, reason);
	}
}
