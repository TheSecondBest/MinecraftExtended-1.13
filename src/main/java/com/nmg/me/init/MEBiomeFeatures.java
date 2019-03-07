package com.nmg.me.init;

import com.nmg.me.world.gen.feature.SeaAnemoneFeature;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MEBiomeFeatures
{

	public static final Feature<SeaGrassConfig> SEA_ANEMONE_FEATURE = new SeaAnemoneFeature();

	public static void register()
	{
		registerOceanBiomeFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createCompositeFeature(
						SEA_ANEMONE_FEATURE,
						new SeaGrassConfig(40, 0.5),
						Biome.TOP_SOLID_ONCE,
						IPlacementConfig.NO_PLACEMENT_CONFIG
				),
				Biomes.DEEP_LUKEWARM_OCEAN,
				Biomes.LUKEWARM_OCEAN,
				Biomes.DEEP_OCEAN,
				Biomes.OCEAN,
				Biomes.DEEP_WARM_OCEAN,
				Biomes.WARM_OCEAN
		);
	}

	private static void registerBiomeFeature(GenerationStage.Decoration decorationStage, CompositeFeature<?, ?> feature, List<BiomeManager.BiomeType> biomeTypes, List<Biome.Category> categories)
	{
		for (BiomeManager.BiomeType biomeType : biomeTypes)
		{
			BiomeManager.getBiomes(biomeType).forEach((biomeEntry -> {
				if (categories.contains(biomeEntry.biome.getCategory()))
					biomeEntry.biome.addFeature(decorationStage, feature);
			}));
		}
	}

	private static void registerOceanBiomeFeature(GenerationStage.Decoration decorationStage, CompositeFeature<?, ?> feature, Biome... oceanBiomes)
	{
		for (Biome biome : oceanBiomes)
		{
			biome.addFeature(decorationStage, feature);
		}
	}

	private static <T> List<T> createList(T... entries)
	{
		List<T> result = new ArrayList<>();
		Collections.addAll(result, entries);
		return result;
	}

}
