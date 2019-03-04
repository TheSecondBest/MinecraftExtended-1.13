package com.nmg.me.client.gui.interactionobjects;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerCrate;
import com.nmg.me.tileentity.TileEntityCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class InteractionObjectCrate implements IInteractionObject
{

	private TileEntityCrate crate;

	public InteractionObjectCrate(TileEntityCrate crate)
	{
		this.crate = crate;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerCrate(playerInventory, this.crate);
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":crate_gui";
	}

	@Override
	public ITextComponent getName()
	{
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Nullable
	@Override
	public ITextComponent getCustomName()
	{
		return null;
	}
}
