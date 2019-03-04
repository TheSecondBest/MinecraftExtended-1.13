package com.nmg.me.block;

import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockPierDoor extends BlockDoor
{

	private final VoxelShape NS_SHAPE;
	private final VoxelShape EW_SHAPE;

	public BlockPierDoor()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(OPEN, Boolean.FALSE).with(POWERED, Boolean.FALSE).with(HALF, DoubleBlockHalf.LOWER));
		NS_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 5.5D, 16.0D, 16.0D, 10.5D);
		EW_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(NS_SHAPE)[EnumFacing.EAST.getHorizontalIndex()];
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		EnumFacing facing = state.get(FACING);
		boolean isOpen = !state.get(OPEN);

		switch (facing)
		{
			case NORTH:
			case SOUTH:
				return isOpen ? NS_SHAPE : EW_SHAPE;
			case EAST:
			case WEST:
			default:
				return isOpen ? EW_SHAPE : NS_SHAPE;
		}
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	{
		return state.get(HALF) == DoubleBlockHalf.UPPER ? Items.AIR : super.getItemDropped(state, worldIn, pos, fortune);
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
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
