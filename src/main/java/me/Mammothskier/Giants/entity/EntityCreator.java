package me.Mammothskier.Giants.entity;

import me.Mammothskier.Giants.util.NMSUtils;

import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class EntityCreator {
	
	public static void createGiant (Location location, SpawnReason reason) {
		String version = NMSUtils.getBukkitVersion();
		if ("v1_7_R4".equals(version)) {
			//TODO Legacy support
		}
		if ("v1_8_R1".equals(version)) {
			net.minecraft.server.v1_8_R1.World world = ((org.bukkit.craftbukkit.v1_8_R1.CraftWorld) location.getWorld()).getHandle();
			me.Mammothskier.Giants.entity.nms.v1_8_R1.CustomEntityGiantZombie giant = new me.Mammothskier.Giants.entity.nms.v1_8_R1.CustomEntityGiantZombie(world);
			giant.setPosition(location.getX(), location.getY(), location.getZ());
			world.addEntity(giant, reason);
		} else if ("v1_8_R2".equals(version)) {
			net.minecraft.server.v1_8_R2.World world = ((org.bukkit.craftbukkit.v1_8_R2.CraftWorld) location.getWorld()).getHandle();
			me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityGiantZombie giant = new me.Mammothskier.Giants.entity.nms.v1_8_R2.CustomEntityGiantZombie(world);
			giant.setPosition(location.getX(), location.getY(), location.getZ());
			world.addEntity(giant, reason);
		}
	}
}
