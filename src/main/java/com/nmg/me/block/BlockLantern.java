package com.nmg.me.block;

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
import net.minecraft.init.Particles;
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
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockLantern extends MEBlockWaterLogged
{

	private final VoxelShape SHAPE;

	public BlockLantern()
	{
		super(Properties.create(Material.ANVIL).lightValue(9));
		SHAPE = this.generateShape();
	}

	private VoxelShape generateShape()
	{
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(Block.makeCuboidShape(4, 0, 4, 12, 1, 12));
		shapes.add(Block.makeCuboidShape(7.2, 1, 7.2, 8.7, 6, 8.7));
		shapes.add(Block.makeCuboidShape(7.7, 6, 7.7, 8.2, 7, 8.2));
		shapes.add(Block.makeCuboidShape(4, 1, 4, 6, 10, 5));
		shapes.add(Block.makeCuboidShape(10, 1, 4, 12, 10, 5));
		shapes.add(Block.makeCuboidShape(10, 1, 11, 12, 10, 12));
		shapes.add(Block.makeCuboidShape(4, 1, 11, 6, 10, 12));
		shapes.add(Block.makeCuboidShape(4, 1, 5, 5, 10, 6));
		shapes.add(Block.makeCuboidShape(11, 1, 5, 12, 10, 6));
		shapes.add(Block.makeCuboidShape(11, 1, 10, 12, 10, 11));
		shapes.add(Block.makeCuboidShape(4, 1, 10, 5, 10, 11));
		shapes.add(Block.makeCuboidShape(6, 9, 4, 10, 10, 5));
		shapes.add(Block.makeCuboidShape(6, 9, 11, 10, 10, 12));
		shapes.add(Block.makeCuboidShape(11, 9, 6, 12, 10, 10));
		shapes.add(Block.makeCuboidShape(4, 9, 6, 5, 10, 10));
		shapes.add(Block.makeCuboidShape(5, 1, 5, 11, 9, 6));
		shapes.add(Block.makeCuboidShape(5, 1, 10, 11, 9, 11));
		shapes.add(Block.makeCuboidShape(5, 1, 5, 6, 9, 11));
		shapes.add(Block.makeCuboidShape(10, 1, 5, 11, 9, 11));
		shapes.add(Block.makeCuboidShape(5, 10, 5, 6, 11, 11));
		shapes.add(Block.makeCuboidShape(10, 10, 5, 11, 11, 11));
		shapes.add(Block.makeCuboidShape(6, 10, 5, 10, 11, 6));
		shapes.add(Block.makeCuboidShape(6, 10, 10, 10, 11, 11));
		shapes.add(Block.makeCuboidShape(9, 11, 6, 10, 12, 10));
		shapes.add(Block.makeCuboidShape(6, 11, 6, 7, 12, 10));
		shapes.add(Block.makeCuboidShape(7, 11, 6, 9, 12, 7));
		shapes.add(Block.makeCuboidShape(7, 11, 9, 9, 12, 10));
		shapes.add(Block.makeCuboidShape(7, 12, 7, 9, 13, 9));

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
		if (!stateIn.get(WATERLOGGED))
		{
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + 0.5D;
			double d2 = (double)pos.getZ() + 1.0D;

			worldIn.spawnParticle(Particles.SMOKE, d0, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(Particles.FLAME, d0, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
		}
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

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

}
