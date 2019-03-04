package com.nmg.me.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class MEItemPickaxe extends ItemPickaxe
{
	public MEItemPickaxe(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties properties)
	{
		super(tier, attackDamage, attackSpeed, properties);
	}

}
