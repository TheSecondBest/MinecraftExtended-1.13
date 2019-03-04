package com.nmg.me.init;

import com.nmg.me.tileentity.*;
import net.minecraft.tileentity.TileEntityType;

public class METileEntityTypes
{

	public static final TileEntityType<TileEntityCrate> CRATE;
	public static final TileEntityType<TileEntityLargeChest> LARGE_CHEST;
	public static final TileEntityType<TileEntityBookshelf.Cabinet> BOOKSHELF_CABINET;
	public static final TileEntityType<TileEntityBookshelf.Shelf> BOOKSHELF;
	public static final TileEntityType<TileEntityDisplayCabinet> DISPLAY_CABINET;
	public static final TileEntityType<TileEntityStorageShelf> STORAGE_SHELF;

	static
	{
		CRATE = TileEntityType.register("pme:crate", TileEntityType.Builder.create(TileEntityCrate::new));
		LARGE_CHEST = TileEntityType.register("pme:large_chest", TileEntityType.Builder.create(TileEntityLargeChest::new));
		BOOKSHELF_CABINET = TileEntityType.register("pme:bookshelf_cabinet", TileEntityType.Builder.create(TileEntityBookshelf.Cabinet::new));
		BOOKSHELF = TileEntityType.register("pme:bookshelf_shelf", TileEntityType.Builder.create(TileEntityBookshelf.Shelf::new));
		DISPLAY_CABINET = TileEntityType.register("pme:display_cabinet", TileEntityType.Builder.create(TileEntityDisplayCabinet::new));
		STORAGE_SHELF = TileEntityType.register("pme:storage_shelf", TileEntityType.Builder.create(TileEntityStorageShelf::new));
	}

}
