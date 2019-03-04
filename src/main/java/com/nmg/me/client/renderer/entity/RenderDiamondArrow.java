package com.nmg.me.client.renderer.entity;

import com.nmg.me.Constants;
import com.nmg.me.entity.projectile.EntityDiamondArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDiamondArrow extends RenderArrow<EntityDiamondArrow>
{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/projectiles/diamond_arrow.png");

	public RenderDiamondArrow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityDiamondArrow entity)
	{
		return TEXTURE;
	}
}
