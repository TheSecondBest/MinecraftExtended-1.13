package com.nmg.me.client.gui.interactionobjects;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerCarpentersTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class InteractionObjectCarpentersTable implements IInteractionObject
{

	private BlockPos pos;

	public InteractionObjectCarpentersTable(BlockPos pos)
	{
		this.pos = pos;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerCarpentersTable(playerInventory, playerIn.world, this.pos);
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":carpenters_table_gui";
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
