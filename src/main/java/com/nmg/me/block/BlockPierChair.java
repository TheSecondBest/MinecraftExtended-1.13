package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockPierChair extends MEBlockFacing
{
	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierChair()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] BENCH_LEG_NE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 16, 2));
		final VoxelShape[] BENCH_BACKREST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 11, 0, 14, 14, 2));
		final VoxelShape[] BENCH_BACKREST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 0, 14, 9, 2));
		final VoxelShape[] BENCH_SUPPORT_EAST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 6, 2, 16, 8, 14));
		final VoxelShape[] BENCH_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 2, 14, 8, 5));
		final VoxelShape[] BENCH_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 7.5, 14, 8, 10.5));
		final VoxelShape[] BENCH_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 13, 14, 8, 16));
		final VoxelShape[] BENCH_LOWER_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 2, 2, 16, 4, 14));
		final VoxelShape[] BENCH_BACK_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 1, 0, 14, 4, 2));
		final VoxelShape[] BENCH_FRONT_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 1, 14, 14, 4, 16));
		final VoxelShape[] BENCH_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 14, 0, 14, 16, 2));
		final VoxelShape[] BENCH_LEG_SE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 14, 16, 8, 16));
		final VoxelShape[] BENCH_SUPPORT_WEST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 2, 2, 8, 14));
		final VoxelShape[] BENCH_LEG_SW = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 14, 2, 8, 16));
		final VoxelShape[] BENCH_LOWER_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 2, 2, 4, 14));
		final VoxelShape[] BENCH_LEG_NW = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 16, 2));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
		for(IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();
			shapes.add(BENCH_LEG_NE[facing.getHorizontalIndex()]);
			shapes.add(BENCH_BACKREST_0[facing.getHorizontalIndex()]);
			shapes.add(BENCH_BACKREST_1[facing.getHorizontalIndex()]);
			shapes.add(BENCH_SUPPORT_EAST[facing.getHorizontalIndex()]);
			shapes.add(BENCH_PLANK_0[facing.getHorizontalIndex()]);
			shapes.add(BENCH_PLANK_1[facing.getHorizontalIndex()]);
			shapes.add(BENCH_PLANK_2[facing.getHorizontalIndex()]);
			shapes.add(BENCH_LOWER_SUPPORT_1[facing.getHorizontalIndex()]);
			shapes.add(BENCH_BACK_SUPPORT[facing.getHorizontalIndex()]);
			shapes.add(BENCH_FRONT_SUPPORT[facing.getHorizontalIndex()]);
			shapes.add(BENCH_TOP[facing.getHorizontalIndex()]);
			shapes.add(BENCH_LEG_SE[facing.getHorizontalIndex()]);
			shapes.add(BENCH_SUPPORT_WEST[facing.getHorizontalIndex()]);
			shapes.add(BENCH_LEG_SW[facing.getHorizontalIndex()]);
			shapes.add(BENCH_LOWER_SUPPORT_0[facing.getHorizontalIndex()]);
			shapes.add(BENCH_LEG_NW[facing.getHorizontalIndex()]);
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
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return MEUtils.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), player, 4.5 * 0.0625D);
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
}
