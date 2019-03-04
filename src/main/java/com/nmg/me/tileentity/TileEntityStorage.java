package com.nmg.me.tileentity;

import com.nmg.me.client.gui.ISimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class TileEntityStorage extends METileEntityClientSync implements ISimpleInventory
{

	private final NonNullList<ItemStack> INVENTORY;
	private int itemCount = 0;

	public TileEntityStorage(TileEntityType type, int inventorySize)
	{
		super(type);
		this.INVENTORY = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
	}

	public void addItem(ItemStack stack)
	{
		if (this.itemCount < this.INVENTORY.size())
		{
			this.INVENTORY.set(this.itemCount++, stack.split(1));
			this.markDirty();
		}
	}

	public ItemStack removeItem()
	{
		if (this.itemCount > 0)
		{
			ItemStack stack = this.INVENTORY.set(--this.itemCount, ItemStack.EMPTY);
			this.markDirty();
			return stack;
		}

		return ItemStack.EMPTY;
	}

	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		super.write(compound);

		compound.setInt("ItemCount", this.itemCount);

		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.itemCount; i++)
		{
			NBTTagCompound itemTag = new NBTTagCompound();
			this.getStack(i).write(itemTag);
			itemList.add(itemTag);
		}

		compound.setTag("Items", itemList);

		return compound;
	}

	@Override
	public void read(NBTTagCompound compound)
	{
		super.read(compound);

		this.itemCount = compound.getInt("ItemCount");
		NBTTagList itemList = compound.getList("Items", 10);

		for (int i = 0; i < itemList.size(); i++)
		{
			NBTTagCompound itemTag = itemList.getCompound(i);
			this.INVENTORY.set(i, ItemStack.read(itemTag));
		}
	}

	@Override
	public void clear()
	{
		this.INVENTORY.clear();
		this.itemCount = 0;
		this.markDirty();
	}

	@Override
	public int getSize()
	{
		return this.itemCount;
	}

	@Override
	public ItemStack getStack(int index)
	{
		if (index < 0 || index >= this.itemCount)
			return ItemStack.EMPTY;

		return this.INVENTORY.get(index);
	}
}
