package com.nmg.me.item;

import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.entity.projectile.EntityArrow;

public class ItemEmeraldBow extends MEItemBow
{
	public ItemEmeraldBow()
	{
		super(9.5f);
	}

	@Override
	protected void applyArrowModifications(EntityArrow arrow)
	{
		if (arrow instanceof EntityExplodingArrow)
			((EntityExplodingArrow)arrow).setLandFuse(40);
	}
}
