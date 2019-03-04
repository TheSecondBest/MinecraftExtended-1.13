package com.nmg.me.event;

import com.nmg.me.init.MEItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.crafting.VanillaRecipeTypes;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.List;

public class PlayerEvents
{

	/*@SubscribeEvent
	public void onPlayerHarvestBlock(BlockEvent.HarvestDropsEvent event)
	{
		if (event.getHarvester() != null)
		{
			ItemStack stack = event.getHarvester().getHeldItem(EnumHand.MAIN_HAND);
			if (this.isFiery(stack))
			{
				List<ItemStack> drops = event.getDrops();

				for (int i = 0; i < drops.size(); i++)
				{
					ItemStack smeltedDrop = FurnaceRecipes.instance().getSmeltingResult(drops.get(i));

					if (!smeltedDrop.isEmpty())
					{
						smeltedDrop.setCount(drops.get(i).getCount());
						drops.set(i, smeltedDrop);
					}
				}
			}
		}
	}*/

	/*@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		ForgeVersion.CheckResult result = ForgeVersion.getResult(Loader.instance().activeModContainer());

		if (result.status == ForgeVersion.Status.OUTDATED)
		{
			if (result.target != null)
			{
				event.player.sendMessage(new TextComponentTranslation("chat.version.outdated.text", result.target.toString()));
			}
		}
	}*/

	private boolean isFiery(ItemStack stack)
	{
		return stack.getItem() == MEItems.FIERY_PICKAXE || stack.getItem() == MEItems.FIERY_SHOVEL;
	}

}
