package com.cdc.naruto.items;

import com.cdc.naruto.Naruto;
import com.cdc.naruto.handlers.JutsuHelper;
import com.cdc.naruto.init.NarutoJutsus;
import com.cdc.naruto.jutsu.Jutsu;
import com.cdc.naruto.jutsu.JutsuEntry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemScroll extends Item{
    public ItemScroll(){
        setUnlocalizedName("scroll");
        setRegistryName("scroll");
        setCreativeTab(Naruto.tabNaruto);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs itemIn, NonNullList<ItemStack> tab) {
        tab.add(0, new ItemStack(this));

        for(JutsuEntry entry : NarutoJutsus.JUTSUS.values()){
            ItemStack scrollWithJutsu = JutsuHelper.setJutsus(new ItemStack(this), new Jutsu(entry, entry.getChakraCost()));
            for(int i = 0; i < NarutoJutsus.JUTSUS.size();i++){
                tab.add(i, scrollWithJutsu);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = JutsuHelper.perform(playerIn.getHeldItem(handIn), worldIn, playerIn, handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        List<Jutsu> jutsus = JutsuHelper.getJutsu(stack);
        if(!jutsus.isEmpty()){
            for(Jutsu j : jutsus){
                tooltip.add(j.getLocalisedName());
            }
        }
    }
}
