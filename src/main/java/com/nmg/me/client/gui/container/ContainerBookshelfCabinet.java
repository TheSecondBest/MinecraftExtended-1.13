package com.nmg.me.client.gui.container;

import com.nmg.me.tileentity.TileEntityBookshelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBookshelfCabinet extends Container
{

	private final TileEntityBookshelf.Cabinet cabinet;
	private final int numRows;

	public ContainerBookshelfCabinet(InventoryPlayer playerInv, TileEntityBookshelf.Cabinet cabinet)
	{
		this.cabinet = cabinet;
		this.numRows = cabinet.getSizeInventory() / 9;
		int a = (this.numRows - 4) * 18;

		for (int i = 0; i < this.numRows; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlot(new Slot(this.cabinet, j + i * 9, 8 + j * 18, 18 + i * 18));
			}
		}

		for (int l = 0; l < 3; ++l)
		{
			for (int j1 = 0; j1 < 9; ++j1)
			{
				this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 104 + l * 18 + a));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1)
		{
			this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 162 + a));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.cabinet.isUsableByPlayer(playerIn);
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < this.numRows * 9)
			{
				if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

}
