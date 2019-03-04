package com.nmg.me.client.gui;

import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCrate extends GuiContainer
{
	private static final ResourceLocation CRATE_GUI_TEXTURE = new ResourceLocation(Constants.MODID,"textures/gui/container/generic_27.png");

	private InventoryPlayer inventoryPlayer;

	public GuiCrate(Container inventorySlotsIn, InventoryPlayer playerInv)
	{
		super(inventorySlotsIn);
		this.inventoryPlayer = playerInv;
		this.ySize = 168;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String name = I18n.format(MEBlocks.CRATE.getTranslationKey());
		MEUtils.renderText(name, xSize, 6, 0x404040);
		MEUtils.renderText(inventoryPlayer.getDisplayName().getUnformattedComponentText(), 8, ySize - 94, 0x404040, false);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(CRATE_GUI_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
