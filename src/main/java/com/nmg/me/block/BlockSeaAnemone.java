package com.nmg.me.block;

import net.minecraft.block.Block;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTropicalFish;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.init.MobEffects;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSeaAnemone extends Block implements ILiquidContainer
{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

	public BlockSeaAnemone()
	{
		super(Properties.create(Material.OCEAN_PLANT).hardnessAndResistance(0).sound(SoundType.WET_GRASS).doesNotBlockMovement().needsRandomTick());
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPE;
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
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public IFluidState getFluidState(IBlockState state)
	{
		return Fluids.WATER.getStillFluidState(false);
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (entityIn instanceof EntityTropicalFish)
		{
			if (((EntityTropicalFish)entityIn).getVariant() == 65536)
				return;
		}

		if (entityIn instanceof EntityLivingBase)
		{
			((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 240, 0));
		}
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
	{
		if (!state.isValidPosition(worldIn, pos))
		{
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.down();
		IBlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();

		if (block == Blocks.MAGMA_BLOCK)
		{
			return false;
		}
		else
		{
			return Block.doesSideFillSquare(blockstate.getCollisionShape(worldIn, blockpos), EnumFacing.UP);
		}
	}

	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
		return fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8 ? this.getDefaultState() : null;
	}

	/*@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (!stateIn.isValidPosition(worldIn, currentPos))
		{
			if (facing == EnumFacing.DOWN)
			{
				return Blocks.AIR.getDefaultState();
			}

			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		if (facing == EnumFacing.UP && facingState.getBlock() == this)
		{
			return MEBlocks.PINK_SEA_ANEMONE.getDefaultState();
		}
		else
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}*/

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, IBlockState state, Fluid fluidIn)
	{
		return false;
	}

	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, IBlockState state, IFluidState fluidStateIn)
	{
		return false;
	}
}
