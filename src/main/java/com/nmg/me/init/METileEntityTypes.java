package com.nmg.me.init;

import com.nmg.me.Constants;
import com.nmg.me.handlers.RegistryHandler;
import com.nmg.me.tileentity.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

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
		CRATE = createTileEntityType("crate", TileEntityCrate::new);
		LARGE_CHEST = createTileEntityType("large_chest", TileEntityLargeChest::new);
		BOOKSHELF_CABINET = createTileEntityType("bookshelf_cabinet", TileEntityBookshelf.Cabinet::new);
		BOOKSHELF = createTileEntityType("bookshelf_shelf", TileEntityBookshelf.Shelf::new);
		DISPLAY_CABINET = createTileEntityType("display_cabinet", TileEntityDisplayCabinet::new);
		STORAGE_SHELF = createTileEntityType("storage_shelf", TileEntityStorageShelf::new);
	}

	private static <T extends TileEntity> TileEntityType<T> createTileEntityType(String id, Supplier<T> supplier)
	{
		TileEntityType<T> type = TileEntityType.Builder.create(supplier).build(null);
		type.setRegistryName(new ResourceLocation(Constants.MODID, id));

		return type;
	}

	public static void register()
	{
		RegistryHandler.TileEntityTypes.add(CRATE);
		RegistryHandler.TileEntityTypes.add(LARGE_CHEST);
		RegistryHandler.TileEntityTypes.add(BOOKSHELF_CABINET);
		RegistryHandler.TileEntityTypes.add(BOOKSHELF);
		RegistryHandler.TileEntityTypes.add(DISPLAY_CABINET);
		RegistryHandler.TileEntityTypes.add(STORAGE_SHELF);
	}

}
