package com.nmg.me.client.gui;

import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiLargeChest extends GuiContainer
{

	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("minecraft:textures/gui/container/generic_54.png");

	private InventoryPlayer inventoryPlayer;

	public GuiLargeChest(Container inventorySlotsIn, InventoryPlayer inventoryPlayerIn)
	{
		super(inventorySlotsIn);
		this.inventoryPlayer = inventoryPlayerIn;
		this.ySize = 232;
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
		String name = I18n.format(MEBlocks.LARGE_CHEST.getTranslationKey());
		MEUtils.renderText(name, xSize, 6, 0x404040);
		MEUtils.renderText(inventoryPlayer.getDisplayName().getUnformattedComponentText(), 8, ySize - 104, 0x404040, false);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
