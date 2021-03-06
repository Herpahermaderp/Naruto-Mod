package com.cdc.naruto.jutsu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class JutsuFireball extends JutsuEntry {
    public JutsuFireball() {
        super("fireball");
    }

    @Override
    public int getChakraCost() {
        return 10;
    }

    @Override
    public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(world.isRemote) {
        	return super.activeUse(stack, world, player, hand);
        }
        double ax, ay, az;
        ax = player.getLook(1.0F).xCoord / 0.1D;
        ay = player.getLook(1.0F).yCoord / 0.1D;
        az = player.getLook(1.0F).zCoord / 0.1D;

        EntityLargeFireball fireball = new EntityLargeFireball(world, player, ax, ay, az);
        fireball.explosionPower = 1;
        fireball.posX = player.posX + player.getLook(1.0F).xCoord;
        fireball.posY = player.posY + (player.height / 2.0F) + 0.5D;
        fireball.posZ = player.posZ + player.getLook(1.0F).zCoord;
        world.spawnEntity(fireball);
        player.sendStatusMessage(new TextComponentString("Fire Style: Fireball Jutsu"), true);
        return super.activeUse(stack, world, player, hand);
    }
}
