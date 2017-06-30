package com.cdc.naruto.jutsu;

import com.cdc.naruto.entity.jutsu.EntitySubLog;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class JutsuSubstitution extends JutsuEntry {
	public JutsuSubstitution() {
		super("substitution");
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
		
		RayTraceResult playerRTR = player.rayTrace(200, 10);
		BlockPos block = playerRTR.getBlockPos();
		world.spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY, player.posZ, 0.1D, 0.5D, 0.1D);
		Entity log = new EntitySubLog(world);
		log.setPosition(player.posX, player.getEyeHeight(), player.posZ);
		world.spawnEntity(log);
		if(playerRTR.sideHit == EnumFacing.UP) {
			player.setPositionAndUpdate(block.getX(), block.getY() + 1.0D, block.getZ());
		}
		else if(playerRTR.sideHit == EnumFacing.DOWN) {
			player.setPositionAndUpdate(block.getX(), block.getY() - 1.0D, block.getZ());
		}
		else if(playerRTR.sideHit == EnumFacing.NORTH) {
			player.setPositionAndUpdate(block.getX(), block.getY(), block.getZ() - 1.0D);
		}
		else if(playerRTR.sideHit == EnumFacing.SOUTH) {
			player.setPositionAndUpdate(block.getX(), block.getY(), block.getZ() + 1.0D);
		}
		else if(playerRTR.sideHit == EnumFacing.EAST) {
			player.setPositionAndUpdate(block.getX() + 1.0D, block.getY(), block.getZ());
		}
		else if(playerRTR.sideHit == EnumFacing.WEST) {
			player.setPositionAndUpdate(block.getX() - 0.5D, block.getY(), block.getZ());
		}
		player.sendStatusMessage(new TextComponentString("Substitution Jutsu"), true);
		return super.activeUse(stack, world, player, hand);
	}
}
