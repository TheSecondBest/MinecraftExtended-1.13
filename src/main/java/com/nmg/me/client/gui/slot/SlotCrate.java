package com.nmg.me.client.gui.slot;

import com.nmg.me.block.BlockCrate;
import com.nmg.me.tileentity.TileEntityCrate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotCrate extends SlotItemHandler
{
	private final TileEntityCrate crate;

	public SlotCrate(TileEntityCrate crate, IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
		this.crate = crate;
	}

	@Override
	public void onSlotChanged()
	{
		this.crate.markDirty();
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack != null && !(Block.getBlockFromItem(stack.getItem()) instanceof BlockCrate);
	}
}
