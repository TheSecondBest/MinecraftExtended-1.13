package com.nmg.me.block;

import com.nmg.me.client.gui.interactionobjects.InteractionObjectCrate;
import com.nmg.me.tileentity.TileEntityCrate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCrate extends Block
{
	public BlockCrate()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote && player instanceof EntityPlayerMP)
		{
			if (!player.isSneaking())
			{
				TileEntity tileEntity = worldIn.getTileEntity(pos);

				if (tileEntity instanceof TileEntityCrate)
				{
					NetworkHooks.openGui((EntityPlayerMP)player, new InteractionObjectCrate((TileEntityCrate)tileEntity), pos);
					worldIn.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
			}
		}

		return true;
	}

	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileEntityCrate)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
		}

		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world)
	{
		return new TileEntityCrate();
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
}
