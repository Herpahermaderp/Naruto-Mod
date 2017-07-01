package com.cdc.naruto.handlers;

import java.util.List;

import javax.annotation.Nonnull;

import com.cdc.naruto.capabilities.chakra.ICapChakra;
import com.cdc.naruto.init.NarutoCapabilities;
import com.cdc.naruto.items.ItemScroll;
import com.cdc.naruto.jutsu.Jutsu;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class JutsuHelper {
    private static final String KEY_JUTSUS = "jutsus";

    @Nonnull
    public static List<Jutsu> getJutsu(ItemStack stack){
        List<Jutsu> jutsus = Lists.newArrayList();
        if(stack.hasTagCompound()){
            NBTTagCompound nbt = stack.getTagCompound();
            if(nbt.hasKey(KEY_JUTSUS, Constants.NBT.TAG_LIST)){
                NBTTagList jutsuList = nbt.getTagList(KEY_JUTSUS, Constants.NBT.TAG_COMPOUND);
                if(!jutsuList.hasNoTags()){
                    for(int i = 0;i < jutsuList.tagCount();i++){
                        jutsus.add(new Jutsu(jutsuList.getCompoundTagAt(i)));
                    }
                }
            }
        }
        return jutsus;
    }

    public static ItemStack setJutsus(ItemStack stack, Jutsu jutsu){
        NBTTagCompound stackNBT = new NBTTagCompound();
        if(stack.hasTagCompound()){
            stackNBT = stack.getTagCompound();
        }
        NBTTagList jutsuList = new NBTTagList();
        jutsuList.appendTag(jutsu.writeToNBT());
        if(stackNBT != null){
            stackNBT.setTag(KEY_JUTSUS, jutsuList);
        }
        stack.setTagCompound(stackNBT);
        return stack;
    }

    public static ItemStack perform(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(world.isRemote) return stack;
        List<Jutsu> jutsus = getJutsu(stack);
        if(!jutsus.isEmpty()) {
            int multiplier = 0;
            for(Jutsu j : jutsus) {
                if(player.hasCapability(NarutoCapabilities.CHAKRA, null)) {
                    ICapChakra icap = player.getCapability(NarutoCapabilities.CHAKRA, null);
                    if(icap != null) {
                        if (icap.getChakra() < j.getChakra() || icap.getChakra() - j.getChakra() < 0) {
                            player.sendStatusMessage(new TextComponentString("You do not have enough chakra to perform this jutsu."), false);
                            return stack;
                        }
                        
                        else {
                            stack = j.activeUse(stack, world, player, hand);
                            multiplier += j.getChakra();
                            player.sendStatusMessage(new TextComponentString("This takes " + j.getChakra() + " chakra."), false);
                            if(!player.capabilities.isCreativeMode) {
                            	icap.diminishChakra(multiplier);
                            }
                        }
                    }
                }
            }
            //TODO: Decrease chakra according to multiplier
        }
        return stack;
    }

    public static ItemStack getScrollWithJutsu(EntityPlayer player, String jutsuUnlocName){
        InventoryPlayer playerInv = player.inventory;
        for(ItemStack stack : playerInv.mainInventory){
            if(stack != null && stack.getItem() instanceof ItemScroll){
                List<Jutsu> jutsus = getJutsu(stack);
                if(!jutsus.isEmpty()){
                    for(Jutsu j : jutsus){
                        if(j.getEntry().getRawName().equals(jutsuUnlocName)){
                            return stack;
                        }
                    }
                }
            }
        }
        return null;
    }
}
