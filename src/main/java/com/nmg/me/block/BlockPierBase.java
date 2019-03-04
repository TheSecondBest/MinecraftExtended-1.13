package com.nmg.me.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPierBase extends Block
{

	private static final BooleanProperty SOLID = BooleanProperty.create("solid");

	public BlockPierBase()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(SOLID, false));
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return state.get(SOLID);
	}

	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockState state = super.getStateForPlacement(context);
		return state.with(SOLID, context.getPlayer().isSneaking());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(SOLID);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation(this.getTranslationKey() + ".desc").applyTextStyle(TextFormatting.YELLOW));
	}
}
