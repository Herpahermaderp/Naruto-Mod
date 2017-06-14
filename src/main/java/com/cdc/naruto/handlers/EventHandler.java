package com.cdc.naruto.handlers;

import java.util.Random;

import com.cdc.naruto.Naruto;
import com.cdc.naruto.capabilities.ICapability;
import com.cdc.naruto.capabilities.chakra.ICapChakra;
import com.cdc.naruto.init.NarutoCapabilities;
import com.cdc.naruto.jutsu.EnumJutsuType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class EventHandler {
    public static final String KEY_NATURE = "nature";

    /**
     * TODO: Create a common getter for the capabilities
     */
    /*private static Capability<? extends ICapability> getCapabilityFromPlayer(EntityPlayer player, Capability<? extends ICapability> capability){
        if(player.hasCapability(capability, null)){
            return player.getCapability(capability, null);
        }
    }*/

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){
        Entity entity = event.getObject();
        if(entity instanceof EntityPlayer){
            for(Capability<? extends ICapability> cap : NarutoCapabilities.getCapabilities()){
                ICapability icap = cap.getDefaultInstance();
                if(icap == null) continue;
                if(!entity.hasCapability(cap, null))
                    event.addCapability(icap.getKey(), icap.getProvider());
            }
        }
    }

    @SubscribeEvent
    public static void fillChakra(TickEvent.PlayerTickEvent event){
        if(event.player.world.isRemote) return;
        EntityPlayer player = event.player;
        ICapChakra chakra = player.getCapability(NarutoCapabilities.CHAKRA, null);
        if(chakra != null){
            player.sendStatusMessage(new TextComponentString("Chakra: " + chakra.getChakra()), true);
            if(player.world.getTotalWorldTime() % 60 == 0){
                chakra.fillChakra(1);
            }
        }
    }

    @SubscribeEvent
    public static void lowChakra(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        World world = player.world;
        if(world.isRemote) return;
        if(player.hasCapability(NarutoCapabilities.CHAKRA, null)) {
            ICapChakra chakra = player.getCapability(NarutoCapabilities.CHAKRA, null);
            if(chakra == null) return;
            int damage = 0;
            if(chakra.getChakra() < 25F){
                damage++;
                if(chakra.getChakra() < 10){
                    damage+=2;
                }
            }
            if(damage > 0 && !player.isCreative()) {
                if (world.getTotalWorldTime() % 60 == 0) {
                    player.setHealth(player.getHealth() - damage);
                    player.performHurtAnimation();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        if(event.player instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            for(Capability<? extends ICapability> cap : NarutoCapabilities.getCapabilities()){
                ICapability icap = player.getCapability(cap, null);
                if(icap != null) icap.dataChanged(player);
            }
        }
    }

    @SubscribeEvent
    public static void givePlayerChakraNature(EntityJoinWorldEvent event){
        if(event.getWorld().isRemote) return;
        if(event.getEntity() instanceof EntityPlayerMP){
            EntityPlayerMP player = (EntityPlayerMP)event.getEntity();
            if(player.hasCapability(NarutoCapabilities.JUTSU, null)){
                if(!player.getEntityData().hasKey(KEY_NATURE)){
                    Random random = event.getWorld().rand;
                    switch(random.nextInt(5)){
                        case 0:
                            player.getEntityData().setString(KEY_NATURE, EnumJutsuType.EARTH.getName());
                            break;
                        case 1:
                            player.getEntityData().setString(KEY_NATURE, EnumJutsuType.FIRE.getName());
                            break;
                        case 2:
                            player.getEntityData().setString(KEY_NATURE, EnumJutsuType.WATER.getName());
                            break;
                        case 3:
                            player.getEntityData().setString(KEY_NATURE, EnumJutsuType.LIGHTNING.getName());
                            break;
                        case 4:
                            player.getEntityData().setString(KEY_NATURE, EnumJutsuType.WIND.getName());

                    }
                    Naruto.LOGGER.info("Set chakra nature for player " + player.getName());
                }else{
                    Naruto.LOGGER.info(player.getEntityData().getString(KEY_NATURE));
                }
            }
        }
    }
}
