package com.nmg.me.item;

import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.entity.projectile.EntityArrow;

public class ItemGoldBow extends MEItemBow
{
	public ItemGoldBow()
	{
		super(0.9f);
	}

	@Override
	protected void applyArrowModifications(EntityArrow arrow)
	{
		if (arrow instanceof EntityExplodingArrow)
			((EntityExplodingArrow)arrow).setLandFuse(20);
	}
}
