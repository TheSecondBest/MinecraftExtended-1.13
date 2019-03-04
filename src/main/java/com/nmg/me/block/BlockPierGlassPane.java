package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.List;

public class BlockPierGlassPane extends MEBlockFacingWaterLogged
{

	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierGlassPane()
	{
		super(Properties.create(Material.GLASS).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] PANE_BOTTOM = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 5.5, 16, 1, 10.5));
		final VoxelShape[] PANE_WEST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 5.5, 1, 15, 10.5));
		final VoxelShape[] PANE_EAST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 1, 5.5, 16, 15, 10.5));
		final VoxelShape[] PANE_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 15, 5.5, 16, 16, 10.5));
		final VoxelShape[] PANE_GLASS = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 1, 7, 15, 15, 9));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		for (IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();
			shapes.add(PANE_BOTTOM[facing.getHorizontalIndex()]);
			shapes.add(PANE_WEST[facing.getHorizontalIndex()]);
			shapes.add(PANE_EAST[facing.getHorizontalIndex()]);
			shapes.add(PANE_TOP[facing.getHorizontalIndex()]);
			shapes.add(PANE_GLASS[facing.getHorizontalIndex()]);
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
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPES.get(state);
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		EnumFacing facing = state.get(FACING);
		boolean solid = false;

		switch (facing)
		{
			case NORTH:
			case SOUTH:
				solid = (face == EnumFacing.WEST || face == EnumFacing.EAST);
				break;
			case EAST:
			case WEST:
				solid = (face == EnumFacing.SOUTH || face == EnumFacing.NORTH);
				break;
		}

		return solid ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
}
