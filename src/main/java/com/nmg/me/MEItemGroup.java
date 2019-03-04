package com.nmg.me;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MEItemGroup extends ItemGroup
{

	private ItemStack icon;

	public MEItemGroup(String label)
	{
		super(label);
	}

	public MEItemGroup(int index, String label)
	{
		super(index, label);
	}

	public void setIcon(Block icon)
	{
		this.icon = new ItemStack(icon);
	}

	public void setIcon(Item icon)
	{
		this.icon = new ItemStack(icon);
	}

	@Override
	public ItemStack createIcon()
	{
		return this.icon;
	}
}
