package com.nmg.me.utils;

import com.nmg.me.Constants;
import com.nmg.me.entity.EntitySittableBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

import java.util.List;

public class MEUtils
{

	public static final double PIXEL_SIZE = 0.0625D;
	private static FontRenderer fontRenderer;

	public static AxisAlignedBB calculateAABB(double startX, double startY, double startZ, double width, double height, double depth)
	{
		return new AxisAlignedBB(startX * PIXEL_SIZE, startY * PIXEL_SIZE, startZ * PIXEL_SIZE, (startX + width) * PIXEL_SIZE, (startY + height) * PIXEL_SIZE, (startZ + depth) * PIXEL_SIZE);
	}

	public static void renderText(String text, int x, int y, int color)
	{
		renderText(text, x, y, color, true);
	}

	public static void renderText(String text, int x, int y, int color, boolean centered)
	{
		if (fontRenderer == null)
			fontRenderer = Minecraft.getInstance().fontRenderer;

		if (centered)
			x = x / 2 - fontRenderer.getStringWidth(text) / 2;

		fontRenderer.drawString(text, x, y, color);
	}

	public static boolean sitOnBlock(World world, double x, double y, double z, EntityPlayer player, double yOffset)
	{
		if (!checkForExistingEntity(world, x, y, z, player))
		{
			EntitySittableBlock mount = new EntitySittableBlock(world, x, y, z, yOffset);
			world.spawnEntity(mount);
			player.startRiding(mount);
		}

		return true;
	}

	public static boolean sitOnBlockWithRotationOffset(World world, double x, double y, double z, EntityPlayer player, double yOffset, int metadata, double rotationOffset)
	{
		if (!checkForExistingEntity(world, x, y, z, player) && !world.isRemote)
		{
			EntitySittableBlock mount = new EntitySittableBlock(world, x, y, z, yOffset, metadata, rotationOffset);
			world.spawnEntity(mount);
			player.startRiding(mount);
		}

		return true;
	}

	public static boolean checkForExistingEntity(World world, double x, double y, double z, EntityPlayer player)
	{
		List<EntitySittableBlock> list = world.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1.0D, 1.0D, 1.0D));

		for (EntitySittableBlock mount : list)
		{
			if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z)
			{
				if (!mount.isBeingRidden())
				{
					player.startRiding(mount);
				}

				return true;
			}
		}

		return false;
	}

	public static boolean isSomeoneSitting(World world, double x, double y, double z)
	{
		List<EntitySittableBlock> list = world.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1.0D, 1.0D, 1.0D));

		for (EntitySittableBlock mount : list)
		{
			if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z)
			{
				return mount.isBeingRidden();
			}
		}

		return false;
	}

	public static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		} catch (NumberFormatException | NullPointerException e)
		{
			return false;
		}

		return true;
	}

	public static void syncToClient(TileEntity tileEntity)
	{
		World world = tileEntity.getWorld();
		BlockPos pos = tileEntity.getPos();

		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockStateFlags.NOTIFY_AND_UPDATE);
	}

	public static void spawnEntityItem(World worldIn, BlockPos pos, ItemStack stack)
	{
		if (!worldIn.isRemote)
		{
			EntityItem entityItem = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
			float f = worldIn.rand.nextFloat() * 0.25F;
			float f1 = worldIn.rand.nextFloat() * ((float)Math.PI * 2F);
			entityItem.motionX = (double)(-MathHelper.sin(f1) * f);
			entityItem.motionZ = (double)(MathHelper.cos(f1) * f);
			entityItem.motionY = 0.20000000298023224D;
			worldIn.spawnEntity(entityItem);
		}
	}
}
