package com.cdc.naruto.jutsu;

public enum EnumJutsuType {
    EARTH(0, "earth"),
    FIRE(1, "fire"),
    WATER(2, "water"),
    WIND(3, "wind"),
    LIGHTNING(4, "lightning"),
    WOOD(5, "WOOD"),
    KEKKEI_GENKAI(6, "kekkei_genkai");

    private int id;
    private String name;

    EnumJutsuType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static EnumJutsuType byIndex(int index){
        return EnumJutsuType.values()[index];
    }
}
