package com.nmg.me.entity.projectile;

import com.nmg.me.init.MEEntityTypes;
import com.nmg.me.init.MEItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDiamondArrow extends EntityArrow
{
	public EntityDiamondArrow(World worldIn)
	{
		super(MEEntityTypes.DIAMOND_ARROW, worldIn);
		this.setDamage(4.0D);
	}

	public EntityDiamondArrow(World worldIn, double x, double y, double z)
	{
		super(MEEntityTypes.DIAMOND_ARROW, x, y, z, worldIn);
		this.setDamage(4.0D);
	}

	public EntityDiamondArrow(World worldIn, EntityLivingBase shooter)
	{
		super(MEEntityTypes.DIAMOND_ARROW, shooter, worldIn);
		this.setDamage(4.0D);
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MEItems.DIAMOND_ARROW);
	}
}
