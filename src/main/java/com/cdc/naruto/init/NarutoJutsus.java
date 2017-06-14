package com.cdc.naruto.init;

import com.cdc.naruto.Naruto;
import com.cdc.naruto.jutsu.Jutsu;
import com.cdc.naruto.jutsu.JutsuEntry;
import com.cdc.naruto.jutsu.JutsuFireball;
import com.cdc.naruto.jutsu.JutsuMudWall;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class NarutoJutsus {
    public static Map<String, JutsuEntry> JUTSUS = new HashMap<>();

    public static void init(){
        addJutsu(new JutsuFireball());
        addJutsu(new JutsuMudWall());
    }

    private static void addJutsu(JutsuEntry jutsu){
        JUTSUS.put(jutsu.getRawName(), jutsu);
    }

    public static JutsuEntry getJutsu(String name){
        return JUTSUS.get(name);
    }

}
