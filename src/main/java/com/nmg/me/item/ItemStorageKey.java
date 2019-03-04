package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import com.nmg.me.init.MEWorldProviders;
import com.nmg.me.world.METeleporter;
import com.nmg.me.world.storage.WorldStorageSavedData;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStorageKey extends Item
{

	public ItemStorageKey()
	{
		super((new Item.Properties()).group(MinecraftExtended.ITEMS));
	}

	/*@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (playerIn instanceof EntityPlayerMP)
		{
			int dim = METeleporter.getCurrentDimension(playerIn);
			WorldStorageSavedData worldStorageSavedData = WorldStorageSavedData.get(worldIn);


			if (dim != MEWorldProviders.dimIdStorage)
			{
				worldStorageSavedData.addDimensionPos(playerIn, 0);
				BlockPos pos = worldStorageSavedData.getDimensionPos(playerIn, MEWorldProviders.dimIdStorage);
				METeleporter.teleportToDimension(playerIn, MEWorldProviders.dimIdStorage, pos);
			}
			else
			{
				worldStorageSavedData.addDimensionPos(playerIn, MEWorldProviders.dimIdStorage);
				BlockPos pos = worldStorageSavedData.getDimensionPos(playerIn, 0);
				METeleporter.teleportToDimension(playerIn, 0, pos);
			}
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}*/

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation(this.getTranslationKey() + ".desc").applyTextStyle(TextFormatting.YELLOW));
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

}
