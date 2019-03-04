package com.nmg.me.client.renderer.entity;

import com.nmg.me.Constants;
import com.nmg.me.entity.projectile.EntityExplodingArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderExplodingArrow extends RenderArrow<EntityExplodingArrow>
{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/projectiles/exploding_arrow.png");

	public RenderExplodingArrow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityExplodingArrow entity)
	{
		return TEXTURE;
	}
}
