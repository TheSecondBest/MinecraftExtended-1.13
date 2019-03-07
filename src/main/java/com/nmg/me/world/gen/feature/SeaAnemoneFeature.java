package com.nmg.me.world.gen.feature;

import com.nmg.me.Constants;
import com.nmg.me.init.MEBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SeaGrassConfig;

import java.util.Random;

public class SeaAnemoneFeature extends Feature<SeaGrassConfig>
{
	@Override
	public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random random, BlockPos pos, SeaGrassConfig config)
	{
		int i = 0;

		for (int j = 0; j < config.field_203237_a; ++j)
		{
			int k = random.nextInt(8) - random.nextInt(8);
			int l = random.nextInt(8) - random.nextInt(8);
			int i1 = world.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + k, pos.getZ() + l);

			BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);

			if (world.getBlockState(blockpos).getBlock() == Blocks.WATER)
			{
				IBlockState iblockstate = this.getSeaAnemoneBlockState(random, config);
				if (iblockstate.isValidPosition(world, blockpos))
				{
					world.setBlockState(blockpos, iblockstate, Constants.BlockStateFlags.NOTIFY_CLIENTS);
					++i;
				}
			}
		}

		return i > 0;
	}

	private IBlockState getSeaAnemoneBlockState(Random random, SeaGrassConfig config)
	{
		if (random.nextDouble() < config.field_203238_b)
		{
			return MEBlocks.PINK_SEA_ANEMONE.getDefaultState();
		}
		else
		{
			return MEBlocks.BLUE_SEA_ANEMONE.getDefaultState();
		}
	}

}
