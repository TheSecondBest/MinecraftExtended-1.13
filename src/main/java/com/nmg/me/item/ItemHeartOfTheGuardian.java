package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHeartOfTheGuardian extends Item
{
	public ItemHeartOfTheGuardian()
	{
		super(new Item.Properties().group(MinecraftExtended.ITEMS));
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
}
