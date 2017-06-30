package com.cdc.naruto.jutsu;

public enum EnumJutsuType {
    FIRE(0, "fire"),
    WIND(1, "wind"),
    LIGHTNING(2, "lightning"),
    EARTH(3, "earth"),
    WATER(4, "water"),
    ICE(5, "ice"),
    WOOD(6, "wood"),
    LAVA(7, "lava"),
    STORM(8, "storm"),
    BOIL(9, "boil"),
    DUST(10, "dust"),
    EXPLOSION(11, "explosion"),
    SCORCH(12, "scorch"),
    MAGNET(13, "magnet"),
    CRYSTAL(14, "crystal"),
    STEEL(15, "steel"),
    DARK(16, "dark"),
    SWIFT(17, "swift"),
    KEKKEI_GENKAI(18, "kekkei_genkai");

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
