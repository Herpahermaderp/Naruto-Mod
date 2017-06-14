package com.cdc.naruto.init;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.cdc.naruto.capabilities.ICapability;
import com.cdc.naruto.capabilities.Storage;
import com.cdc.naruto.capabilities.chakra.CapabilityChakra;
import com.cdc.naruto.capabilities.chakra.ICapChakra;
import com.cdc.naruto.capabilities.jutsu.CapabilityJutsu;
import com.cdc.naruto.capabilities.jutsu.ICapJutsu;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class NarutoCapabilities {
    @CapabilityInject(ICapJutsu.class)
    public static Capability<ICapJutsu> JUTSU = null;
    @CapabilityInject(ICapChakra.class)
    public static Capability<ICapChakra> CHAKRA = null;

    private static ArrayList<Capability<? extends ICapability>> CAPABILITIES = new ArrayList<Capability<? extends ICapability>>();

    public static ArrayList<Capability<? extends ICapability>> getCapabilities(){
        if(CAPABILITIES.isEmpty()){
            CAPABILITIES.add(JUTSU);
            CAPABILITIES.add(CHAKRA);
        }
        return CAPABILITIES;
    }

    public static int getCapabilitiyID(Capability<? extends ICapability> capability){
        return getCapabilities().indexOf(capability);
    }

    public static Capability<? extends ICapability> getCapabilityFromID(int id){
        ArrayList<Capability<? extends ICapability>> capList = getCapabilities();
        if(id < 0) id = 0;
        else if(id >= capList.size()) id = capList.size() - 1;
        return capList.get(id);
    }

    private static <T extends ICapability> void regCapability(Class<T> capInterface, Callable<? extends T> capFactory){
        CapabilityManager.INSTANCE.register(capInterface, new Storage<T>(), capFactory);
    }

    public static void init(){
        regCapability(ICapJutsu.class, CapabilityJutsu::new);
        regCapability(ICapChakra.class, CapabilityChakra::new);
    }
}
