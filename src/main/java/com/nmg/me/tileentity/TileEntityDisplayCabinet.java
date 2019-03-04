package com.nmg.me.tileentity;

import com.nmg.me.init.MEBlocks;
import com.nmg.me.init.METileEntityTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityDisplayCabinet extends METileEntityClientSync
{

	private ItemStack currentItem;

	public TileEntityDisplayCabinet()
	{
		super(METileEntityTypes.DISPLAY_CABINET);
		this.currentItem = ItemStack.EMPTY;
	}

	public void setItem(ItemStack stack)
	{
		if (this.currentItem.isEmpty() && stack.getItem() != Item.getItemFromBlock(MEBlocks.DISPLAY_CABINET))
		{
			this.currentItem = stack.split(1);
			this.markDirty();
		}
	}

	public ItemStack removeItem()
	{
		ItemStack copy = this.currentItem.copy();
		this.currentItem = ItemStack.EMPTY;
		this.markDirty();
		return copy;
	}

	/*@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
		return (oldState.getBlock() != newSate.getBlock());
	}*/

	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		super.write(compound);

		if (!this.currentItem.isEmpty())
		{
			NBTTagCompound stackTag = new NBTTagCompound();
			this.currentItem.write(stackTag);
			compound.setTag("Item", stackTag);
		}

		return compound;
	}

	@Override
	public void read(NBTTagCompound compound)
	{
		super.read(compound);

		if (compound.hasKey("Item"))
		{
			this.currentItem = ItemStack.read((NBTTagCompound) compound.getTag("Item"));
		}
		else
		{
			this.currentItem = ItemStack.EMPTY;
		}
	}

	public ItemStack getCurrentItem()
	{
		return this.currentItem;
	}
}
