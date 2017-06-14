package com.cdc.naruto.init;

import com.cdc.naruto.items.ItemScroll;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NarutoItems {

    public static List<Item> ITEMS = new ArrayList<>();

    public static Item scroll;

    public static void init(){
        scroll = addItem(new ItemScroll());
    }

    private static Item addItem(Item item){
        ITEMS.add(item);
        return item;
    }
}
