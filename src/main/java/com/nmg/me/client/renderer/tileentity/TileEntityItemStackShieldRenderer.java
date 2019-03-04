package com.nmg.me.client.renderer.tileentity;

import com.nmg.me.item.MEItemShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ModelShield;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityItemStackShieldRenderer extends TileEntityItemStackRenderer
{

	private final ModelShield modelShield = new ModelShield();

	@Override
	public void renderByItem(ItemStack stack)
	{
		if (stack.getItem() instanceof MEItemShield)
		{
			Minecraft.getInstance().getTextureManager().bindTexture(((MEItemShield) stack.getItem()).getTexture());

			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			this.modelShield.render();

			if (stack.hasEffect())
			{
				this.renderEffect(this.modelShield::render);
			}

			GlStateManager.popMatrix();
		}
	}

	private void renderEffect(Runnable renderModelFunction)
	{
		GlStateManager.color3f(0.5019608F, 0.2509804F, 0.8F);
		Minecraft.getInstance().getTextureManager().bindTexture(ItemRenderer.RES_ITEM_GLINT);
		ItemRenderer.renderEffect(Minecraft.getInstance().getTextureManager(), renderModelFunction, 1);
	}

}
