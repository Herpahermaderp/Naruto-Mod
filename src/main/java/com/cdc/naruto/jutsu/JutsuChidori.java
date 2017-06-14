package com.cdc.naruto.jutsu;

import com.cdc.naruto.entity.EntityChidori;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class JutsuChidori extends JutsuEntry{
    public JutsuChidori() {
        super("chidori");
    }

    @Override
    public int getChakraCost() {
        return 30;
    }

    @Override
    public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if(world.isRemote) return super.activeUse(stack, world, player, hand);
        EntityChidori chidori = new EntityChidori(world);
        Vec3d look = player.getLook(1.0F);
        chidori.setPosition(look.xCoord, look.yCoord, look.zCoord);
        Entity entity = Minecraft.getMinecraft().objectMouseOver.entityHit;
        if(Mouse.isButtonDown(0)){
            if(entity instanceof EntityLivingBase){

            }
        }
        return super.activeUse(stack, world, player, hand);
    }
}
