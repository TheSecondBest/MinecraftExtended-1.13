package com.nmg.me.block;

import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;

import java.util.ArrayList;
import java.util.List;

public class BlockPier extends MEBlockFacing
{

	private final VoxelShape SHAPE;

	public BlockPier()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		SHAPE = this.generateShape();
	}

	private VoxelShape generateShape()
	{
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(Block.makeCuboidShape(0, 0, 0, 4, 1, 16)); // PIER_SUPPORT_RIGHT
		shapes.add(Block.makeCuboidShape(12, 0, 0, 16, 1, 16)); // PIER_SUPPORT_LEFT
		shapes.add(Block.makeCuboidShape(0, 1, 0, 16, 2, 3)); // PIER_PLANK_0
		shapes.add(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7.5)); // PIER_PLANK_1
		shapes.add(Block.makeCuboidShape(0, 1, 13, 16, 2, 16)); // PIER_PLANK_3
		shapes.add(Block.makeCuboidShape(0, 1, 8.5, 16, 2, 11.5)); // PIER_PLANK_2

		return VoxelShapeHelper.combineAll(shapes);
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPE;
	}


	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IWorldReaderBase world, BlockPos pos)
	{
		return false;
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
