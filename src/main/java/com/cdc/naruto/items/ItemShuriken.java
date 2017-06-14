package com.cdc.naruto.items;

import com.cdc.naruto.Naruto;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemShuriken extends Item {
    public ItemShuriken(){
        setUnlocalizedName("shuriken");
        setRegistryName("shuriken");
        setCreativeTab(Naruto.tabNaruto);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);



        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
