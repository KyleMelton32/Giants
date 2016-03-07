package me.Mammothskier.Giants.entity.nms.v1_7_R4;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_7_R4.util.UnsafeList;

import me.Mammothskier.Giants.Giants;
import me.Mammothskier.Giants.files.Files;
import net.minecraft.server.v1_7_R4.EntityGiantZombie;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntityIronGolem;
import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R4.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_7_R4.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R4.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R4.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_7_R4.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_7_R4.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_7_R4.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_7_R4.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;
import net.minecraft.server.v1_7_R4.World;
import net.minecraft.server.v1_7_R4.GenericAttributes;

public class CustomEntityGiantZombie extends EntityGiantZombie {

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
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntitySkeleton.class, 1.0D, true));
		this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
		this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
	}
	
	@Override
	public float getHeadHeight() {
        return 14.440001F;
    }
	
	@Override
    protected void aD() {
        super.aW();
        String string = Giants.getProperty(Files.ENTITIES, "Entities Configuration.Stats.Speed.Giant Zombie");
        double speed = 1;
        try {
        	speed = Double.parseDouble(string);
        } catch (Exception e) {
        	speed = 1;
        }
        this.getAttributeInstance(GenericAttributes.b).setValue(35D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.23000000417232513D * speed);
        this.getAttributeInstance(GenericAttributes.e).setValue(50.0D);
    }
	
	protected void n() {
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1, true));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityIronGolem.class, 1, true));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, 0, true));
    }
}
