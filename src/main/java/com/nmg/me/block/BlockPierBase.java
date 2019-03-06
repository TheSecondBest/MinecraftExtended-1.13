package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockPierBase extends MEBlockWaterLogged
{

	private static final BooleanProperty SOLID = BooleanProperty.create("solid");
	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierBase()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(SOLID, false).with(WATERLOGGED, false));
		SHAPES = this.generateShape(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShape(ImmutableList<IBlockState> states)
	{
		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		for (IBlockState state : states)
		{
			List<VoxelShape> shapes = new ArrayList<>();

			if (!state.get(SOLID))
			{
				shapes.add(Block.makeCuboidShape(0, 0, 0, 1, 16, 1));
				shapes.add(Block.makeCuboidShape(15, 0, 0, 16, 16, 1));
				shapes.add(Block.makeCuboidShape(15, 0, 15, 16, 16, 16));
				shapes.add(Block.makeCuboidShape(0, 0, 15, 1, 16, 16));
				shapes.add(Block.makeCuboidShape(1, 0, 0, 15, 1, 1));
				shapes.add(Block.makeCuboidShape(1, 3, 0, 15, 5, 1));
				shapes.add(Block.makeCuboidShape(1, 11, 0, 15, 13, 1));
				shapes.add(Block.makeCuboidShape(1, 15, 0, 15, 16, 1));
				shapes.add(Block.makeCuboidShape(1, 7, 0, 15, 9, 1));
				shapes.add(Block.makeCuboidShape(1, 15, 15, 15, 16, 16));
				shapes.add(Block.makeCuboidShape(1, 0, 15, 15, 1, 16));
				shapes.add(Block.makeCuboidShape(1, 3, 15, 15, 5, 16));
				shapes.add(Block.makeCuboidShape(1, 7, 15, 15, 9, 16));
				shapes.add(Block.makeCuboidShape(1, 11, 15, 15, 13, 16));
				shapes.add(Block.makeCuboidShape(0, 0, 1, 1, 1, 15));
				shapes.add(Block.makeCuboidShape(0, 15, 1, 1, 16, 15));
				shapes.add(Block.makeCuboidShape(15, 15, 1, 16, 16, 15));
				shapes.add(Block.makeCuboidShape(15, 0, 1, 16, 1, 15));
				shapes.add(Block.makeCuboidShape(0, 3, 1, 1, 5, 15));
				shapes.add(Block.makeCuboidShape(0, 7, 1, 1, 9, 15));
				shapes.add(Block.makeCuboidShape(0, 11, 1, 1, 13, 15));
				shapes.add(Block.makeCuboidShape(15, 11, 1, 16, 13, 15));
				shapes.add(Block.makeCuboidShape(15, 7, 1, 16, 9, 15));
				shapes.add(Block.makeCuboidShape(15, 3, 1, 16, 5, 15));
				shapes.add(Block.makeCuboidShape(2, 15, 1, 5, 16, 15));
				shapes.add(Block.makeCuboidShape(6.5, 15, 1, 9.5, 16, 15));
				shapes.add(Block.makeCuboidShape(11, 15, 1, 14, 16, 15));
				shapes.add(Block.makeCuboidShape(2, 0, 1, 5, 1, 15));
				shapes.add(Block.makeCuboidShape(6.5, 0, 1, 9.5, 1, 15));
				shapes.add(Block.makeCuboidShape(11, 0, 1, 14, 1, 15));
				shapes.add(Block.makeCuboidShape(6.5, 7, 1, 9.5, 9, 15));
				shapes.add(Block.makeCuboidShape(11, 7, 1, 14, 9, 15));
				shapes.add(Block.makeCuboidShape(2, 7, 1, 5, 9, 15));
			}
			else
			{
				shapes.add(Block.makeCuboidShape(0, 0, 0, 16, 1, 1));
				shapes.add(Block.makeCuboidShape(0, 0, 15, 16, 1, 16));
				shapes.add(Block.makeCuboidShape(0, 0, 1, 1, 1, 15));
				shapes.add(Block.makeCuboidShape(15, 0, 1, 16, 1, 15));
				shapes.add(Block.makeCuboidShape(0, 1, 0, 1, 15, 1));
				shapes.add(Block.makeCuboidShape(0, 1, 15, 1, 15, 16));
				shapes.add(Block.makeCuboidShape(15, 1, 15, 16, 15, 16));
				shapes.add(Block.makeCuboidShape(15, 1, 0, 16, 15, 1));
				shapes.add(Block.makeCuboidShape(0, 15, 0, 16, 16, 1));
				shapes.add(Block.makeCuboidShape(0, 15, 15, 16, 16, 16));
				shapes.add(Block.makeCuboidShape(0, 15, 1, 1, 16, 15));
				shapes.add(Block.makeCuboidShape(15, 15, 1, 16, 16, 15));
				shapes.add(Block.makeCuboidShape(1, 0, 1, 15, 16, 15));
				shapes.add(Block.makeCuboidShape(1, 11, 0, 15, 13, 1));
				shapes.add(Block.makeCuboidShape(1, 3, 0, 15, 5, 1));
				shapes.add(Block.makeCuboidShape(0, 3, 1, 1, 5, 15));
				shapes.add(Block.makeCuboidShape(0, 11, 1, 1, 13, 15));
				shapes.add(Block.makeCuboidShape(1, 3, 15, 15, 5, 16));
				shapes.add(Block.makeCuboidShape(1, 11, 15, 15, 13, 16));
				shapes.add(Block.makeCuboidShape(15, 3, 1, 16, 5, 15));
				shapes.add(Block.makeCuboidShape(15, 11, 1, 16, 13, 15));
				shapes.add(Block.makeCuboidShape(1, 7, 15, 15, 9, 16));
				shapes.add(Block.makeCuboidShape(0, 7, 1, 1, 9, 15));
				shapes.add(Block.makeCuboidShape(1, 7, 0, 15, 9, 1));
				shapes.add(Block.makeCuboidShape(15, 7, 1, 16, 9, 15));
			}

			builder.put(state, VoxelShapeHelper.combineAll(shapes));
		}

		return builder.build();
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPES.get(state);
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
		super.fillStateContainer(builder);
		builder.add(SOLID);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation(this.getTranslationKey() + ".desc").applyTextStyle(TextFormatting.YELLOW));
	}
}
