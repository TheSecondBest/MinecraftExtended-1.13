package com.nmg.me.client.renderer.entity;

import com.nmg.me.Constants;
import com.nmg.me.entity.projectile.EntityEmeraldArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderEmeraldArrow extends RenderArrow<EntityEmeraldArrow>
{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "textures/entity/projectiles/emerald_arrow.png");

	public RenderEmeraldArrow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityEmeraldArrow entity)
	{
		return TEXTURE;
	}
}
