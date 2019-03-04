package com.nmg.me.block;

import com.nmg.me.utils.MEUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockPierBridge extends MEBlockFacing
{

	private static final BooleanProperty NORTH = BooleanProperty.create("north");
	private static final BooleanProperty EAST = BooleanProperty.create("east");
	private static final BooleanProperty SOUTH = BooleanProperty.create("south");
	private static final BooleanProperty WEST = BooleanProperty.create("west");

	private static final AxisAlignedBB BOUNDING_BOX = MEUtils.calculateAABB(0, 0, 0, 16, 12.75, 16);
	private static final AxisAlignedBB AABB_BOTTOM = MEUtils.calculateAABB(0, 0, 0, 16, 2, 16);
	private static final AxisAlignedBB AABB_NORTH_FENCE_LEFT = MEUtils.calculateAABB(0, 2, 0, 1, 10.75, 16);
	private static final AxisAlignedBB AABB_NORTH_FENCE_RIGHT = MEUtils.calculateAABB(15, 2, 0, 1, 10.75, 16);
	private static final AxisAlignedBB AABB_EAST_FENCE_LEFT = MEUtils.calculateAABB(0, 2, 0, 16, 10.75, 1);
	private static final AxisAlignedBB AABB_EAST_FENCE_RIGHT = MEUtils.calculateAABB(0, 2, 15, 16, 10.75, 1);

	public BlockPierBridge()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState()
				.with(FACING, EnumFacing.NORTH)
				.with(NORTH, Boolean.FALSE)
				.with(EAST, Boolean.FALSE)
				.with(SOUTH, Boolean.FALSE)
				.with(WEST, Boolean.FALSE));
	}

	/*@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return BOUNDING_BOX;
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
