package com.nmg.me.world;

import com.nmg.me.init.MEBiomes;
import com.nmg.me.init.MEWorldProviders;
import com.nmg.me.world.gen.ChunkGeneratorStorage;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderStorage// extends WorldProvider
{

	/*public WorldProviderStorage()
	{
		this.biomeProvider = new BiomeProviderSingle(MEBiomes.BIOME_STORAGE);
	}

	@Override
	public DimensionType getDimensionType()
	{
		return MEWorldProviders.dimensionTypeStorage;
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorStorage(world, world.getSeed());
	}

	@Override
	public void onPlayerAdded(EntityPlayerMP player)
	{
		this.checkPlayerCollision(player);
	}

	private void checkPlayerCollision(EntityPlayerMP player)
	{
		if (isSolidBlock(player.getPosition()))
		{
			BlockPos right = player.getPosition().east();
			if (!isSolidBlock(right))
			{
				player.setPositionAndUpdate(right.getX(), right.getY(), right.getZ());
			}
			else
			{
				BlockPos left = player.getPosition().west();
				if (!isSolidBlock(left))
				{
					player.setPositionAndUpdate(left.getX(), left.getY(), left.getZ());
				}
			}
		}
	}

	private boolean isSolidBlock(BlockPos pos)
	{
		return world.getBlockState(pos).getMaterial() != Material.AIR;
	}

	@Override
	public boolean hasSkyLight()
	{
		return true;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}*/


}
