package com.cdc.naruto.jutsu;

import com.cdc.naruto.init.NarutoJutsus;
import com.cdc.naruto.items.ItemScroll;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class Jutsu {
    private static final String KEY_COMPOUND_TAG = "compoundTag";
    private static final String KEY_CHAKRA = "chakra";
    private static final String KEY_NAME = "jutsu_entry";
    private static final String KEY_DISPLAY_NAME = "display_name";

    private JutsuEntry entry;
    private NBTTagCompound jutsuNBT;

    private Jutsu() {
        jutsuNBT = new NBTTagCompound();
        jutsuNBT.setInteger(KEY_CHAKRA, 0);
    }

    public Jutsu(String name, int chakra) {
        this(NarutoJutsus.getJutsu(name), chakra);
    }

    public Jutsu(String name) {
        this(NarutoJutsus.getJutsu(name));
    }

    public Jutsu(JutsuEntry entry) {
        this(entry, entry.getChakraCost());
    }

    public Jutsu(JutsuEntry jutsu, int chakra) {
        this();
        this.entry = jutsu;
        jutsuNBT.setInteger(KEY_CHAKRA, chakra);
    }

    public Jutsu(NBTTagCompound compound) {
        this();
        if(compound.hasKey(KEY_NAME, Constants.NBT.TAG_STRING)) this.entry = NarutoJutsus.getJutsu(compound.getString(KEY_NAME));
        if(compound.hasKey(KEY_COMPOUND_TAG, Constants.NBT.TAG_COMPOUND)) setTagCompound(compound.getCompoundTag(KEY_COMPOUND_TAG));
    }

    @Nullable
    public JutsuEntry getEntry() {
        return entry;
    }

    public NBTTagCompound getTagCompound() {
        return jutsuNBT;
    }

    public boolean hasTagCompound() {
        return !jutsuNBT.hasNoTags();
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        if(entry != null) {
            nbt.setString(KEY_NAME, this.entry.getRawName());
            nbt.setTag(KEY_COMPOUND_TAG, this.getTagCompound());
        }
        return nbt;
    }

    public void setTagCompound(NBTTagCompound nbt) {
        jutsuNBT = nbt;
    }

    @Nullable
    public String getDescription() {
        if(entry != null) return entry.getDescription();
        return null;
    }

    public int getChakra() {
        return jutsuNBT.getInteger(KEY_CHAKRA);
    }

    public void setChakra(int chakra) {
        if(!hasTagCompound())
            jutsuNBT = new NBTTagCompound();

        jutsuNBT.setInteger(KEY_CHAKRA, chakra);
    }

    public static boolean isJutsuEqualTo(ItemStack stack1, ItemStack stack2) {
        if((stack1.getItem() instanceof ItemScroll) && (stack2.getItem() instanceof ItemScroll)) {
            if(stack1.hasTagCompound() && stack2.hasTagCompound()) {
                return stack1.getTagCompound().getCompoundTag(KEY_NAME) == stack2.getTagCompound().getCompoundTag(KEY_NAME);
            }
        }
        return false;
    }

    public boolean hasCustomDisplayName() {
        return !getDisplayName().equals("");
    }

    public String getLocalisedName() {
        return this.entry.getLocalisedName();
    }

    public String getDisplayName() {
        return jutsuNBT.getString(KEY_DISPLAY_NAME);
    }

    public void setDisplayName(String name) {
        jutsuNBT.setString(KEY_DISPLAY_NAME, name);
    }

    public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
        return entry.activeUse(stack, world, player, hand);
    }
}
