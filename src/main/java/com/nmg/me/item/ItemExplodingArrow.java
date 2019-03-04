package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemExplodingArrow extends ItemArrow
{

	public ItemExplodingArrow(Properties builder)
	{
		super(builder);
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		return new EntityExplodingArrow(worldIn, shooter);
	}
}
