package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.List;

public class BlockPierBridge extends MEBlockFacing
{

	private static final BooleanProperty NORTH = BooleanProperty.create("north");
	private static final BooleanProperty EAST = BooleanProperty.create("east");
	private static final BooleanProperty SOUTH = BooleanProperty.create("south");
	private static final BooleanProperty WEST = BooleanProperty.create("west");

	public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierBridge()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState()
				.with(FACING, EnumFacing.NORTH)
				.with(NORTH, Boolean.FALSE)
				.with(EAST, Boolean.FALSE)
				.with(SOUTH, Boolean.FALSE)
				.with(WEST, Boolean.FALSE));

		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));
		final VoxelShape[] POST_RIGHT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] POST_RIGHT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] RAILING_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 16));
		final VoxelShape[] SUPPORT_RIGHT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0.5, 8, 1.5, 1, 9.5, 14.5));
		final VoxelShape[] SUPPORT_RIGHT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0.5, 4, 1.5, 1, 5.5, 14.5));
		final VoxelShape[] SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));

		final VoxelShape[] CORNER_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] CORNER_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] CORNER_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] CORNER_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] CORNER_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));
		final VoxelShape[] CORNER_SUPPORT_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 8, 15, 14.5, 9.5, 15.5));
		final VoxelShape[] CORNER_SUPPORT_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 4, 15, 14.5, 5.5, 15.5));
		final VoxelShape[] CORNER_POST_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] CORNER_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 14.5, 12.75, 16));
		final VoxelShape[] CORNER_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] CORNER_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] CORNER_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] CORNER_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] CORNER_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] CORNER_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));
		final VoxelShape[] CORNER_POST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12.75, 1.5));

		final VoxelShape[] THREE_WAY_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] THREE_WAY_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] THREE_WAY_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 1.5));
		final VoxelShape[] THREE_WAY_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 1.5, 12.75, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] THREE_WAY_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] THREE_WAY_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] THREE_WAY_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] THREE_WAY_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));
		final VoxelShape[] THREE_WAY_POST_0_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] THREE_WAY_POST_1_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] THREE_WAY_RAILING_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] THREE_WAY_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));

		final VoxelShape[] FOUR_WAY_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] FOUR_WAY_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] FOUR_WAY_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 1.5, 12.75, 16));
		final VoxelShape[] FOUR_WAY_POST_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] FOUR_WAY_POST_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 14.5, 16, 12.75, 16));
		final VoxelShape[] FOUR_WAY_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] FOUR_WAY_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] FOUR_WAY_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] FOUR_WAY_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] FOUR_WAY_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] FOUR_WAY_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));


		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
		for(IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);

			List<VoxelShape> shapes = new ArrayList<>();
			EnumBridgeShape bridgeShape = this.getShape(state);

			if (bridgeShape == EnumBridgeShape.STRAIGHT)
			{
				shapes.add(POST_0[facing.getHorizontalIndex()]);
				shapes.add(POST_1[facing.getHorizontalIndex()]);
				shapes.add(RAILING[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(POST_RIGHT_0[facing.getHorizontalIndex()]);
				shapes.add(POST_RIGHT_1[facing.getHorizontalIndex()]);
				shapes.add(RAILING_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT_0[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT_1[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(PLANK_3[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.CORNER)
			{
				shapes.add(CORNER_POST_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_RAILING[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_3[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_3[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.THREE_WAY)
			{
				shapes.add(THREE_WAY_POST_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_3[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_0_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_1_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_1[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.FOUR_WAY)
			{
				shapes.add(FOUR_WAY_POST_0[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_3[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_3[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_3[facing.getHorizontalIndex()]);
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
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPES.get(state);
	}

	/*@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
	{
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BOTTOM);
		IBlockState actualState = this.getActualState(state, worldIn, pos);
		EnumBridgeShape shape = this.getShape(actualState);

		for (AxisAlignedBB collisionBox : this.getCollisionBoxesForShape(shape, actualState))
		{
			addCollisionBoxToList(pos, entityBox, collidingBoxes, collisionBox);
		}
	}

	private List<AxisAlignedBB> getCollisionBoxesForShape(EnumBridgeShape shape, IBlockState state)
	{
		List<AxisAlignedBB> collisionBoxes = new ArrayList<>();

		boolean north = state.getValue(NORTH);
		boolean east = state.getValue(EAST);
		boolean south = state.getValue(SOUTH);
		boolean west = state.getValue(WEST);

		if (shape == EnumBridgeShape.STRAIGHT)
		{
			if (north || south)
			{
				collisionBoxes.add(AABB_NORTH_FENCE_LEFT);
				collisionBoxes.add(AABB_NORTH_FENCE_RIGHT);
			}
			else if (east || west)
			{
				collisionBoxes.add(AABB_EAST_FENCE_LEFT);
				collisionBoxes.add(AABB_EAST_FENCE_RIGHT);
			}
			else
			{
				EnumFacing facing = state.getValue(FACING);

				if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)
				{
					collisionBoxes.add(AABB_NORTH_FENCE_LEFT);
					collisionBoxes.add(AABB_NORTH_FENCE_RIGHT);
				}
				else if (facing == EnumFacing.EAST || facing == EnumFacing.WEST)
				{
					collisionBoxes.add(AABB_EAST_FENCE_LEFT);
					collisionBoxes.add(AABB_EAST_FENCE_RIGHT);
				}
			}
		}
		else if (shape == EnumBridgeShape.CORNER)
		{
			if (south)
			{
				collisionBoxes.add(AABB_EAST_FENCE_LEFT);

				if (east)
					collisionBoxes.add(AABB_NORTH_FENCE_LEFT);
				else if (west)
					collisionBoxes.add(AABB_NORTH_FENCE_RIGHT);
			}
			else if (north)
			{
				collisionBoxes.add(AABB_EAST_FENCE_RIGHT);

				if (east)
					collisionBoxes.add(AABB_NORTH_FENCE_LEFT);
				else if (west)
					collisionBoxes.add(AABB_NORTH_FENCE_RIGHT);
			}
		}
		else if (shape == EnumBridgeShape.THREE_WAY)
		{

			if (north && !east && south && west)
			{
				collisionBoxes.add(AABB_NORTH_FENCE_RIGHT);
			}
			else if (north && east && south && !west)
			{
				collisionBoxes.add(AABB_NORTH_FENCE_LEFT);
			}
			else if (!north && east && south && west)
			{
				collisionBoxes.add(AABB_EAST_FENCE_LEFT);
			}
			else if (north && east && !south && west)
			{
				collisionBoxes.add(AABB_EAST_FENCE_RIGHT);
			}
		}

		return collisionBoxes;
	}*/

	private boolean isFourWay(IBlockState state)
	{
		return state.get(NORTH) && state.get(EAST) && state.get(SOUTH) && state.get(WEST);
	}

	private boolean isThreeWay(IBlockState state)
	{
		boolean north = state.get(NORTH);
		boolean east = state.get(EAST);
		boolean south = state.get(SOUTH);
		boolean west = state.get(WEST);

		if (north)
		{
			return (!east && south && west) || (east && south && !west) || (east && !south && west);
		}
		else if (south)
		{
			return (east && west);
		}

		return false;
	}

	private boolean isCorner(IBlockState state)
	{
		boolean north = state.get(NORTH);
		boolean east = state.get(EAST);
		boolean south = state.get(SOUTH);
		boolean west = state.get(WEST);

		if (north)
		{
			return (!east && !south && west) || (east && !south && !west);
		}
		else if (south)
		{
			return (east && !west) || (!east && west);
		}

		return false;
	}

	private EnumBridgeShape getShape(IBlockState state)
	{
		if (this.isCorner(state))
		{
			return EnumBridgeShape.CORNER;
		}
		else if (this.isThreeWay(state))
		{
			return EnumBridgeShape.THREE_WAY;
		}
		else if (this.isFourWay(state))
		{
			return EnumBridgeShape.FOUR_WAY;
		}

		return EnumBridgeShape.STRAIGHT;
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockReader world, BlockPos pos)
	{
		return state.with(NORTH, this.canConnect(world, pos.north()))
				.with(EAST, this.canConnect(world, pos.east()))
				.with(SOUTH, this.canConnect(world, pos.south()))
				.with(WEST, this.canConnect(world, pos.west()));
	}

	private boolean canConnect(IBlockReader worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() instanceof BlockPierBridge;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(FACING);
		builder.add(NORTH);
		builder.add(EAST);
		builder.add(SOUTH);
		builder.add(WEST);
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

	public enum EnumBridgeShape implements IStringSerializable
	{
		STRAIGHT("straight"),
		CORNER("corner"),
		THREE_WAY("three_way"),
		FOUR_WAY("four_way");

		private final String name;

		EnumBridgeShape(String name)
		{
			this.name = name;
		}

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}

}
