package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;

import java.util.ArrayList;
import java.util.List;

public class BlockPier extends MEBlockFacingWaterLogged
{

	public static final EnumProperty<PierPart> PART = EnumProperty.create("part", PierPart.class);

	public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPier()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(PART, PierPart.TOP).with(WATERLOGGED, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] BASE_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 4, 1, 16));
		final VoxelShape[] BASE_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(12, 0, 0, 16, 1, 16));
		final VoxelShape[] BASE_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 3));
		final VoxelShape[] BASE_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7.5));
		final VoxelShape[] BASE_4 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13, 16, 2, 16));
		final VoxelShape[] BASE_5 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 8.5, 16, 2, 11.5));
		final VoxelShape[] LEG_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 0, 1, 3, 14, 3));
		final VoxelShape[] LEG_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 1, 15, 14, 3));
		final VoxelShape[] LEG_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 13, 15, 14, 15));
		final VoxelShape[] LEG_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 0, 13, 3, 14, 15));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		for (IBlockState state : states)
		{
			List<VoxelShape> shapes = new ArrayList<>();
			EnumFacing facing = state.get(FACING);
			PierPart part = state.get(PART);

			if (part == PierPart.TOP)
			{
				shapes.add(BASE_0[facing.getHorizontalIndex()]);
				shapes.add(BASE_1[facing.getHorizontalIndex()]);
				shapes.add(BASE_2[facing.getHorizontalIndex()]);
				shapes.add(BASE_3[facing.getHorizontalIndex()]);
				shapes.add(BASE_4[facing.getHorizontalIndex()]);
				shapes.add(BASE_5[facing.getHorizontalIndex()]);
			}
			else if (part == PierPart.BOTTOM)
			{
				shapes.add(LEG_0[facing.getHorizontalIndex()]);
				shapes.add(LEG_1[facing.getHorizontalIndex()]);
				shapes.add(LEG_2[facing.getHorizontalIndex()]);
				shapes.add(LEG_3[facing.getHorizontalIndex()]);
			}

			builder.put(state, VoxelShapeHelper.combineAll(shapes));
		}

		return builder.build();
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPES.get(state);
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		return SHAPES.get(state);
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();

		return super.getStateForPlacement(context).with(PART, world.getBlockState(pos.up()).getBlock() == this ? PierPart.BOTTOM : PierPart.TOP);
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (stateIn.get(WATERLOGGED))
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return facing.getAxis().getPlane() == EnumFacing.Plane.VERTICAL ? stateIn.with(PART, worldIn.getBlockState(currentPos.up()).getBlock() == this ? PierPart.BOTTOM : PierPart.TOP) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(PART);
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

	public enum PierPart implements IStringSerializable
	{
		TOP("top"),
		BOTTOM("bottom")
		;

		private final String name;

		PierPart(String name)
		{
			this.name = name;
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}

}
