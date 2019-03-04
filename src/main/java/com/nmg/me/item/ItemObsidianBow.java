package com.nmg.me.item;

import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.entity.projectile.EntityArrow;

public class ItemObsidianBow extends MEItemBow
{
	public ItemObsidianBow()
	{
		super(6.0f);
	}

	@Override
	protected void applyArrowModifications(EntityArrow arrow)
	{
		if (arrow instanceof EntityExplodingArrow)
			((EntityExplodingArrow)arrow).setLandFuse(80);
	}
}
