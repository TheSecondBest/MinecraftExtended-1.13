package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.utils.MEUtils;
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
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockStorageShelf extends MEBlockFacingWaterLogged
{

	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockStorageShelf()
	{
		super(Properties.create(Material.WOOD));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] WALL_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 0, 0, 3, 16, 8));
		final VoxelShape[] WALL_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 0, 14, 16, 8));
		final VoxelShape[] TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 15, 0, 13, 16, 8));
		final VoxelShape[] BOTTOM = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 0, 0, 13, 1, 8));
		final VoxelShape[] SHELF = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 7.5, 0, 13, 8.5, 8));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		for(IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();
			shapes.add(WALL_0[facing.getHorizontalIndex()]);
			shapes.add(WALL_1[facing.getHorizontalIndex()]);
			shapes.add(TOP[facing.getHorizontalIndex()]);
			shapes.add(BOTTOM[facing.getHorizontalIndex()]);
			shapes.add(SHELF[facing.getHorizontalIndex()]);
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

	/*
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);

			if (tileEntity instanceof TileEntityStorageShelf)
			{
				TileEntityStorageShelf shelf = (TileEntityStorageShelf) tileEntity;
				ItemStack heldItem = playerIn.getHeldItem(hand);

				if (!playerIn.isSneaking())
				{
					shelf.addItem(heldItem);
					shelf.syncToClient();
				}
				else
				{
					MEUtils.spawnEntityItem(worldIn, pos, shelf.removeItem());
					shelf.syncToClient();
				}
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);

			if (tileEntity instanceof TileEntityBookshelf.Shelf)
			{
				TileEntityBookshelf.Shelf shelf = (TileEntityBookshelf.Shelf) tileEntity;

				for (int i = 0; i < shelf.getSize(); i++)
				{
					MEUtils.spawnEntityItem(worldIn, pos, shelf.getStack(i));
				}

				shelf.clear();
			}
		}

		super.breakBlock(worldIn, pos, state);
	}*/


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

	/*@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityStorageShelf();
	}*/


}
