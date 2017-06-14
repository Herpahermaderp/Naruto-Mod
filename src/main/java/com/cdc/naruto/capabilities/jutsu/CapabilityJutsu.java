package com.cdc.naruto.capabilities.jutsu;

import com.cdc.naruto.Ref;
import com.cdc.naruto.capabilities.CapabilityProvider;
import com.cdc.naruto.handlers.NetworkHandler;
import com.cdc.naruto.init.NarutoCapabilities;
import com.cdc.naruto.jutsu.EnumJutsuType;
import com.cdc.naruto.messages.MessageCapability;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.common.network.NetworkEventFiringHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.ArrayList;
import java.util.List;

public class CapabilityJutsu implements ICapJutsu{

    private static final ResourceLocation jutsuRL = new ResourceLocation(Ref.MODID, "Jutsu");

    private List<EnumJutsuType> knownJutsu = new ArrayList();

    @Override
    public List<EnumJutsuType> getKnownJutsu() {
        return knownJutsu;
    }

    @Override
    public boolean knowsJutsu(EnumJutsuType jutsu) {
        return knownJutsu.contains(jutsu);
    }

    @Override
    public boolean addJutsu(EnumJutsuType jutsu) {
        return !knowsJutsu(jutsu) && knownJutsu.add(jutsu);
    }

    @Override
    public boolean addJutsu(EnumJutsuType jutsu, EntityPlayerMP player) {
        boolean result = addJutsu(jutsu);
        if(result){
            dataChanged(player);
        }
        return result;
    }

    @Override
    public void removeJutsu(EnumJutsuType jutsu) {
        knownJutsu.remove(jutsu);
    }

    @Override
    public void removeJutsu(EnumJutsuType jutsu, EntityPlayerMP player) {
        int oldSize = knownJutsu.size();
        removeJutsu(jutsu);
        if(knownJutsu.size() != oldSize) dataChanged(player);
    }

    @Override
    public ResourceLocation getKey() {
        return jutsuRL;
    }

    @Override
    public ICapabilityProvider getProvider() {
        return new CapabilityProvider<>(NarutoCapabilities.JUTSU);
    }

    @Override
    public void dataChanged(EntityPlayerMP player) {
        NetworkHandler.INSTANCE.sendTo(new MessageCapability(NarutoCapabilities.JUTSU, serializeNBT()), player);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        int[] ids = new int[knownJutsu.size()];
        for(int i = 0; i < knownJutsu.size(); i++){
            ids[i] = knownJutsu.get(i).ordinal();
        }
        NBTTagIntArray jutsuIDs = new NBTTagIntArray(ids);
        nbt.setTag("jutsu", jutsuIDs);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("jutsu")){
            knownJutsu.clear();
            int[] jutsuIDs = nbt.getIntArray("jutsu");
            for(int id : jutsuIDs){
                addJutsu(EnumJutsuType.byIndex(id));
            }
        }
    }
}
