package com.nmg.me.client.renderer.entity;

import com.nmg.me.Constants;
import com.nmg.me.entity.projectile.EntityObsidianArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderObsidianArrow extends RenderArrow<EntityObsidianArrow>
{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/projectiles/obsidian_arrow.png");

	public RenderObsidianArrow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityObsidianArrow entity)
	{
		return TEXTURE;
	}

}
