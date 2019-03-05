package com.nmg.me.handlers;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.init.MEEntityTypes;
import com.nmg.me.world.biome.MEBiome;
import com.nmg.me.block.BlockPierDoor;
import com.nmg.me.init.MEBiomes;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.init.MEItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RegistryHandler
{

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Blocks
	{

		private static final List<Block> BLOCKS = new LinkedList<>();

		public static void add(Block block)
		{
			BLOCKS.add(block);
		}

		public static List<Block> getBlocks()
		{
			return Collections.unmodifiableList(BLOCKS);
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			MEBlocks.register();
			BLOCKS.stream().forEach(block -> event.getRegistry().register(block));
		}

	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Items
	{

		private static final List<Item> ITEMS = new LinkedList<>();

		public static void add(Item item)
		{
			ITEMS.add(item);
		}

		public static List<Item> getItems()
		{
			return Collections.unmodifiableList(ITEMS);
		}

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			MEItems.register();
			ITEMS.stream().forEach(item -> event.getRegistry().register(item));
		}
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class EntityTypes
	{
		private static final List<EntityType<?>> ENTITY_TYPES = new LinkedList<>();

		public static <T extends Entity> void add(EntityType<T> type)
		{
			ENTITY_TYPES.add(type);
		}

		public static List<EntityType<?>> getEntityTypes()
		{
			return Collections.unmodifiableList(ENTITY_TYPES);
		}

		@SubscribeEvent
		public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event)
		{
			MEEntityTypes.register();
			ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));
		}
	}

	/*@Mod.EventBusSubscriber(modid = Constants.MODID)
	public static class Biomes
	{

		private static final List<MEBiome> BIOMES = new LinkedList<>();

		public static void add(MEBiome biome)
		{
			BIOMES.add(biome);
		}

		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event)
		{
			BIOMES.stream().forEach(biome -> {
				event.getRegistry().register(biome);
				MinecraftExtended.logger.debug("Biome registered: " + biome.getRegistryName());
				BiomeDictionary.addTypes(biome, biome.getTypes());
				BiomeManager.addBiome(biome.getBiomeType(), new BiomeManager.BiomeEntry(biome, biome.getWeight()));
				BiomeManager.addSpawnBiome(biome);
				MinecraftExtended.logger.debug("Biome added: " + biome.getRegistryName());
			});
		}
	}*/

}
