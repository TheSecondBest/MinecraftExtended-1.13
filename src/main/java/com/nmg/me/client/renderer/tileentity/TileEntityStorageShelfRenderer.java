package com.nmg.me.client.renderer.tileentity;

import com.nmg.me.block.MEBlockFacing;
import com.nmg.me.tileentity.TileEntityStorageShelf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;

public class TileEntityStorageShelfRenderer extends TileEntityRenderer<TileEntityStorageShelf>
{

	@Override
	public void render(TileEntityStorageShelf shelf, double x, double y, double z, float partialTicks, int destroyStage)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.disableLighting();

			switch (shelf.getBlockState().get(MEBlockFacing.FACING))
			{
				case NORTH:
					GlStateManager.translated(x + 0.1, y + 0.1, z + 0.2);
					GlStateManager.rotatef(90, 0, 1, 0);
					break;
				case EAST:
					GlStateManager.translated(x + 1.05, y + 0.25, z + 0.1);
					break;
				case SOUTH:
					GlStateManager.translated(x + 0.875, y + 0.25, z + 1.05);
					GlStateManager.rotatef(270, 0, 1, 0);
					break;
				case WEST:
					GlStateManager.translated(x - 0.05, y + 0.25, z + 0.875);
					GlStateManager.rotatef(180, 0, 1, 0);
					break;
			}

			GlStateManager.rotatef(90, 1, 0, 0);
			GlStateManager.scalef(0.5f, 0.5f, 1.5f);

			double row = 0;
			int element;
			double yPos = 0;

			for (int i = 0; i < shelf.getSize(); i++)
			{
				if (i >= 2 && row == 0)
				{
					row = 0.8;
				}

				if (row > 0)
				{
					element = i - 2;
					yPos = 0.2;
				}
				else
				{
					element = i;
				}

				EntityItem item = new EntityItem(Minecraft.getInstance().world, 0, 0, 0, shelf.getStack(i));
				item.hoverStart = 0;
				Minecraft.getInstance().getRenderManager().renderEntity(item, 0, element * 0.7, 0, 0, 0, false);
			}

			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}

}
