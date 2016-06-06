package me.Mammothskier.Giants.entity.nms.v1_9_R1;

import net.minecraft.server.v1_9_R1.PathfinderGoalMeleeAttack;

public class CustomPathfinderGoalGiantZombieAttack extends PathfinderGoalMeleeAttack {
	
    private final CustomEntityGiantZombie h;
    private int i;

	public CustomPathfinderGoalGiantZombieAttack(CustomEntityGiantZombie entityzombie, double d0, boolean flag) {
		super(entityzombie, d0, flag);
		this.h = entityzombie;
	}

    public void c() {
        super.c();
        this.setI(0);
    }

    public void d() {
        super.d();
        this.h.a(false);
    }

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}
}
