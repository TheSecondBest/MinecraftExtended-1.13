package com.nmg.me.init;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.block.*;
import com.nmg.me.handlers.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class MEBlocks
{

	public static final Block STORAGE_WALL;
	public static final Block LARGE_CHEST;
	public static final Block PIER, PIER_STAIR, PIER_SLAB, PIER_BENCH, PIER_GLASS_PANE, PIER_CHAIR;
	public static final Block LANTERN;
	public static final Block LAMP_POST;
	public static final Block CRATE;
	public static final Block CARPENTERS_TABLE;
	public static final Block BARREL;
	public static final Block PIER_FENCE, PIER_FENCE_GATE;
	public static final Block PIER_BASE;
	public static final Block PIER_CHANDELIER;
	public static final Block PIER_DOOR;
	public static final Block PIER_BRIDGE;
	public static final Block PIER_BOOKSHELF;
	public static final Block DISPLAY_CABINET;
	public static final Block STORAGE_SHELF;
	public static final Block CANDLE;
	public static final Block PINK_SEA_ANEMONE, BLUE_SEA_ANEMONE;

	static
	{
		STORAGE_WALL = new BlockStorageWall();
		LARGE_CHEST = new BlockLargeChest();
		PIER = new BlockPier();
		PIER_SLAB = new BlockPierHalfSlab();
		LANTERN = new BlockLantern();
		LAMP_POST = new BlockLampPost();
		CRATE = new BlockCrate();
		PIER_BENCH = new BlockPierBench();
		CARPENTERS_TABLE = new BlockCarpentersTable();
		PIER_GLASS_PANE = new BlockPierGlassPane();
		BARREL = new BlockBarrel();
		PIER_CHAIR = new BlockPierChair();
		PIER_FENCE = new BlockFence(Block.Properties.create(Material.WOOD, Blocks.OAK_PLANKS.getMapColor(null, null, null)).hardnessAndResistance(2.0f, 5.0f));
		PIER_FENCE_GATE = new MEBlockFenceGate(Block.Properties.create(Material.WOOD, Blocks.OAK_PLANKS.getMapColor(null, null, null)));
		PIER_BASE = new BlockPierBase();
		PIER_CHANDELIER = new BlockChandelier();
		PIER_DOOR = new BlockPierDoor();
		PIER_BRIDGE = new BlockPierBridge();
		PIER_STAIR = new BlockPierStair();
		PIER_BOOKSHELF = new BlockPierBookshelf();
		DISPLAY_CABINET = new BlockDisplayCabinet();
		STORAGE_SHELF = new BlockStorageShelf();
		CANDLE = new BlockCandle();
		PINK_SEA_ANEMONE = new BlockSeaAnemone();
		BLUE_SEA_ANEMONE = new BlockSeaAnemone();
	}

	public static void register()
	{
		registerBlock("storage_wall", STORAGE_WALL);
		registerBlock("large_chest", LARGE_CHEST);
		registerBlock("pier", PIER);
		registerBlock("pier_slab", PIER_SLAB);
		registerBlock("lantern", LANTERN);
		registerBlock("lamp_post", LAMP_POST);
		registerBlock("crate", CRATE);
		registerBlock("pier_bench", PIER_BENCH);
		registerBlock("carpenters_table", CARPENTERS_TABLE);
		registerBlock("pier_glass_pane", PIER_GLASS_PANE);
		registerBlock("barrel", BARREL);
		registerBlock("pier_chair", PIER_CHAIR);
		registerBlock("pier_fence", PIER_FENCE);
		registerBlock("pier_fence_gate", PIER_FENCE_GATE);
		registerBlock("pier_base", PIER_BASE);
		registerBlock("chandelier", PIER_CHANDELIER);
		registerBlock("pier_door", PIER_DOOR, false);
		registerBlock("pier_bridge", PIER_BRIDGE);
		registerBlock("pier_bookshelf", PIER_BOOKSHELF);
		registerBlock("display_cabinet", DISPLAY_CABINET);
		registerBlock("storage_shelf", STORAGE_SHELF);
		registerBlock("candle", CANDLE);
		registerBlock("pink_sea_anemone", PINK_SEA_ANEMONE);
		registerBlock("blue_sea_anemone", BLUE_SEA_ANEMONE);

		// README: Always register stair LAST

		registerBlock("pier_stair", PIER_STAIR);

	}

	private static void registerBlock(String id, Block block)
	{
		registerBlock(id, block, true);
	}

	private static void registerBlock(String id, Block block, boolean inGroup)
	{
		Item.Properties properties = new Item.Properties();

		if (inGroup)
			properties.group(MinecraftExtended.BLOCKS);

		if (block instanceof IExtendedItemProperties)
		{
			properties = ((IExtendedItemProperties)block).getExtendedProperties(properties);
		}

		registerBlock(id, block, new ItemBlock(block, properties));
	}

	private static void registerBlock(String id, Block block, ItemBlock item)
	{
		block.setRegistryName(new ResourceLocation(Constants.MODID, id));
		RegistryHandler.Blocks.add(block);

		if (block.getRegistryName() != null)
		{
			item.setRegistryName(block.getRegistryName());
			RegistryHandler.Items.add(item);
		}
	}


}
