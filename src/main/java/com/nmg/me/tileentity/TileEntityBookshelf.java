package com.nmg.me.tileentity;

import com.nmg.me.init.METileEntityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TileEntityBookshelf
{

	public static class Shelf extends TileEntityStorage
	{

		public Shelf()
		{
			super(METileEntityTypes.BOOKSHELF, 22);
		}

	}

	public static class Cabinet extends METileEntity
	{

		public Cabinet()
		{
			super(METileEntityTypes.BOOKSHELF_CABINET, "bookshelf_cabinet", 27);
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
		{
			this.fillWithLoot(playerIn);
			return null;
		}
	}

}
