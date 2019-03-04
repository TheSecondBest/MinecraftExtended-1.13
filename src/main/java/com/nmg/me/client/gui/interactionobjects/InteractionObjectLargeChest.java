package com.nmg.me.client.gui.interactionobjects;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerLargeChest;
import com.nmg.me.tileentity.TileEntityLargeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class InteractionObjectLargeChest implements IInteractionObject
{

	private TileEntityLargeChest chest;

	public InteractionObjectLargeChest(TileEntityLargeChest chest)
	{
		this.chest = chest;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerLargeChest(playerInventory, this.chest);
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":large_chest_gui";
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
