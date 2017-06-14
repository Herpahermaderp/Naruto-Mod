package com.cdc.naruto.util;

import com.cdc.naruto.init.NarutoItems;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryUtil {

    public static void init(){
        for(Item item : NarutoItems.ITEMS){
            GameRegistry.register(item);
        }
    }
}
