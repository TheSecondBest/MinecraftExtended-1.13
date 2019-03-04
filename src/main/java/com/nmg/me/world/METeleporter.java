package com.nmg.me.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.util.ITeleporter;

public class METeleporter implements ITeleporter
{

	private double x, y, z;

	public METeleporter(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw)
	{
		world.getBlockState(new BlockPos(this.x, this.y, this.z));
		entity.setPosition(x, y, z);
		entity.motionX = 0;
		entity.motionY = 0;
		entity.motionZ = 0;
	}

	public static void teleportToDimension(EntityPlayer player, DimensionType dimension, BlockPos pos)
	{
		teleportToDimension(player, dimension, pos.getX(), pos.getY(), pos.getZ());
	}

	public static void teleportToDimension(EntityPlayer player, DimensionType dimension, double x, double y, double z)
	{
		DimensionType oldDimension = getCurrentDimension(player);

		if (dimension == oldDimension)
			return;

		EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
		MinecraftServer server = player.getEntityWorld().getServer();

		if (server != null)
		{
			WorldServer worldServer = server.getWorld(dimension);

			if (worldServer.getServer() != null)
			{
				worldServer.getServer().getPlayerList().changePlayerDimension(entityPlayerMP, dimension, new METeleporter(x, y, z));
				player.setPositionAndUpdate(x, y, z);
			}
		}
	}

	public static DimensionType getCurrentDimension(EntityPlayer player)
	{
		return player.getEntityWorld().getDimension().getType();
	}

	@Override
	public boolean isVanilla()
	{
		return false;
	}

}
