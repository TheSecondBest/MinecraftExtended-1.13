package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.client.gui.interactionobjects.InteractionObjectBookshelf;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.tileentity.TileEntityBookshelf;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockPierBookshelf extends MEBlockFacing
{

	private static final BooleanProperty TOP = BooleanProperty.create("top");

	private final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockPierBookshelf()
	{
		super(Properties.create(Material.WOOD));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(TOP, Boolean.FALSE));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

		final VoxelShape[] CABINET_CUBE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 16, 16, 13));
		final VoxelShape[] CABINET_DOOR = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 1, 13, 15, 15, 14));
		final VoxelShape[] CABINET_HANDLE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 8, 14, 14, 12, 15));

		final VoxelShape[] SHELF_WALL_BACK = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 0, 16, 16, 1));
		final VoxelShape[] SHELF_WALL_SIDE_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(0, 0, 1, 1, 16, 7));
		final VoxelShape[] SHELF_WALL_SIDE_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(15, 0, 1, 16, 16, 7));
		final VoxelShape[] SHELF_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 15, 1, 15, 16, 7));
		final VoxelShape[] SHELF_SHELF_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 3.5, 1, 15, 4.5, 7));
		final VoxelShape[] SHELF_SHELF_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1, 9.5, 1, 15, 10.5, 7));

		for (IBlockState state : states)
		{
			List<VoxelShape> shapes = new ArrayList<>();
			EnumFacing facing = state.get(FACING);

			if (state.get(TOP))
			{
				shapes.add(SHELF_WALL_BACK[facing.getHorizontalIndex()]);
				shapes.add(SHELF_WALL_SIDE_0[facing.getHorizontalIndex()]);
				shapes.add(SHELF_WALL_SIDE_1[facing.getHorizontalIndex()]);
				shapes.add(SHELF_TOP[facing.getHorizontalIndex()]);
				shapes.add(SHELF_SHELF_0[facing.getHorizontalIndex()]);
				shapes.add(SHELF_SHELF_1[facing.getHorizontalIndex()]);
			}
			else
			{
				shapes.add(CABINET_CUBE[facing.getHorizontalIndex()]);
				shapes.add(CABINET_DOOR[facing.getHorizontalIndex()]);
				shapes.add(CABINET_HANDLE[facing.getHorizontalIndex()]);
			}

			builder.put(state, VoxelShapeHelper.combineAll(shapes));
		}

		return builder.build();
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPES.get(state);
	}

	@Override
	public float getEnchantPowerBonus(IBlockState state, IWorldReader world, BlockPos pos)
	{
		return 1;
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		return super.isValidPosition(state, worldIn, pos) && worldIn.isAirBlock(pos.up());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos.up(), MEBlocks.PIER_BOOKSHELF.getDefaultState().with(FACING, state.get(FACING)).with(TOP, Boolean.TRUE), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote && player instanceof EntityPlayerMP)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);

			if (!state.get(TOP) && !player.isSneaking())
			{
				if (tileEntity instanceof TileEntityBookshelf.Cabinet)
				{
					NetworkHooks.openGui((EntityPlayerMP)player, new InteractionObjectBookshelf((TileEntityBookshelf.Cabinet)tileEntity), pos);
					worldIn.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
			}
			else if (state.get(TOP))
			{

				if (tileEntity instanceof TileEntityBookshelf.Shelf)
				{
					TileEntityBookshelf.Shelf shelf = (TileEntityBookshelf.Shelf) tileEntity;
					ItemStack heldItem = player.getHeldItem(hand);

					if (this.isBook(heldItem) && !player.isSneaking())
					{
						shelf.addItem(heldItem);
						shelf.syncToClient();
					}
					else if (player.isSneaking())
					{
						MEUtils.spawnEntityItem(worldIn, pos, shelf.removeItem());
						shelf.syncToClient();
					}
				}
			}
		}

		return true;
	}

	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving)
	{
		if (!worldIn.isRemote && state.getBlock() != newState.getBlock())
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

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (state.get(TOP) && worldIn.getBlockState(pos.down()).getBlock() == this)
		{
			worldIn.destroyBlock(pos.down(), false);
		}
		else if (!state.get(TOP) && worldIn.getBlockState(pos.up()).getBlock() == this)
		{
			worldIn.destroyBlock(pos.up(), false);
		}
	}

	private boolean isBook(ItemStack stack)
	{
		return stack.getItem() == Items.BOOK || stack.getItem() == Items.ENCHANTED_BOOK || stack.getItem() == Items.WRITTEN_BOOK;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockState state = super.getStateForPlacement(context);
		return state.with(TOP, Boolean.FALSE);
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(FACING);
		builder.add(TOP);
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world)
	{
		if (state.get(TOP))
		{
			return new TileEntityBookshelf.Shelf();
		}

		return new TileEntityBookshelf.Cabinet();
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
}
