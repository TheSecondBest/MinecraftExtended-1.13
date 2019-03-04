package com.nmg.me.client.gui.container;

import com.nmg.me.init.MEBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IRecipeContainer;

public class ContainerCarpentersTable extends Container implements IRecipeContainer
{

	public InventoryCrafting craftingMatrix = new InventoryCrafting(this, 5, 5);
	public InventoryCraftResult craftingResult = new InventoryCraftResult();

	private final BlockPos pos;
	private final World world;
	private final EntityPlayer player;

	public ContainerCarpentersTable(InventoryPlayer playerInv, World world, BlockPos pos)
	{
		this.pos = pos;
		this.world = world;
		this.player = playerInv.player;

		this.addSlot(new SlotCrafting(playerInv.player, this.craftingMatrix, this.craftingResult, 0, 142, 53));

		for (int i = 0; i < 5; ++i)
		{
			for (int j = 0; j < 5; ++j)
			{
				int index = j + i * 5;
				this.addSlot(new Slot(this.craftingMatrix, index, 38 + j * 18, 16 + i * 18));
			}
		}

		for (int k = 0; k < 3; ++k)
		{
			for (int i1 = 0; i1 < 9; ++i1)
			{
				int index = i1 + k * 9 + 25;
				this.addSlot(new Slot(playerInv, index, 8 + i1 * 18, 118 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l)
		{
			this.addSlot(new Slot(playerInv, l, 8 + l * 18, 176));
		}

	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn)
	{
		this.slotChangedCraftingGrid(this.world, this.player, this.craftingMatrix, this.craftingResult);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);

		if (!this.world.isRemote)
		{
			this.clearContainer(playerIn, this.world, this.craftingMatrix);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		if (this.world.getBlockState(this.pos).getBlock() != MEBlocks.CARPENTERS_TABLE)
			return false;
		else
			return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0)
			{
				itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

				if (!this.mergeItemStack(itemstack1, 10, 46, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (index >= 10 && index < 37)
			{
				if (!this.mergeItemStack(itemstack1, 37, 46, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (index >= 37 && index < 46)
			{
				if (!this.mergeItemStack(itemstack1, 10, 37, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 10, 46, false))
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

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

			if (index == 0)
			{
				playerIn.dropItem(itemstack2, false);
			}
		}

		return itemstack;
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
	{
		return slotIn.inventory != this.craftingResult && super.canMergeSlot(stack, slotIn);
	}

	@Override
	public InventoryCraftResult getCraftResult()
	{
		return this.craftingResult;
	}

	@Override
	public InventoryCrafting getCraftMatrix()
	{
		return this.craftingMatrix;
	}
}
