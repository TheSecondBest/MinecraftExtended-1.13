package com.nmg.me.block;

import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockPierBench extends MEBlockFacing
{

	private static final BooleanProperty LEFT = BooleanProperty.create("left");

	public BlockPierBench()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(LEFT, false));
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		EntityPlayer placer = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5, false);
		if (placer != null)
		{
			EnumFacing facing = placer.getHorizontalFacing();

			switch (facing)
			{
				case NORTH:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.west());
				case EAST:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.north());
				case SOUTH:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.east());
				case WEST:
					return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.south());
			}
		}

		return false;
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return MEUtils.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), player, 4.5 * 0.0625D);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if (!state.get(LEFT))
		{
			EnumFacing facing = placer.getHorizontalFacing();
			IBlockState iBlockState = MEBlocks.PIER_BENCH.getDefaultState().with(LEFT, true).with(FACING, facing);

			switch (facing)
			{
				case NORTH:
					worldIn.setBlockState(pos.west(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case EAST:
					worldIn.setBlockState(pos.north(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case SOUTH:
					worldIn.setBlockState(pos.east(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
				case WEST:
					worldIn.setBlockState(pos.south(), iBlockState, Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
					break;
			}
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!state.get(LEFT))
		{
			switch (state.get(FACING))
			{
				case NORTH:
					this.destroyBlockIfBench(worldIn, pos.west());
					break;
				case EAST:
					this.destroyBlockIfBench(worldIn, pos.north());
					break;
				case SOUTH:
					this.destroyBlockIfBench(worldIn, pos.east());
					break;
				case WEST:
					this.destroyBlockIfBench(worldIn, pos.south());
					break;
			}
		}
		else
		{
			switch (state.get(FACING))
			{
				case NORTH:
					this.destroyBlockIfBench(worldIn, pos.east());
					break;
				case EAST:
					this.destroyBlockIfBench(worldIn, pos.south());
					break;
				case SOUTH:
					this.destroyBlockIfBench(worldIn, pos.west());
					break;
				case WEST:
					this.destroyBlockIfBench(worldIn, pos.north());
					break;
			}
		}
	}

	private void destroyBlockIfBench(World worldIn, BlockPos pos)
	{
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockPierBench)
		{
			worldIn.destroyBlock(pos, false);
		}
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockState state = super.getStateForPlacement(context);
		return state.with(FACING, context.getPlacementHorizontalFacing()).with(LEFT, Boolean.FALSE);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(LEFT);
		builder.add(FACING);
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
