package com.nmg.me.block;

import com.nmg.me.Constants;
import com.nmg.me.client.gui.interactionobjects.InteractionObjectBookshelf;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.tileentity.TileEntityBookshelf;
import com.nmg.me.utils.MEUtils;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockPierBookshelf extends MEBlockFacing
{

	private static final BooleanProperty TOP = BooleanProperty.create("top");

	private static final AxisAlignedBB BOUNDING_BOX_NORTH_TOP = MEUtils.calculateAABB(0, 0, 0, 16, 16, 7);
	private static final AxisAlignedBB BOUNDING_BOX_NORTH_BOTTOM = MEUtils.calculateAABB(0, 0, 0, 16, 16, 14);
	private static final AxisAlignedBB BOUNDING_BOX_EAST_TOP = MEUtils.calculateAABB(9, 0, 0, 7, 16, 16);
	private static final AxisAlignedBB BOUNDING_BOX_EAST_BOTTOM = MEUtils.calculateAABB(2, 0, 0, 14, 16, 16);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH_TOP = MEUtils.calculateAABB(0, 0, 9, 16, 16, 7);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH_BOTTOM = MEUtils.calculateAABB(0, 0, 2, 16, 16, 14);
	private static final AxisAlignedBB BOUNDING_BOX_WEST_TOP = MEUtils.calculateAABB(0, 0, 0, 7, 16, 16);
	private static final AxisAlignedBB BOUNDING_BOX_WEST_BOTTOM = MEUtils.calculateAABB(0, 0, 0, 14, 16, 16);

	public BlockPierBookshelf()
	{
		super(Properties.create(Material.WOOD));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(TOP, Boolean.FALSE));
	}

	/*@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		boolean top = state.getValue(TOP);

		switch (state.getValue(FACING))
		{
			case NORTH:
				return top ? BOUNDING_BOX_NORTH_TOP : BOUNDING_BOX_NORTH_BOTTOM;
			case EAST:
				return top ? BOUNDING_BOX_EAST_TOP : BOUNDING_BOX_EAST_BOTTOM;
			case SOUTH:
				return top ? BOUNDING_BOX_SOUTH_TOP : BOUNDING_BOX_SOUTH_BOTTOM;
			case WEST:
				return top ? BOUNDING_BOX_WEST_TOP : BOUNDING_BOX_WEST_BOTTOM;
		}

		return FULL_BLOCK_AABB;
	}*/

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

		super.onReplaced(state, worldIn, pos, newState, isMoving);
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
