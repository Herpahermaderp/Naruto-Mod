package com.cdc.naruto.capabilities.jutsu;

import com.cdc.naruto.capabilities.ICapability;
import com.cdc.naruto.jutsu.EnumJutsuType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.List;

public interface ICapJutsu extends ICapability {
    List<EnumJutsuType> getKnownJutsu();

    boolean knowsJutsu(EnumJutsuType jutsu);

    boolean addJutsu(EnumJutsuType jutsu);

    boolean addJutsu(EnumJutsuType jutsu, EntityPlayerMP player);

    void removeJutsu(EnumJutsuType jutsu);

    void removeJutsu(EnumJutsuType jutsu, EntityPlayerMP player);
}
