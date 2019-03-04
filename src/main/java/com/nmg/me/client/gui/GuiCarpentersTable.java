package com.nmg.me.client.gui;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.container.ContainerCarpentersTable;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerRecipeBook;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GuiCarpentersTable extends GuiContainer implements IRecipeShownListener
{

	private static final ResourceLocation CARPENTERS_TABLE_TEXTURES = new ResourceLocation(Constants.MODID, "textures/gui/container/carpenters_table.png");

	private final GuiRecipeBook recipeBook = new GuiRecipeBook();
	private boolean widthTooNarrow;

	public GuiCarpentersTable(InventoryPlayer playerInv, World worldIn)
	{
		this(playerInv, worldIn, BlockPos.ORIGIN);
	}

	public GuiCarpentersTable(InventoryPlayer playerInv, World worldIn, BlockPos pos)
	{
		super(new ContainerCarpentersTable(playerInv, worldIn, pos));
		this.ySize = 200;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.widthTooNarrow = this.width < 379;
		this.recipeBook.func_201520_a(this.width, this.height, this.mc, this.widthTooNarrow, (ContainerRecipeBook) this.inventorySlots);
		this.guiLeft = this.recipeBook.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
		this.children.add(this.recipeBook);
		this.addButton(new GuiButtonImage(10, this.guiLeft + 8, this.height / 2 - 49, 20, 18, 0, 202, 19, CARPENTERS_TABLE_TEXTURES)
		{
			@Override
			public void onClick(double mouseX, double mouseY)
			{
				GuiCarpentersTable.this.recipeBook.func_201518_a(GuiCarpentersTable.this.widthTooNarrow);
				GuiCarpentersTable.this.recipeBook.toggleVisibility();
				GuiCarpentersTable.this.guiLeft = GuiCarpentersTable.this.recipeBook.updateScreenPosition(GuiCarpentersTable.this.widthTooNarrow, GuiCarpentersTable.this.width, GuiCarpentersTable.this.height);
				this.setPosition(GuiCarpentersTable.this.guiLeft + 5, GuiCarpentersTable.this.height / 2 - 49);
			}
		});
	}

	@Override
	public void tick()
	{
		super.tick();
		this.recipeBook.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();

		if (this.recipeBook.isVisible() && this.widthTooNarrow)
		{
			this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
			this.recipeBook.render(mouseX, mouseY, partialTicks);
		}
		else
		{
			this.recipeBook.render(mouseX, mouseY, partialTicks);
			super.render(mouseX, mouseY, partialTicks);
			this.recipeBook.renderGhostRecipe(this.guiLeft, this.guiTop, true, partialTicks);
		}

		this.renderHoveredToolTip(mouseX, mouseY);
		this.recipeBook.renderTooltip(this.guiLeft, this.guiTop, mouseX, mouseY);
	}

	@Override
	protected boolean isPointInRegion(int p_195359_1_, int p_195359_2_, int p_195359_3_, int p_195359_4_, double p_195359_5_, double p_195359_7_)
	{
		return (!this.widthTooNarrow || !this.recipeBook.isVisible()) && super.isPointInRegion(p_195359_1_, p_195359_2_, p_195359_3_, p_195359_4_, p_195359_5_, p_195359_7_);
	}

	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_)
	{
		if (this.recipeBook.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_))
		{
			return true;
		}
		else
		{
			return this.widthTooNarrow && this.recipeBook.isVisible() ? true : super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
		}
	}

	protected boolean func_195361_a(double p_195361_1_, double p_195361_3_, int p_195361_5_, int p_195361_6_, int p_195361_7_)
	{
		boolean flag = p_195361_1_ < (double) p_195361_5_ || p_195361_3_ < (double) p_195361_6_ || p_195361_1_ >= (double) (p_195361_5_ + this.xSize) || p_195361_3_ >= (double) (p_195361_6_ + this.ySize);
		return this.recipeBook.func_195604_a(p_195361_1_, p_195361_3_, this.guiLeft, this.guiTop, this.xSize, this.ySize, p_195361_7_) && flag;
	}

	@Override
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type)
	{
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
		this.recipeBook.slotClicked(slotIn);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String text = I18n.format(MEBlocks.CARPENTERS_TABLE.getTranslationKey());
		MEUtils.renderText(text, this.xSize, 5, 0x404040);
		MEUtils.renderText(I18n.format("container.inventory"), 64, this.ySize - 93, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CARPENTERS_TABLE_TEXTURES);
		int i = this.guiLeft;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}

	@Nullable
	@Override
	public IGuiEventListener getFocused()
	{
		return this.recipeBook;
	}

	@Override
	public void recipesUpdated()
	{
		this.recipeBook.recipesUpdated();
	}

	@Override
	public void onGuiClosed()
	{
		this.recipeBook.removed();
		super.onGuiClosed();
	}

	@Override
	public GuiRecipeBook func_194310_f()
	{
		return this.recipeBook;
	}
}
