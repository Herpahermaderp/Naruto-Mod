package com.cdc.naruto.entity.jutsu;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChidori extends Entity{
    private static final String KEY_POWER = "power";
    public int power;

    public EntityChidori(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {

    }

    public void setPower(int power){
        this.power = power;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if(compound.hasKey(KEY_POWER)){
            power = compound.getInteger(KEY_POWER);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        if(!compound.hasKey(KEY_POWER)){
            compound.setInteger(KEY_POWER, power);
        }
    }
}
