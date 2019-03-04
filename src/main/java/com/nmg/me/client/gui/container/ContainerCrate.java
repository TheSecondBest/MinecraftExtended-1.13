package com.nmg.me.client.gui.container;

import com.nmg.me.client.gui.slot.SlotCrate;
import com.nmg.me.tileentity.TileEntityCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerCrate extends Container
{

	private final TileEntityCrate crate;

	public ContainerCrate(InventoryPlayer playerInv, TileEntityCrate crate)
	{
		this.crate = crate;
		LazyOptional<IItemHandler> inventoryOptional = this.crate.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		IItemHandler inventory = inventoryOptional.orElse(null);
		int numRows = inventory.getSlots() / 9;
		int a = (numRows - 4) * 18;

		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlot(new SlotCrate(crate, inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
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
		return this.crate.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

			if (index < containerSlots)
			{
				if (!this.mergeItemStack(stack1, containerSlots, inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(stack1, 0, containerSlots, false))
			{
				return ItemStack.EMPTY;
			}

			if (stack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (stack1.getCount() == stack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, stack1);
		}

		return stack;
	}
}
