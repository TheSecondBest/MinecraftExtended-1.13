package com.nmg.me.init;

import com.nmg.me.world.WorldProviderStorage;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class MEWorldProviders
{

	public static int dimIdStorage;

	public static DimensionType dimensionTypeStorage;

	private static void registerDimensionTypes()
	{
		/*dimIdStorage = DimensionManager.getNextFreeDimId();

		dimensionTypeStorage = DimensionType.register("Storage", "_storage", dimIdStorage, WorldProviderStorage.class, false);*/
	}

	public static void registerWorldProviders()
	{
		/*registerDimensionTypes();
		DimensionManager.registerDimension(dimIdStorage, dimensionTypeStorage);*/
	}

}
