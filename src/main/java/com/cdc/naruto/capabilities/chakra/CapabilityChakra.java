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
	
	private float maxChakra = 100.0F;
	private float currentChakra = maxChakra;
	
	@Override
	public void diminishChakra(float amount) {
		this.currentChakra -= amount;
		if (this.currentChakra < 0.0F) {
			this.currentChakra = 0.0F;
		}
	}

	@Override
	public void replenishChakra() {
		while(this.currentChakra < this.maxChakra) {
			currentChakra++;
		}
	}

	@Override
	public void fillChakra(float amount) {
		this.currentChakra += amount;
		if(this.currentChakra > this.maxChakra) {
			this.currentChakra = this.maxChakra;
		}
	}
	
	@Override
	public void setChakra(float amount) {
		this.currentChakra = amount;
		if(this.currentChakra > this.maxChakra) {
			this.currentChakra = this.maxChakra;
		}
	}

	@Override
	public float getChakra() {
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
		nbt.setFloat("currentChakra", this.currentChakra);
		nbt.setFloat("maxChakra", this.maxChakra);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if(nbt.hasKey("currentChakra")) {
			nbt.getFloat("currentChakra");
		}
		
		if(nbt.hasKey("maxChakra")) {
			nbt.getFloat("maxChakra");
		}
	}
}
