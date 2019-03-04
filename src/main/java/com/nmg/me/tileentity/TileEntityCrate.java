package com.nmg.me.tileentity;

import com.nmg.me.client.gui.container.ContainerCrate;
import com.nmg.me.init.METileEntityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCrate extends METileEntity
{
	public TileEntityCrate()
	{
		super(METileEntityTypes.CRATE, "crate", 27);
	}

	@Override
	public void read(NBTTagCompound compound)
	{
		super.read(compound);
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		this.fillWithLoot(playerIn);
		return new ContainerCrate(playerInventory, this);
	}
}
