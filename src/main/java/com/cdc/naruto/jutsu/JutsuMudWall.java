package com.cdc.naruto.jutsu;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class JutsuMudWall extends JutsuEntry{
    public JutsuMudWall() {
        super("mud_wall");
    }

    @Override
    public int getChakraCost() {
        return 15;
    }

    @Override
    public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(world.isRemote) return super.activeUse(stack, world, player, hand);

        RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;
        BlockPos pos = result.getBlockPos();

        if(world.getBlockState(pos).getMaterial() == Material.GRASS || world.getBlockState(pos).getMaterial() == Material.GROUND || world.getBlockState(pos).getMaterial() == Material.ROCK){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    for(int k = 0; k < 3; k++){
                        if(player.getHorizontalFacing() == EnumFacing.NORTH) {
                            world.setBlockState(new BlockPos(pos.getX() + (i/2), pos.getY() + j, pos.getZ() + (k/2)), Blocks.DIRT.getDefaultState());
                            world.setBlockState(new BlockPos(pos.getX() - (i/2), pos.getY() + j, pos.getZ() - (k/2)+1), Blocks.DIRT.getDefaultState());
                        }
                        else if(player.getHorizontalFacing() == EnumFacing.SOUTH) {
                            world.setBlockState(new BlockPos(pos.getX() + (i/2), pos.getY() + j, pos.getZ() + (k/2)), Blocks.DIRT.getDefaultState());
                            world.setBlockState(new BlockPos(pos.getX() - (i/2), pos.getY() + j, pos.getZ() - (k/2)+1), Blocks.DIRT.getDefaultState());
                        }
                        else if(player.getHorizontalFacing() == EnumFacing.EAST) {
                            world.setBlockState(new BlockPos(pos.getX() + (k/2), pos.getY() + j, pos.getZ() + (i/2)), Blocks.DIRT.getDefaultState());
                            world.setBlockState(new BlockPos(pos.getX() - (k/2)+1, pos.getY() + j, pos.getZ() - (i/2)), Blocks.DIRT.getDefaultState());
                        }
                        else if(player.getHorizontalFacing() == EnumFacing.WEST) {
                            world.setBlockState(new BlockPos(pos.getX() + (k/2), pos.getY() + j, pos.getZ() + (i/2)), Blocks.DIRT.getDefaultState());
                            world.setBlockState(new BlockPos(pos.getX() - (k/2)+1, pos.getY() + j, pos.getZ() - (i/2)), Blocks.DIRT.getDefaultState());
                        }
                        player.sendStatusMessage(new TextComponentString("Earth Style: Mud Wall Jutsu"), true);
                    }
                }
            }
        }
        
        else {
            player.sendStatusMessage(new TextComponentString("Earth Style: Mud Wall Jutsu could not be performed."), true);
            return super.activeUse(stack, world, player, hand);
        }
        return super.activeUse(stack, world, player, hand);
    }
}
