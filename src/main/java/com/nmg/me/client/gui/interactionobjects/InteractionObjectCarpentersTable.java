package com.nmg.me.client.gui.interactionobjects;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerCarpentersTable;
import com.nmg.me.init.MEBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class InteractionObjectCarpentersTable implements IInteractionObject
{

	private final World world;
	private BlockPos pos;

	public InteractionObjectCarpentersTable(World world, BlockPos pos)
	{
		this.world = world;
		this.pos = pos;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerCarpentersTable(playerInventory, this.world, this.pos);
	}

	@Override
	public String getGuiID()
	{
		return Constants.MODID + ":carpenters_table_gui";
	}

	@Override
	public ITextComponent getName()
	{
		return new TextComponentTranslation(MEBlocks.CARPENTERS_TABLE.getTranslationKey());
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
