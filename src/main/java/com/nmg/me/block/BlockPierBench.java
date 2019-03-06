package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockPierBench extends MEBlockFacing
{

	private static final BooleanProperty LEFT = BooleanProperty.create("left");
	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierBench()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(LEFT, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] LEFT_BENCH_LEG_NE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 16, 2));
		final VoxelShape[] LEFT_BENCH_BACKREST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 11, 0, 16, 14, 2));
		final VoxelShape[] LEFT_BENCH_BACKREST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 0, 16, 9, 2));
		final VoxelShape[] LEFT_BENCH_SUPPORT_EAST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 2, 2, 8, 14));
		final VoxelShape[] LEFT_BENCH_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 2, 16, 8, 5));
		final VoxelShape[] LEFT_BENCH_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 7.5, 16, 8, 10.5));
		final VoxelShape[] LEFT_BENCH_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 6, 13, 16, 8, 16));
		final VoxelShape[] LEFT_BENCH_LOWER_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 2, 2, 4, 14));
		final VoxelShape[] LEFT_BENCH_BACK_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 1, 0, 16, 4, 2));
		final VoxelShape[] LEFT_BENCH_FRONT_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 1, 14, 16, 4, 16));
		final VoxelShape[] LEFT_BENCH_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 14, 0, 16, 16, 2));
		final VoxelShape[] LEFT_BENCH_LEG_SE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 14, 2, 8, 16));

		final VoxelShape[] RIGHT_BENCH_LEG_NE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 16, 2));
		final VoxelShape[] RIGHT_BENCH_BACKREST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 11, 0, 14, 14, 2));
		final VoxelShape[] RIGHT_BENCH_BACKREST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 0, 14, 9, 2));
		final VoxelShape[] RIGHT_BENCH_SUPPORT_EAST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 6, 2, 16, 8, 14));
		final VoxelShape[] RIGHT_BENCH_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 2, 14, 8, 5));
		final VoxelShape[] RIGHT_BENCH_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 7.5, 14, 8, 10.5));
		final VoxelShape[] RIGHT_BENCH_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 6, 13, 14, 8, 16));
		final VoxelShape[] RIGHT_BENCH_LOWER_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 2, 2, 16, 4, 14));
		final VoxelShape[] RIGHT_BENCH_BACK_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 14, 4, 2));
		final VoxelShape[] RIGHT_BENCH_FRONT_SUPPORT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 14, 14, 4, 16));
		final VoxelShape[] RIGHT_BENCH_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 14, 0, 14, 16, 2));
		final VoxelShape[] RIGHT_BENCH_LEG_SE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 14, 16, 8, 16));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
		for(IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();

			if (state.get(LEFT))
			{
				shapes.add(LEFT_BENCH_LEG_NE[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_BACKREST_0[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_BACKREST_1[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_SUPPORT_EAST[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_LOWER_SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_BACK_SUPPORT[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_FRONT_SUPPORT[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_TOP[facing.getHorizontalIndex()]);
				shapes.add(LEFT_BENCH_LEG_SE[facing.getHorizontalIndex()]);
			}
			else
			{
				shapes.add(RIGHT_BENCH_LEG_NE[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_BACKREST_0[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_BACKREST_1[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_SUPPORT_EAST[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_LOWER_SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_BACK_SUPPORT[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_FRONT_SUPPORT[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_TOP[facing.getHorizontalIndex()]);
				shapes.add(RIGHT_BENCH_LEG_SE[facing.getHorizontalIndex()]);
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
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		EntityPlayer placer = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
		if (placer != null)
		{
			EnumFacing facing = placer.getHorizontalFacing();

			switch (facing)
			{
				case NORTH:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.west());
				case EAST:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.north());
				case SOUTH:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.east());
				case WEST:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.south());
			}
		}

		return false;
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return MEUtils.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), player, 4.5 * 0.0625D);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if (!state.get(LEFT))
		{
			EnumFacing facing = placer.getHorizontalFacing();
			IBlockState iBlockState = MEBlocks.PIER_BENCH.getDefaultState().with(LEFT, true).with(FACING, facing);

			switch (facing)
			{
				case NORTH:
					worldIn.setBlockState(pos.west(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case EAST:
					worldIn.setBlockState(pos.north(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case SOUTH:
					worldIn.setBlockState(pos.east(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case WEST:
					worldIn.setBlockState(pos.south(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
			}
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!state.get(LEFT))
		{
			switch (state.get(FACING))
			{
				case NORTH:
					this.destroyBlockIfBench(worldIn, pos.west());
					break;
				case EAST:
					this.destroyBlockIfBench(worldIn, pos.north());
					break;
				case SOUTH:
					this.destroyBlockIfBench(worldIn, pos.east());
					break;
				case WEST:
					this.destroyBlockIfBench(worldIn, pos.south());
					break;
			}
		}
		else
		{
			switch (state.get(FACING))
			{
				case NORTH:
					this.destroyBlockIfBench(worldIn, pos.east());
					break;
				case EAST:
					this.destroyBlockIfBench(worldIn, pos.south());
					break;
				case SOUTH:
					this.destroyBlockIfBench(worldIn, pos.west());
					break;
				case WEST:
					this.destroyBlockIfBench(worldIn, pos.north());
					break;
			}
		}
	}

	private void destroyBlockIfBench(World worldIn, BlockPos pos)
	{
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockPierBench)
		{
			worldIn.destroyBlock(pos, false);
		}
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockState state = super.getStateForPlacement(context);
		return state.with(FACING, context.getPlacementHorizontalFacing()).with(LEFT, Boolean.FALSE);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(LEFT);
		builder.add(FACING);
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
