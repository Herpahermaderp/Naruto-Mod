package com.cdc.naruto.entity.jutsu;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySubLog extends EntityLiving {
	public EntitySubLog(World world) {
		super(world);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	@Override
	public void onLivingUpdate() {
		this.setHealth(getHealth() - 1);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3);
	}
}
