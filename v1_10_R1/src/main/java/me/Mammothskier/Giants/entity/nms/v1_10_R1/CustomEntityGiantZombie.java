package me.Mammothskier.Giants.entity.nms.v1_10_R1;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_10_R1.util.UnsafeList;

import me.Mammothskier.Giants.Files.ConfigValues;
import me.Mammothskier.Giants.Files.FileHandler;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.DataWatcher;
import net.minecraft.server.v1_10_R1.DataWatcherObject;
import net.minecraft.server.v1_10_R1.DataWatcherRegistry;
import net.minecraft.server.v1_10_R1.EntityGiantZombie;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityIronGolem;
import net.minecraft.server.v1_10_R1.EntityPigZombie;
import net.minecraft.server.v1_10_R1.EntityVillager;
import net.minecraft.server.v1_10_R1.EntityZombie;
import net.minecraft.server.v1_10_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_10_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_10_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_10_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.GenericAttributes;

public class CustomEntityGiantZombie extends EntityGiantZombie {

    private static final DataWatcherObject<Boolean> by = DataWatcher.a(EntityZombie.class, DataWatcherRegistry.h);

	public CustomEntityGiantZombie(World world) {
		super(world);
		try {
			Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
			bField.setAccessible(true);
			Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
			cField.setAccessible(true);
			bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
			bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
			cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
			cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
			this.n();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new CustomPathfinderGoalGiantZombieAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	}
	
	@Override
	public float getHeadHeight() {
        return 14.440001F;
    }
	
    public void a(boolean flag) {
        this.getDataWatcher().set(CustomEntityGiantZombie.by, Boolean.valueOf(flag));
    }
	
	@Override
    protected void initAttributes() {
        super.initAttributes();
        String string = FileHandler.getInstanceProperty(ConfigValues.zombieSpeed);
        double speed = 1;
        try {
        	speed = Double.parseDouble(string);
        } catch (Exception e) {
        	speed = 1;
        }
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(35D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D * speed);
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(50.0D);
    }
	
	protected void o() {
		this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityPigZombie.class}));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
		if (FileHandler.getInstanceProperty(ConfigValues.zombieAggressiveToVillage).equalsIgnoreCase("false")) 
			this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false)); // Spigot
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<EntityIronGolem>(this, EntityIronGolem.class, true));
    }
	
	@Override
	public float a(BlockPosition position) {
		return 0.5F - this.world.n(position);
	}
}
