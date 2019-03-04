package com.nmg.me.block;

import com.google.common.collect.ImmutableMap;
import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockLampPost extends Block
{

	private static final BooleanProperty TOP = BooleanProperty.create("top");
	private final ImmutableMap<Boolean, VoxelShape> SHAPES;

	public BlockLampPost()
	{
		super(Properties.create(Material.WOOD).lightValue(14).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(TOP, false));
		SHAPES = this.generateShapes();
	}

	private ImmutableMap<Boolean, VoxelShape> generateShapes()
	{
		ImmutableMap.Builder<Boolean, VoxelShape> builder = new ImmutableMap.Builder<>();
		List<VoxelShape> shapes = new ArrayList<>();

		shapes.add(Block.makeCuboidShape(4, 0, 4, 12, 1, 12));
		shapes.add(Block.makeCuboidShape(4, 1, 4, 5, 7, 5));
		shapes.add(Block.makeCuboidShape(11, 1, 4, 12, 7, 5));
		shapes.add(Block.makeCuboidShape(11, 1, 11, 12, 7, 12));
		shapes.add(Block.makeCuboidShape(4, 1, 11, 5, 7, 12));
		shapes.add(Block.makeCuboidShape(4, 7, 4, 12, 8, 12));
		shapes.add(Block.makeCuboidShape(5, 1, 4, 11, 7, 5));
		shapes.add(Block.makeCuboidShape(5, 1, 11, 11, 7, 12));
		shapes.add(Block.makeCuboidShape(4, 1, 5, 5, 7, 11));
		shapes.add(Block.makeCuboidShape(11, 1, 5, 12, 7, 11));
		shapes.add(Block.makeCuboidShape(6.8, 1, 6.8, 8.8, 6, 8.8));

		builder.put(true, VoxelShapeHelper.combineAll(shapes));
		builder.put(false, Block.makeCuboidShape(7, 0, 7, 9, 16, 9));

		return builder.build();
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPES.get(state.get(TOP));
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPES.get(state.get(TOP));
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
	{
		return worldIn.isAirBlock(pos) && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2)) && worldIn.isAirBlock(pos.up(3));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos.up(), MEBlocks.LAMP_POST.getDefaultState().with(TOP, false), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
		worldIn.setBlockState(pos.up(2), MEBlocks.LAMP_POST.getDefaultState().with(TOP, false), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);

		if (!state.get(TOP))
		{
			worldIn.setBlockState(pos.up(3), MEBlocks.LAMP_POST.getDefaultState().with(TOP, true), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!state.get(TOP))
		{
			boolean down = (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockLampPost);
			boolean twoDown = (worldIn.getBlockState(pos.down(2)).getBlock() instanceof BlockLampPost);

			if (twoDown)
			{
				worldIn.destroyBlock(pos.down(), false);
				worldIn.destroyBlock(pos.down(2), false);
			}
			else if (down)
			{
				if (worldIn.getBlockState(pos.up(2)).getBlock() instanceof BlockLampPost)
					worldIn.destroyBlock(pos.up(2), false);

				worldIn.destroyBlock(pos.down(), false);
			}
			else
			{
				worldIn.destroyBlock(pos.up(2), false);
				worldIn.destroyBlock(pos.up(3), false);
			}

			worldIn.destroyBlock(pos.up(), false);
		}
		else
		{
			worldIn.destroyBlock(pos.down(), false);
			worldIn.destroyBlock(pos.down(2), false);
			worldIn.destroyBlock(pos.down(3), false);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new TextComponentTranslation(this.getTranslationKey() + ".desc").applyTextStyle(TextFormatting.YELLOW));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(TOP);
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
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
