package com.nmg.me.tileentity;

import com.nmg.me.client.gui.container.ContainerLargeChest;
import com.nmg.me.init.METileEntityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileEntityLargeChest extends METileEntity
{
	public TileEntityLargeChest()
	{
		super(METileEntityTypes.LARGE_CHEST, "large_chest", 54);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		this.fillWithLoot(playerIn);
		return new ContainerLargeChest(playerInventory, this);
	}
}
