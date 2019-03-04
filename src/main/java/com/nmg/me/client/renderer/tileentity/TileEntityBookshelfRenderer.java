package com.nmg.me.client.renderer.tileentity;

import com.nmg.me.block.MEBlockFacing;
import com.nmg.me.tileentity.TileEntityBookshelf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumFacing;

public class TileEntityBookshelfRenderer extends TileEntityRenderer<TileEntityBookshelf.Shelf>
{

	@Override
	public void render(TileEntityBookshelf.Shelf shelf, double x, double y, double z, float partialTicks, int destroyStage)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.disableLighting();

			switch (shelf.getBlockState().get(MEBlockFacing.FACING))
			{
				case NORTH:
					GlStateManager.translated(x + 0.125, y + 0.25, z - 0.05);
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

			GlStateManager.rotatef(60, 0, 0, 1);
			GlStateManager.scalef(0.75f, 0.75f, 1.5f);

			double row = 0;
			int element;
			double yPos = 0;

			for (int i = 0; i < shelf.getSize(); i++)
			{
				if (i >= 11 && row == 0)
				{
					row = 0.8;
				}

				if (row > 0)
				{
					element = i - 11;
					yPos = 0.2;
				}
				else
				{
					element = i;
				}

				EntityItem item = new EntityItem(Minecraft.getInstance().world, 0, 0, 0, shelf.getStack(i));
				item.hoverStart = 0;
				Minecraft.getInstance().getRenderManager().renderEntity(item, 0.6 * row, yPos, element * 0.05, 0, 0, false);
			}

			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}

}
