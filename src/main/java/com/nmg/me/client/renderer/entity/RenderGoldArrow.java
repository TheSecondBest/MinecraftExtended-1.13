package com.nmg.me.client.renderer.entity;

import com.nmg.me.Constants;
import com.nmg.me.entity.projectile.EntityGoldArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderGoldArrow extends RenderArrow<EntityGoldArrow>
{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/projectiles/gold_arrow.png");

	public RenderGoldArrow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityGoldArrow entity)
	{
		return TEXTURE;
	}
}
