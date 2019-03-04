package com.nmg.me.block;

import com.nmg.me.utils.MEUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockPierStair extends MEBlockFacing
{

	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");

	public static final AxisAlignedBB AABB_LOWER = MEUtils.calculateAABB(0, 0, 0, 16, 8, 16);

	public static final AxisAlignedBB AABB_UPPER_NORTH = MEUtils.calculateAABB(0, 9, 0, 16, 7, 8);
	public static final AxisAlignedBB AABB_UPPER_SOUTH = MEUtils.calculateAABB(0, 9, 8, 16, 7, 8);
	public static final AxisAlignedBB AABB_UPPER_EAST = MEUtils.calculateAABB(8, 9, 0, 8, 7, 16);
	public static final AxisAlignedBB AABB_UPPER_WEST = MEUtils.calculateAABB(0, 9, 0, 8, 7, 16);

	public BlockPierStair()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false));
	}

	/*@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
	{
		switch (state.getValue(FACING))
		{
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_UPPER_NORTH);
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_UPPER_SOUTH);
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_UPPER_WEST);
				break;
			case EAST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_UPPER_EAST);
				break;
		}
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LOWER);
	}*/

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockReader world, BlockPos pos)
	{
		boolean north = false;
		boolean west = false;
		boolean south = false;
		boolean east = false;

		if (this.isStair(world, pos.west()))
		{
			west = true;
		}

		if (this.isStair(world, pos.east()))
		{
			east = true;
		}

		if (this.isStair(world, pos.north()))
		{
			north = true;
		}

		if (this.isStair(world, pos.south()))
		{
			south = true;
		}

		return state.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
	}

	private boolean isStair(IBlockReader world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() instanceof BlockPierStair;
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

}
