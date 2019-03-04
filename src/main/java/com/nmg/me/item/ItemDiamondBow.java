package com.nmg.me.item;

import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.entity.projectile.EntityArrow;

public class ItemDiamondBow extends MEItemBow
{

	public ItemDiamondBow()
	{
		super(4.0f);
	}

	@Override
	protected void applyArrowModifications(EntityArrow arrow)
	{
		if (arrow instanceof EntityExplodingArrow)
			((EntityExplodingArrow)arrow).setLandFuse(60);
	}
}
