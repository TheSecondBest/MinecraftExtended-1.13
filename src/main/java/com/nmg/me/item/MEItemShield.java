package com.nmg.me.item;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.client.renderer.tileentity.TileEntityItemStackShieldRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class MEItemShield extends ItemShield
{

	private final ResourceLocation TEXTURE;
	private Item repairItem;

	public MEItemShield(String id, Item.Properties properties)
	{
		super(properties.group(MinecraftExtended.COMBAT).setTEISR(() -> TileEntityItemStackShieldRenderer::new));
		TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/shield/" + id + ".png");
	}

	public MEItemShield setRepairItem(Item repairItem)
	{
		this.repairItem = repairItem;
		return this;
	}

	@Override
	public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public String getTranslationKey(ItemStack stack)
	{
		return this.getTranslationKey();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return this.repairItem != null && repair.getItem() == this.repairItem;
	}

	public ResourceLocation getTexture()
	{
		return TEXTURE;
	}
}
