package com.nmg.me.client.gui;

import net.minecraft.item.ItemStack;

public interface ISimpleInventory
{

	void clear();
	int getSize();
	ItemStack getStack(int index);

}
