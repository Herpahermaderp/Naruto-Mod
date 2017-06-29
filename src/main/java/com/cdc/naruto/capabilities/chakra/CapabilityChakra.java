package com.cdc.naruto.capabilities.chakra;

import com.cdc.naruto.Ref;
import com.cdc.naruto.capabilities.CapabilityProvider;
import com.cdc.naruto.handlers.NetworkHandler;
import com.cdc.naruto.init.NarutoCapabilities;
import com.cdc.naruto.messages.MessageCapability;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityChakra implements ICapChakra {

	private static final ResourceLocation chakraRL = new ResourceLocation(Ref.MODID, "chakra");
	
	private int maxChakra = 100;
	private int currentChakra = maxChakra;
	
	@Override
	public void diminishChakra(int amount) {
		this.currentChakra -= amount;
		if (this.currentChakra < 0) {
			this.currentChakra = 0;
		}
	}

	@Override
	public void replenishChakra() {
		if(this.currentChakra < this.maxChakra) {
			this.currentChakra++;
		}
	}

	@Override
	public void fillChakra(int amount) {
		this.currentChakra += amount;
		if(this.currentChakra > this.maxChakra) {
			this.currentChakra = this.maxChakra;
		}
	}
	
	@Override
	public void setChakra(int amount) {
		this.currentChakra = amount;
		if(this.currentChakra > this.maxChakra) {
			this.currentChakra = this.maxChakra;
		}
	}

	@Override
	public int getChakra() {
		return this.currentChakra;
	}
	
	@Override
	public ResourceLocation getKey() {
		return chakraRL;
	}

	@Override
	public ICapabilityProvider getProvider() {
		return new CapabilityProvider<>(NarutoCapabilities.CHAKRA);
	}

	@Override
	public void dataChanged(EntityPlayerMP player) {
		NetworkHandler.INSTANCE.sendTo(new MessageCapability(NarutoCapabilities.CHAKRA, serializeNBT()), player);
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("currentChakra", this.currentChakra);
		nbt.setInteger("maxChakra", this.maxChakra);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if(nbt.hasKey("currentChakra")) {
			nbt.getInteger("currentChakra");
		}
		
		if(nbt.hasKey("maxChakra")) {
			nbt.getInteger("maxChakra");
		}
	}
}
