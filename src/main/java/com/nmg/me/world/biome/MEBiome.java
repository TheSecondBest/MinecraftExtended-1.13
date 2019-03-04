package com.nmg.me.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class MEBiome extends Biome
{

	private BiomeManager.BiomeType biomeType;
	private BiomeDictionary.Type[] types;
	private int weight;

	public MEBiome(Biome.BiomeBuilder biomeBuilder, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... biomeTypes)
	{
		super(biomeBuilder);
		this.biomeType = biomeType;
		this.weight = weight;
		this.types = biomeTypes;
	}

	public int getWeight()
	{
		return weight;
	}

	public BiomeManager.BiomeType getBiomeType()
	{
		return biomeType;
	}

	public BiomeDictionary.Type[] getTypes()
	{
		return types;
	}

}
