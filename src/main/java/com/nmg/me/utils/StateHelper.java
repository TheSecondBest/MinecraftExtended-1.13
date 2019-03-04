package com.nmg.me.utils;

import com.nmg.me.block.MEBlockFacing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class StateHelper
{
	public static Block getBlock(IBlockReader world, BlockPos pos, EnumFacing facing, Direction dir)
	{
		BlockPos target = getBlockPosRelativeTo(world, pos, facing, dir);
		return world.getBlockState(target).getBlock();
	}

	public static Direction getRotation(IBlockReader world, BlockPos pos, EnumFacing facing, Direction dir)
	{
		BlockPos target = getBlockPosRelativeTo(world, pos, facing, dir);
		EnumFacing other = world.getBlockState(target).get(MEBlockFacing.FACING);
		Direction dir_2 = getDirectionRelativeTo(facing, other);
		return dir_2;
	}

	public static boolean isAirBlock(IBlockReader world, BlockPos pos, EnumFacing facing, Direction dir)
	{
		BlockPos target = getBlockPosRelativeTo(world, pos, facing, dir);
		return world.getBlockState(target).getBlock() instanceof BlockAir;
	}

	private static BlockPos getBlockPosRelativeTo(IBlockReader world, BlockPos pos, EnumFacing facing, Direction dir)
	{
		switch(dir)
		{
			case LEFT:
				return pos.offset(facing.rotateY());
			case RIGHT:
				return pos.offset(facing.rotateYCCW());
			case UP:
				return pos.offset(facing);
			case DOWN:
				return pos.offset(facing.getOpposite());
			default:
				return pos;
		}
	}

	private static Direction getDirectionRelativeTo(EnumFacing thisBlock, EnumFacing otherBlock)
	{
		int num = thisBlock.getHorizontalIndex() - otherBlock.getHorizontalIndex();
		switch(num)
		{
			case -3:
				return Direction.LEFT;
			case -2:
				return Direction.UP;
			case -1:
				return Direction.RIGHT;
			case 0:
				return Direction.DOWN;
			case 1:
				return Direction.LEFT;
			case 2:
				return Direction.UP;
			case 3:
				return Direction.RIGHT;
		}
		return Direction.NONE;
	}

	public static enum Direction
	{
		UP,
		DOWN,
		LEFT,
		RIGHT,
		NONE;
	}

}
