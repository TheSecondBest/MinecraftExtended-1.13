package com.nmg.me.block;

import com.nmg.me.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class MEBlockFacingWaterLogged extends MEBlockFacing implements IBucketPickupHandler, ILiquidContainer
{

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public MEBlockFacingWaterLogged(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(WATERLOGGED);
	}

	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluidState = world.getFluidState(pos);

		return super.getStateForPlacement(context).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	public IFluidState getFluidState(IBlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, IBlockState state)
	{
		if (state.get(WATERLOGGED))
		{
			worldIn.setBlockState(pos, state.with(WATERLOGGED, false), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
			return Fluids.WATER;
		}

		return Fluids.EMPTY;
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, IBlockState state, Fluid fluidIn)
	{
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, IBlockState state, IFluidState fluidStateIn)
	{
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER)
		{
			if (!worldIn.isRemote())
			{
				worldIn.setBlockState(pos, state.with(WATERLOGGED, true), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}

			return true;
		}

		return false;
	}
}
