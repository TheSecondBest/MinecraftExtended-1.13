package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockPierBridge extends MEBlockFacingWaterLogged
{

	private static final BooleanProperty NORTH = BooleanProperty.create("north");
	private static final BooleanProperty EAST = BooleanProperty.create("east");
	private static final BooleanProperty SOUTH = BooleanProperty.create("south");
	private static final BooleanProperty WEST = BooleanProperty.create("west");

	public static final EnumProperty<BlockPier.PierPart> PART = EnumProperty.create("part", BlockPier.PierPart.class);

	private final Map<EnumFacing, VoxelShape[]> SHAPES;
	private final VoxelShape LEG_SHAPE;

	public BlockPierBridge()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState()
				.with(FACING, EnumFacing.NORTH)
				.with(NORTH, false)
				.with(EAST, false)
				.with(SOUTH, false)
				.with(WEST, false)
				.with(PART, BlockPier.PierPart.TOP)
				.with(WATERLOGGED, false));

		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
		LEG_SHAPE = this.getLegShape();
	}

	private static int getMask(EnumFacing facing)
	{
		return 1 << facing.getHorizontalIndex();
	}

	protected int getIndex(IBlockState state)
	{
		int i = 0;
		if (state.get(NORTH))
		{
			i |= getMask(EnumFacing.NORTH);
		}

		if (state.get(EAST))
		{
			i |= getMask(EnumFacing.EAST);
		}

		if (state.get(SOUTH))
		{
			i |= getMask(EnumFacing.SOUTH);
		}

		if (state.get(WEST))
		{
			i |= getMask(EnumFacing.WEST);
		}

		return i;
	}

	private VoxelShape getLegShape()
	{
		List<VoxelShape> shapes = new ArrayList<>();
		shapes.add(Block.makeCuboidShape(1, 0, 1, 3, 14, 3));
		shapes.add(Block.makeCuboidShape(13, 0, 1, 15, 14, 3));
		shapes.add(Block.makeCuboidShape(13, 0, 13, 15, 14, 15));
		shapes.add(Block.makeCuboidShape(1, 0, 13, 3, 14, 15));

		return VoxelShapeHelper.combineAll(shapes);
	}

	private Map<EnumFacing, VoxelShape[]> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));
		final VoxelShape[] POST_RIGHT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] POST_RIGHT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] RAILING_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 16));
		final VoxelShape[] SUPPORT_RIGHT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0.5, 8, 1.5, 1, 9.5, 14.5));
		final VoxelShape[] SUPPORT_RIGHT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0.5, 4, 1.5, 1, 5.5, 14.5));
		final VoxelShape[] SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));

		final VoxelShape[] CORNER_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] CORNER_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] CORNER_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] CORNER_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] CORNER_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));
		final VoxelShape[] CORNER_SUPPORT_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 8, 15, 14.5, 9.5, 15.5));
		final VoxelShape[] CORNER_SUPPORT_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 4, 15, 14.5, 5.5, 15.5));
		final VoxelShape[] CORNER_POST_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] CORNER_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 14.5, 12.75, 16));
		final VoxelShape[] CORNER_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] CORNER_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] CORNER_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] CORNER_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] CORNER_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] CORNER_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));
		final VoxelShape[] CORNER_POST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12.75, 1.5));

		final VoxelShape[] THREE_WAY_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] THREE_WAY_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] THREE_WAY_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 1.5));
		final VoxelShape[] THREE_WAY_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 1.5, 12.75, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] THREE_WAY_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] THREE_WAY_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] THREE_WAY_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] THREE_WAY_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));
		final VoxelShape[] THREE_WAY_POST_0_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] THREE_WAY_POST_1_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] THREE_WAY_RAILING_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 16));
		final VoxelShape[] THREE_WAY_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 8, 1.5, 15.5, 9.5, 14.5));
		final VoxelShape[] THREE_WAY_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 4, 1.5, 15.5, 5.5, 14.5));

		final VoxelShape[] FOUR_WAY_POST_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 0, 1.5, 12, 1.5));
		final VoxelShape[] FOUR_WAY_POST_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 2, 14.5, 1.5, 12, 16));
		final VoxelShape[] FOUR_WAY_RAILING = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 0, 1.5, 12.75, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 12, 14.5, 1.5, 12.75, 16));
		final VoxelShape[] FOUR_WAY_POST_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 14.5, 16, 12, 16));
		final VoxelShape[] FOUR_WAY_POST_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 2, 0, 16, 12, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 0, 16, 12.75, 1.5));
		final VoxelShape[] FOUR_WAY_RAILING_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14.5, 12, 14.5, 16, 12.75, 16));
		final VoxelShape[] FOUR_WAY_SUPPORT_RIGHT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 2, 1, 16));
		final VoxelShape[] FOUR_WAY_SUPPORT_LEFT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(14, 0, 0, 16, 1, 16));
		final VoxelShape[] FOUR_WAY_PLANK_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 0, 16, 2, 2.5));
		final VoxelShape[] FOUR_WAY_PLANK_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 4.5, 16, 2, 7));
		final VoxelShape[] FOUR_WAY_PLANK_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 9, 16, 2, 11.5));
		final VoxelShape[] FOUR_WAY_PLANK_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 1, 13.5, 16, 2, 16));

		Map<EnumFacing, VoxelShape[]> result = new HashMap<>();
		result.put(EnumFacing.NORTH, new VoxelShape[states.size()]);
		result.put(EnumFacing.EAST, new VoxelShape[states.size()]);
		result.put(EnumFacing.SOUTH, new VoxelShape[states.size()]);
		result.put(EnumFacing.WEST, new VoxelShape[states.size()]);

		for (IBlockState state : states)
		{
			if (state.get(PART) == BlockPier.PierPart.BOTTOM)
				continue;

			List<VoxelShape> shapes = new ArrayList<>();

			EnumFacing facing = state.get(FACING);
			EnumBridgeShape bridgeShape = this.getShape(state);
			int index = this.getIndex(state);

			if (bridgeShape == EnumBridgeShape.STRAIGHT)
			{
				shapes.add(POST_0[facing.getHorizontalIndex()]);
				shapes.add(POST_1[facing.getHorizontalIndex()]);
				shapes.add(RAILING[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(POST_RIGHT_0[facing.getHorizontalIndex()]);
				shapes.add(POST_RIGHT_1[facing.getHorizontalIndex()]);
				shapes.add(RAILING_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT_0[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT_1[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(PLANK_3[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.CORNER)
			{
				shapes.add(CORNER_POST_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_RAILING[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_3[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(CORNER_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(CORNER_PLANK_3[facing.getHorizontalIndex()]);
				shapes.add(CORNER_POST[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.THREE_WAY)
			{
				shapes.add(THREE_WAY_POST_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_PLANK_3[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_0_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_POST_1_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_RAILING_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_0[facing.getHorizontalIndex()]);
				shapes.add(THREE_WAY_SUPPORT_1[facing.getHorizontalIndex()]);
			}
			else if (bridgeShape == EnumBridgeShape.FOUR_WAY)
			{
				shapes.add(FOUR_WAY_POST_0[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_POST_3[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_RAILING_3[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_SUPPORT_RIGHT[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_SUPPORT_LEFT[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_0[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_1[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_2[facing.getHorizontalIndex()]);
				shapes.add(FOUR_WAY_PLANK_3[facing.getHorizontalIndex()]);
			}

			result.get(facing)[index] = VoxelShapeHelper.combineAll(shapes);
		}

		return result;
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
	{
		EnumBridgeShape shape = this.getShape(state);

		if (state.get(PART) == BlockPier.PierPart.BOTTOM)
		{
			return LEG_SHAPE;
		}

		if (shape == EnumBridgeShape.FOUR_WAY)
		{
			return SHAPES.get(state.get(FACING))[this.getIndex(state)];
		}

		return this.getShapeFromNeighbors(shape, state);
	}

	private VoxelShape getShapeFromNeighbors(EnumBridgeShape shape, IBlockState state)
	{
		boolean north = state.get(NORTH);
		boolean east = state.get(EAST);
		boolean south = state.get(SOUTH);
		boolean west = state.get(WEST);

		if (shape == EnumBridgeShape.STRAIGHT)
		{
			if (north || south)
			{
				return SHAPES.get(EnumFacing.NORTH)[this.getIndex(state)];
			}
			else if (east || west)
			{
				return SHAPES.get(EnumFacing.EAST)[this.getIndex(state)];
			}
			else
			{
				return SHAPES.get(state.get(FACING))[this.getIndex(state)];
			}
		}
		else if (shape == EnumBridgeShape.CORNER)
		{
			if (south)
			{
				if (east)
				{
					return SHAPES.get(EnumFacing.SOUTH)[this.getIndex(state)];
				}
				else if (west)
				{
					return SHAPES.get(EnumFacing.WEST)[this.getIndex(state)];
				}
			}
			else if (north)
			{
				if (east)
				{
					return SHAPES.get(EnumFacing.EAST)[this.getIndex(state)];
				}
				else if (west)
				{
					return SHAPES.get(EnumFacing.NORTH)[this.getIndex(state)];
				}
			}
		}
		else if (shape == EnumBridgeShape.THREE_WAY)
		{
			if (north && !east && south && west)
			{
				return SHAPES.get(EnumFacing.NORTH)[this.getIndex(state)];
			}
			else if (north && east && south && !west)
			{
				return SHAPES.get(EnumFacing.SOUTH)[this.getIndex(state)];
			}
			else if (!north && east && south && west)
			{
				return SHAPES.get(EnumFacing.WEST)[this.getIndex(state)];
			}
			else if (north && east && !south && west)
			{
				return SHAPES.get(EnumFacing.EAST)[this.getIndex(state)];
			}
		}

		return SHAPES.get(state.get(FACING))[this.getIndex(state)];
	}

	private boolean isFourWay(IBlockState state)
	{
		return state.get(NORTH) && state.get(EAST) && state.get(SOUTH) && state.get(WEST);
	}

	private boolean isThreeWay(IBlockState state)
	{
		boolean north = state.get(NORTH);
		boolean east = state.get(EAST);
		boolean south = state.get(SOUTH);
		boolean west = state.get(WEST);

		if (north)
		{
			return (!east && south && west) || (east && south && !west) || (east && !south && west);
		}
		else if (south)
		{
			return (east && west);
		}

		return false;
	}

	private boolean isCorner(IBlockState state)
	{
		boolean north = state.get(NORTH);
		boolean east = state.get(EAST);
		boolean south = state.get(SOUTH);
		boolean west = state.get(WEST);

		if (north)
		{
			return (!east && !south && west) || (east && !south && !west);
		}
		else if (south)
		{
			return (east && !west) || (!east && west);
		}

		return false;
	}

	private EnumBridgeShape getShape(IBlockState state)
	{
		if (this.isCorner(state))
		{
			return EnumBridgeShape.CORNER;
		}
		else if (this.isThreeWay(state))
		{
			return EnumBridgeShape.THREE_WAY;
		}
		else if (this.isFourWay(state))
		{
			return EnumBridgeShape.FOUR_WAY;
		}

		return EnumBridgeShape.STRAIGHT;
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockReader world, BlockPos pos)
	{
		return state.with(FACING, state.get(FACING))
				.with(NORTH, this.canConnect(world, pos.north()))
				.with(EAST, this.canConnect(world, pos.east()))
				.with(SOUTH, this.canConnect(world, pos.south()))
				.with(WEST, this.canConnect(world, pos.west()));
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();

		return super.getStateForPlacement(context)
				.with(NORTH, this.canConnect(world, pos.north()))
				.with(EAST, this.canConnect(world, pos.east()))
				.with(SOUTH, this.canConnect(world, pos.south()))
				.with(WEST, this.canConnect(world, pos.west()))
				.with(PART, world.getBlockState(pos.up()).getBlock() == this ? BlockPier.PierPart.BOTTOM : BlockPier.PierPart.TOP);
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (stateIn.get(WATERLOGGED))
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return facing.getAxis().getPlane() == EnumFacing.Plane.HORIZONTAL ? stateIn.with(NORTH, this.canConnect(worldIn, currentPos.north()))
				.with(EAST, this.canConnect(worldIn, currentPos.east()))
				.with(SOUTH, this.canConnect(worldIn, currentPos.south()))
				.with(WEST, this.canConnect(worldIn, currentPos.west())) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos).with(PART, worldIn.getBlockState(currentPos.up()).getBlock() == this ? BlockPier.PierPart.BOTTOM : BlockPier.PierPart.TOP);
	}

	private boolean canConnect(IBlockReader worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		return state.getBlock() instanceof BlockPierBridge && state.get(PART) == BlockPier.PierPart.TOP;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(NORTH);
		builder.add(EAST);
		builder.add(SOUTH);
		builder.add(WEST);
		builder.add(PART);
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

	public enum EnumBridgeShape implements IStringSerializable
	{
		STRAIGHT("straight"),
		CORNER("corner"),
		THREE_WAY("three_way"),
		FOUR_WAY("four_way");

		private final String name;

		EnumBridgeShape(String name)
		{
			this.name = name;
		}

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}

}
