package com.nmg.me.client.gui.interactionobjects;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerBookshelfCabinet;
import com.nmg.me.tileentity.TileEntityBookshelf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class InteractionObjectBookshelf implements IInteractionObject
{

	private TileEntityBookshelf.Cabinet cabinet;

	public InteractionObjectBookshelf(TileEntityBookshelf.Cabinet cabinet)
	{
		this.cabinet = cabinet;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerBookshelfCabinet(playerInventory, this.cabinet);
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":bookshelf_cabinet_gui";
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
