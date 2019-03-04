package com.nmg.me.client.gui.slot;

import com.nmg.me.block.BlockLargeChest;
import com.nmg.me.tileentity.TileEntityLargeChest;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotLargeChest extends SlotItemHandler
{

	private final TileEntityLargeChest chest;

	public SlotLargeChest(TileEntityLargeChest chest, IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
		this.chest = chest;
	}

	@Override
	public void onSlotChanged()
	{
		this.chest.markDirty();
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack != null && !(Block.getBlockFromItem(stack.getItem()) instanceof BlockLargeChest);
	}
}
