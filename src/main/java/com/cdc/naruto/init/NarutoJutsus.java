package com.cdc.naruto.init;

import java.util.HashMap;
import java.util.Map;

import com.cdc.naruto.jutsu.JutsuEntry;
import com.cdc.naruto.jutsu.JutsuFireball;
import com.cdc.naruto.jutsu.JutsuMudWall;
import com.cdc.naruto.jutsu.JutsuSubstitution;

public class NarutoJutsus {
    public static Map<String, JutsuEntry> JUTSUS = new HashMap<>();

    public static void init(){
        addJutsu(new JutsuFireball());
        addJutsu(new JutsuMudWall());
        addJutsu(new JutsuSubstitution());
    }

    private static void addJutsu(JutsuEntry jutsu){
        JUTSUS.put(jutsu.getRawName(), jutsu);
    }

    public static JutsuEntry getJutsu(String name){
        return JUTSUS.get(name);
    }

}
