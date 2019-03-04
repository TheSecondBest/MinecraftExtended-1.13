package com.nmg.me.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeStorage extends MEBiome
{
	public BiomeStorage(Biome.BiomeBuilder biomeBuilder)
	{
		super(biomeBuilder, BiomeManager.BiomeType.WARM, 10, BiomeDictionary.Type.VOID);
		this.setRegistryName("storage");
	}
}
