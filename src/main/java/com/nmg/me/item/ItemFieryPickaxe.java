package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import com.nmg.me.init.MEItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFieryPickaxe extends MEItemPickaxe
{
	public ItemFieryPickaxe()
	{
		super(MEItems.ItemTier.OBSIDIAN, 1, -2.8f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation("description.auto_smelting").applyTextStyle(TextFormatting.DARK_RED));
	}

}
