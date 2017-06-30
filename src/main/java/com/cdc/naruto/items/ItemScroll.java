package com.cdc.naruto.items;

import java.util.List;

import com.cdc.naruto.Naruto;
import com.cdc.naruto.handlers.JutsuHelper;
import com.cdc.naruto.init.NarutoJutsus;
import com.cdc.naruto.jutsu.Jutsu;
import com.cdc.naruto.jutsu.JutsuEntry;

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

public class ItemScroll extends Item {
    public ItemScroll(){
        setUnlocalizedName("scroll");
        setRegistryName("scroll");
        setCreativeTab(Naruto.tabNaruto);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs itemIn, NonNullList<ItemStack> tab) {
    	if(this.func_194125_a(itemIn)) {
    		tab.add(0, new ItemStack(this));
    		for(JutsuEntry entry : NarutoJutsus.JUTSUS.values()) {
    			ItemStack scrollWithJutsu = JutsuHelper.setJutsus(new ItemStack(this), new Jutsu(entry, entry.getChakraCost()));
    			tab.add(scrollWithJutsu);
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
        if(!jutsus.isEmpty()) {
            for(Jutsu j : jutsus) {
                tooltip.add(j.getLocalisedName());
            }
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack stack) {
    	return super.hasEffect(stack) || stack.getMetadata() > 0;
    }
}
