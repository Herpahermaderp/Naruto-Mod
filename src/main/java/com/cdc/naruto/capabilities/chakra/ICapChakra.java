package com.cdc.naruto.capabilities.chakra;

import com.cdc.naruto.capabilities.ICapability;

public interface ICapChakra extends ICapability {
	void diminishChakra(int amount);
	void replenishChakra();
	void fillChakra(int amount);
	void setChakra(int amount);
	int getChakra();
}
