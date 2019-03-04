package com.nmg.me.event;

import com.nmg.me.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootEventHandler
{

	@SubscribeEvent
	public void onLootTableLoaded(LootTableLoadEvent event)
	{
		if (event.getName().toString().equals("minecraft:entities/elder_guardian"))
		{
			event.getTable().addPool(getInjectPool("elder_guardian"));
			System.out.println("Injected Loot Pool into Elder Guardian");
		}
	}

	private LootPool getInjectPool(String entryName)
	{
		return new LootPool(new LootEntry[]{getInjectEntry(entryName, 1)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "minecraft_extended_inject_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight)
	{
		return new LootEntryTable(new ResourceLocation(Constants.MODID, "inject/" + name), weight, 0, new LootCondition[0], "minecraft_extended_inject_entry");
	}

}
