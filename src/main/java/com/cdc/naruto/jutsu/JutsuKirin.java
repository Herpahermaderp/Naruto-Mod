package com.cdc.naruto.jutsu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class JutsuKirin extends JutsuEntry {
	public JutsuKirin() {
		super("kirin");
	}
	
	@Override
	public int getChakraCost() {
		return 100;
	}
	
	@Override
	public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if(world.isRemote) {
			return super.activeUse(stack, world, player, hand);
		}
		RayTraceResult RTR = player.rayTrace(200, 10);
		Entity entity = RTR.entityHit;
		BlockPos block = RTR.getBlockPos();
		if(world.isRaining()) {
			if(RTR.typeOfHit == Type.ENTITY) {
				world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, true));
				world.getWorldInfo().setRaining(false);
			}
			
			else if(RTR.typeOfHit == Type.BLOCK) {
				world.addWeatherEffect(new EntityLightningBolt(world, block.getX(), block.getY(), block.getZ(), true));
				world.getWorldInfo().setRaining(false);
			}
		}
		player.sendStatusMessage(new TextComponentString("Kirin"), true);
		return super.activeUse(stack, world, player, hand);
	}
}
