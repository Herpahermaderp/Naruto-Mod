package com.cdc.naruto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cdc.naruto.init.NarutoItems;
import com.cdc.naruto.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MODID, version = Ref.VERSION, name=Ref.NAME, acceptedMinecraftVersions=Ref.ACCEPTED_VERSIONS)
public class Naruto {
    @SidedProxy(clientSide = Ref.CLIENT_RROXY, serverSide = Ref.COMMON_RROXY)
    public static CommonProxy proxy;

    public static final Logger LOGGER = LogManager.getLogger(Ref.MODID.toUpperCase());

    @Mod.Instance
    public static Naruto INSTANCE;

    public static final CreativeTabs tabNaruto = new CreativeTabs("tabNaruto") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(NarutoItems.scroll);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Starting Pre-Initialization...");
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Starting Initialization...");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("Starting Post-Initialization...");
        proxy.postInit(event);
    }
}
