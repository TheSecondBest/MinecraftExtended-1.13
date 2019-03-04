package com.nmg.me.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.tileentity.TileEntityDisplayCabinet;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockDisplayCabinet extends MEBlockFacing implements IBucketPickupHandler, ILiquidContainer
{

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final BooleanProperty OPEN = BooleanProperty.create("open");

	public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

	public BlockDisplayCabinet()
	{
		super(Properties.create(Material.WOOD));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(OPEN, Boolean.FALSE).with(WATERLOGGED, false));
		SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
	}

	private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
	{
		final VoxelShape[] BACK = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 0, 0, 14, 14, 1));
		final VoxelShape[] WALL_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 0, 1, 3, 14, 5));
		final VoxelShape[] WALL_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 1, 14, 14, 5));
		final VoxelShape[] TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 13, 1, 13, 14, 5));
		final VoxelShape[] GRIP_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(7, 9, 1, 9, 10, 2));
		final VoxelShape[] GRIP_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(7, 3, 1, 9, 4, 2));
		final VoxelShape[] TOP_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 0, 1, 13, 1, 5));
		final VoxelShape[] RIM_0 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2, 0, 5, 3, 14, 6));
		final VoxelShape[] RIM_1 = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13, 0, 5, 14, 14, 6));
		final VoxelShape[] RIM_BOTTOM = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 0, 5, 13, 1, 6));
		final VoxelShape[] RIM_TOP = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 13, 5, 13, 14, 6));
		final VoxelShape[] GLASS = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3, 1, 5, 13, 13, 6));
		final VoxelShape[] HANDLE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(11, 6, 5, 13, 7, 6));

		ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
		for(IBlockState state : states)
		{
			EnumFacing facing = state.get(FACING);
			List<VoxelShape> shapes = new ArrayList<>();

			shapes.add(BACK[facing.getHorizontalIndex()]);
			shapes.add(WALL_0[facing.getHorizontalIndex()]);
			shapes.add(WALL_1[facing.getHorizontalIndex()]);
			shapes.add(TOP[facing.getHorizontalIndex()]);
			shapes.add(GRIP_0[facing.getHorizontalIndex()]);
			shapes.add(GRIP_1[facing.getHorizontalIndex()]);
			shapes.add(TOP_1[facing.getHorizontalIndex()]);
			shapes.add(RIM_0[facing.getHorizontalIndex()]);
			shapes.add(RIM_1[facing.getHorizontalIndex()]);
			shapes.add(RIM_BOTTOM[facing.getHorizontalIndex()]);
			shapes.add(RIM_TOP[facing.getHorizontalIndex()]);
			shapes.add(GLASS[facing.getHorizontalIndex()]);
			shapes.add(HANDLE[facing.getHorizontalIndex()]);

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
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (playerIn.isSneaking())
		{
			state = state.cycle(OPEN);
			worldIn.setBlockState(pos, state, Constants.BlockStateFlags.FORCE_RE_RENDER_MT_NOTIFY);
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
			worldIn.playEvent(playerIn, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
		}
		else
		{
			if (!worldIn.isRemote && state.get(OPEN))
			{
				TileEntity tileEntity = worldIn.getTileEntity(pos);

				if (tileEntity instanceof TileEntityDisplayCabinet)
				{
					TileEntityDisplayCabinet displayCabinet = (TileEntityDisplayCabinet) tileEntity;

					if (displayCabinet.getCurrentItem().isEmpty())
					{
						ItemStack heldItem = playerIn.getHeldItem(hand);
						displayCabinet.setItem(heldItem);
					}
					else
					{
						ItemStack stack = displayCabinet.removeItem();

						if (!playerIn.inventory.addItemStackToInventory(stack))
						{
							this.spawnItem(worldIn, pos, stack);
						}
					}

					displayCabinet.syncToClient();
				}
			}
		}

		return true;
	}

	private void spawnItem(World worldIn, BlockPos pos, ItemStack stack)
	{
		EntityItem entityItem = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
		float f = worldIn.rand.nextFloat() * 0.25F;
		float f1 = worldIn.rand.nextFloat() * ((float) Math.PI * 2F);
		entityItem.motionX = (double) (-MathHelper.sin(f1) * f);
		entityItem.motionZ = (double) (MathHelper.cos(f1) * f);
		entityItem.motionY = 0.20000000298023224D;
		worldIn.spawnEntity(entityItem);
	}

	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving)
	{
		if (!worldIn.isRemote)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);

			if (tileEntity instanceof TileEntityDisplayCabinet)
			{
				TileEntityDisplayCabinet cabinet = (TileEntityDisplayCabinet)tileEntity;

				if (!cabinet.getCurrentItem().isEmpty())
				{
					this.spawnItem(worldIn, pos, cabinet.removeItem());
				}
			}
		}

		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	private int getCloseSound()
	{
		return 1013;
	}

	private int getOpenSound()
	{
		return 1007;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluidState = world.getFluidState(pos);
		IBlockState state = super.getStateForPlacement(context);

		return state.with(OPEN, Boolean.FALSE).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		super.fillStateContainer(builder);
		builder.add(OPEN);
		builder.add(WATERLOGGED);
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

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world)
	{
		return new TileEntityDisplayCabinet();
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
}
