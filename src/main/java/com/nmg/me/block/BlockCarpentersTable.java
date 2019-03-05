package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.client.gui.interactionobjects.InteractionObjectCarpentersTable;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.List;

public class BlockCarpentersTable extends MEBlockFacingWaterLogged
{

	public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockCarpentersTable()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] LEG_SUPPORT_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 0, 2, 3, 1, 14));
		final VoxelShape[] LEG_SUPPORT_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 2, 15, 1, 14));
		final VoxelShape[] LEG_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 1, 11, 2.5, 13, 12));
		final VoxelShape[] LEG_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.5, 1, 4, 2.5, 13, 5));
		final VoxelShape[] LEG_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13.5, 1, 4, 14.5, 13, 5));
		final VoxelShape[] LEG_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13.5, 1, 11, 14.5, 13, 12));
		final VoxelShape[] LEG_SUPPORT_2 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2.5, 5, 4, 13.5, 6.5, 5));
		final VoxelShape[] LEG_SUPPORT_3 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2.5, 5, 11, 13.5, 6.5, 12));
		final VoxelShape[] ELEMENT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 13, 1, 16, 14, 15));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		for (IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();
			shapes.add(LEG_SUPPORT_0[facing.getHorizontalIndex()]);
			shapes.add(LEG_SUPPORT_1[facing.getHorizontalIndex()]);
			shapes.add(LEG_0[facing.getHorizontalIndex()]);
			shapes.add(LEG_1[facing.getHorizontalIndex()]);
			shapes.add(LEG_2[facing.getHorizontalIndex()]);
			shapes.add(LEG_3[facing.getHorizontalIndex()]);
			shapes.add(LEG_SUPPORT_2[facing.getHorizontalIndex()]);
			shapes.add(LEG_SUPPORT_3[facing.getHorizontalIndex()]);
			shapes.add(ELEMENT[facing.getHorizontalIndex()]);
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
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			NetworkHooks.openGui((EntityPlayerMP) player, new InteractionObjectCarpentersTable(worldIn, pos), pos);
		}

		return true;
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
