package com.nmg.me.block;

import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Particles;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCandle extends Block
{

	public final VoxelShape SHAPE;

	public BlockCandle()
	{
		super(Properties.create(Material.WOOD).lightValue(7));
		SHAPE = this.generateShape();
	}

	private VoxelShape generateShape()
	{
		List<VoxelShape> shapes = new ArrayList<>();

		shapes.add(Block.makeCuboidShape(6.2, 0, 6.2, 9.8, 0.5, 9.8));
		shapes.add(Block.makeCuboidShape(7, 0.5, 7, 9, 1, 9));
		shapes.add(Block.makeCuboidShape(7.6, 1, 7.55, 8.35, 6, 8.3));
		shapes.add(Block.makeCuboidShape(7.85, 6, 7.8, 8.1, 6.5, 8.05));

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
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		worldIn.spawnParticle(Particles.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(Particles.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

}
