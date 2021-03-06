package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import com.nmg.me.entity.projectile.EntityEmeraldArrow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEmeraldArrow extends ItemArrow
{

	public ItemEmeraldArrow(Properties builder)
	{
		super(builder);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation("description.arrow_damage", String.valueOf(7.5)));
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		return new EntityEmeraldArrow(worldIn, shooter);
	}
}
