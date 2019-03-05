package com.nmg.me.entity.projectile;

import com.nmg.me.init.MEEntityTypes;
import com.nmg.me.init.MEItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGoldArrow extends EntityArrow
{
	public EntityGoldArrow(World worldIn)
	{
		super(MEEntityTypes.GOLD_ARROW, worldIn);
		this.setDamage(3.0D);
	}

	public EntityGoldArrow(World worldIn, double x, double y, double z)
	{
		super(MEEntityTypes.GOLD_ARROW, x, y, z, worldIn);
		this.setDamage(3.0D);
	}

	public EntityGoldArrow(World worldIn, EntityLivingBase shooter)
	{
		super(MEEntityTypes.GOLD_ARROW, shooter, worldIn);
		this.setDamage(3.0D);
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MEItems.GOLD_ARROW);
	}
}
