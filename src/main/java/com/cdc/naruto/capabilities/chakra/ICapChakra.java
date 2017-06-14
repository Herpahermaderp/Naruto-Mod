package com.cdc.naruto.capabilities.chakra;

import com.cdc.naruto.capabilities.ICapability;

public interface ICapChakra extends ICapability {
	void diminishChakra(float amount);
	void replenishChakra();
	void fillChakra(float amount);
	void setChakra(float amount);
	float getChakra();
}
