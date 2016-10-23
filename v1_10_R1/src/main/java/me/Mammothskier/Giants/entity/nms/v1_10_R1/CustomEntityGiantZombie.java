package me.Mammothskier.Giants.entity.nms.v1_10_R1;

import me.Mammothskier.Giants.Files.ConfigValues;
import me.Mammothskier.Giants.Files.FileHandler;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.EntityGiantZombie;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityIronGolem;
import net.minecraft.server.v1_10_R1.EntityPigZombie;
import net.minecraft.server.v1_10_R1.EntityVillager;
import net.minecraft.server.v1_10_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_10_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_10_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.GenericAttributes;

public class CustomEntityGiantZombie extends EntityGiantZombie {

	public CustomEntityGiantZombie (World world) {
		super(world);

	}

	protected void r () {
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



	@Override
	protected void initAttributes() {
		super.initAttributes();
		String string = FileHandler.getInstanceProperty(ConfigValues.zombieSpeed);
		double speed;
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
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
		if (FileHandler.getInstanceProperty(ConfigValues.zombieAggressiveToVillage).equalsIgnoreCase("false"))
			this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, false)); // Spigot
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
	}

	public float a(BlockPosition position) {
		return 0.5F - this.world.n(position);
	}
}
