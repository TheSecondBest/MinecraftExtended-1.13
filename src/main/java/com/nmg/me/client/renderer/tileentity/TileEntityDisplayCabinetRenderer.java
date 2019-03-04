package com.nmg.me.client.renderer.tileentity;

import com.nmg.me.block.MEBlockFacing;
import com.nmg.me.tileentity.TileEntityDisplayCabinet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;

public class TileEntityDisplayCabinetRenderer extends TileEntityRenderer<TileEntityDisplayCabinet>
{

	@Override
	public void render(TileEntityDisplayCabinet te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.disableLighting();

			switch (te.getBlockState().get(MEBlockFacing.FACING))
			{
				case NORTH:
					GlStateManager.translated(x + 0.5, y, z + 0.2);
					break;
				case EAST:
					GlStateManager.translated(x + 0.8, y, z + 0.5);
					GlStateManager.rotatef(90, 0, 1, 0);
					break;
				case SOUTH:
					GlStateManager.translated(x + 0.5, y, z + 0.8);
					break;
				case WEST:
					GlStateManager.translated(x + 0.2, y, z + 0.5);
					GlStateManager.rotatef(90, 0, 1, 0);
					break;
			}

			EntityItem item = new EntityItem(Minecraft.getInstance().world, 0, 0, 0, te.getCurrentItem());
			item.hoverStart = 0;
			Minecraft.getInstance().getRenderManager().renderEntity(item, 0, 0, 0, 0, 0, false);

			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}

}
