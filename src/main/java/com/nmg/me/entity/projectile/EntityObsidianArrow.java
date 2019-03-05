package com.nmg.me.entity.projectile;

import com.nmg.me.init.MEEntityTypes;
import com.nmg.me.init.MEItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityObsidianArrow extends EntityArrow
{
	public EntityObsidianArrow(World worldIn)
	{
		super(MEEntityTypes.OBSIDIAN_ARROW, worldIn);
		this.setDamage(5.0D);
	}

	public EntityObsidianArrow(World worldIn, double x, double y, double z)
	{
		super(MEEntityTypes.OBSIDIAN_ARROW, x, y, z, worldIn);
		this.setDamage(5.0D);
	}

	public EntityObsidianArrow(World worldIn, EntityLivingBase shooter)
	{
		super(MEEntityTypes.OBSIDIAN_ARROW, shooter, worldIn);
		this.setDamage(5.0D);
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MEItems.OBSIDIAN_ARROW);
	}
}
