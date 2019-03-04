package com.nmg.me.block;

import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockChandelier extends Block
{

	private static final BooleanProperty TOP = BooleanProperty.create("top");
	private final ImmutableMap<Boolean, VoxelShape> SHAPES;

	public BlockChandelier()
	{
		super(Properties.create(Material.WOOD).lightValue(15).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(TOP, false));
		SHAPES = this.generateShapes();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		for (int i = 1; i < 4; i++)
		{
			BlockPos blockPos = pos.up(i);

			if (!worldIn.isAirBlock(blockPos)) // Prevent from replacing actual blocks
				break;

			worldIn.setBlockState(blockPos, MEBlocks.PIER_CHANDELIER.getDefaultState().with(TOP, true), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
		}
	}

	private ImmutableMap<Boolean, VoxelShape> generateShapes()
	{
		ImmutableMap.Builder<Boolean, VoxelShape> builder = new ImmutableMap.Builder<>();
		List<VoxelShape> shapes = new ArrayList<>();

		shapes.add(Block.makeCuboidShape(7, 8, 7, 9, 16, 9));
		shapes.add(Block.makeCuboidShape(6, 5, 7, 7, 8, 9));
		shapes.add(Block.makeCuboidShape(5, 3, 7, 6, 5, 9));
		shapes.add(Block.makeCuboidShape(3, 2, 7, 5, 3, 9));
		shapes.add(Block.makeCuboidShape(2, 3, 7, 3, 4, 9));
		shapes.add(Block.makeCuboidShape(1, 4, 7, 2, 8, 9));
		shapes.add(Block.makeCuboidShape(0, 8, 6, 3, 9, 10));
		shapes.add(Block.makeCuboidShape(7, 5, 6, 9, 8, 7));
		shapes.add(Block.makeCuboidShape(7, 3, 5, 9, 5, 6));
		shapes.add(Block.makeCuboidShape(7, 2, 3, 9, 3, 5));
		shapes.add(Block.makeCuboidShape(7, 3, 2, 9, 4, 3));
		shapes.add(Block.makeCuboidShape(7, 4, 1, 9, 8, 2));
		shapes.add(Block.makeCuboidShape(6, 8, 0, 10, 9, 3));
		shapes.add(Block.makeCuboidShape(9, 5, 7, 10, 8, 9));
		shapes.add(Block.makeCuboidShape(10, 3, 7, 11, 5, 9));
		shapes.add(Block.makeCuboidShape(11, 2, 7, 13, 3, 9));
		shapes.add(Block.makeCuboidShape(13, 3, 7, 14, 4, 9));
		shapes.add(Block.makeCuboidShape(14, 4, 7, 15, 8, 9));
		shapes.add(Block.makeCuboidShape(13, 8, 6, 16, 9, 10));
		shapes.add(Block.makeCuboidShape(7, 5, 9, 9, 8, 10));
		shapes.add(Block.makeCuboidShape(7, 3, 10, 9, 5, 11));
		shapes.add(Block.makeCuboidShape(7, 2, 11, 9, 3, 13));
		shapes.add(Block.makeCuboidShape(7, 3, 13, 9, 4, 14));
		shapes.add(Block.makeCuboidShape(7, 4, 14, 9, 8, 15));
		shapes.add(Block.makeCuboidShape(6, 8, 13, 10, 9, 16));
		shapes.add(Block.makeCuboidShape(7.5, 9, 1, 8.5, 11.5, 2));
		shapes.add(Block.makeCuboidShape(7.85, 11.5, 1.35, 8.1, 12.5, 1.6));
		shapes.add(Block.makeCuboidShape(14, 9, 7.5, 15, 11.5, 8.5));
		shapes.add(Block.makeCuboidShape(14.35, 11.5, 7.9, 14.6, 12.5, 8.15));
		shapes.add(Block.makeCuboidShape(7.5, 9, 14, 8.5, 11.5, 15));
		shapes.add(Block.makeCuboidShape(7.85, 11.5, 14.35, 8.1, 12.5, 14.6));
		shapes.add(Block.makeCuboidShape(1, 9, 7.5, 2, 11.5, 8.5));
		shapes.add(Block.makeCuboidShape(1.35, 11.5, 7.9, 1.6, 12.5, 8.15));

		builder.put(false, VoxelShapeHelper.combineAll(shapes));
		builder.put(true, Block.makeCuboidShape(7, 0, 7, 9, 16, 9)); // Add VoxelShape for top "post"

		return builder.build();
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPES.get(state.get(TOP));
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPES.get(state.get(TOP));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		this.destroyChandelierBlocks(worldIn, pos, false);
		this.destroyChandelierBlocks(worldIn, pos, true);
	}

	@Override
	public int getLightValue(IBlockState state, IWorldReader world, BlockPos pos)
	{
		return state.get(TOP) ? 0 : this.lightValue;
	}

	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (!stateIn.get(TOP))
		{
			this.spawnParticles(worldIn, pos.getX() + 0.1, pos.getY() + 0.85, pos.getZ() + 0.5);
			this.spawnParticles(worldIn, pos.getX() + 0.9, pos.getY() + 0.85, pos.getZ() + 0.5);
			this.spawnParticles(worldIn, pos.getX() + 0.5, pos.getY() + 0.85, pos.getZ() + 0.1);
			this.spawnParticles(worldIn, pos.getX() + 0.5, pos.getY() + 0.85, pos.getZ() + 0.9);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticles(World worldIn, double x, double y, double z)
	{
		worldIn.spawnParticle(Particles.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(Particles.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	private void destroyChandelierBlocks(World worldIn, BlockPos pos, boolean down)
	{
		for (int i = 1; i < 4; i++)
		{
			BlockPos blockPos = down ? pos.down(i) : pos.up(i);
			IBlockState blockState = worldIn.getBlockState(blockPos);

			if (blockState.getBlock() == MEBlocks.PIER_CHANDELIER)
			{
				worldIn.destroyBlock(blockPos, false);
			}
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(TOP);
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
