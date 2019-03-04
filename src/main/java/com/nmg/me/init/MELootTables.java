package com.nmg.me.init;

import com.nmg.me.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class MELootTables
{

	public static ResourceLocation ELDER_GUARDIAN_INJECT;

	public static void register()
	{
		ELDER_GUARDIAN_INJECT = LootTableList.register(new ResourceLocation(Constants.MODID, "inject/elder_guardian"));
	}

}
