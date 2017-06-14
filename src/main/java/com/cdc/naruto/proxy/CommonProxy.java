package com.cdc.naruto.proxy;

import com.cdc.naruto.handlers.NetworkHandler;
import com.cdc.naruto.init.NarutoCapabilities;
import com.cdc.naruto.init.NarutoItems;
import com.cdc.naruto.init.NarutoJutsus;
import com.cdc.naruto.util.RegistryUtil;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        NarutoItems.init();
        RegistryUtil.init();
        NarutoJutsus.init();
        NarutoCapabilities.init();
        NetworkHandler.init();
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){
    }
}
