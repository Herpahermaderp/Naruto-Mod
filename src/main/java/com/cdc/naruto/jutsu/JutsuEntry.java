package com.cdc.naruto.jutsu;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
public abstract class JutsuEntry {
    private String unlocalizedName;

    public JutsuEntry(String name){
        unlocalizedName = name;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public String getDescription(){
        return I18n.format("desc."+getUnlocalizedName());
    }

    @Nonnull
    public String getUnlocalizedName(){
        return "jutsu." + unlocalizedName + ".name";
    }

    public String getRawName(){
        return unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public String getLocalisedName(){
        return I18n.format(getUnlocalizedName());
    }

    @Nonnegative
    public abstract int getChakraCost();

    public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
        return stack;
    }

}
