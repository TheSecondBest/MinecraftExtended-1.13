package com.nmg.me.world.gen;

import com.nmg.me.init.MEBlocks;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorStorage //implements IChunkGenerator
{


	/*private static final int STORAGE_CELL_Y = 64;
	private static final int STORAGE_CELL_HEIGHT = 32;
	private static final int STORAGE_CELL_SIZE = 16;

	private final World world;

	public ChunkGeneratorStorage(World world, long seed)
	{
		this.world = world;
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkPrimer = new ChunkPrimer();

		for (int i = 0; i < STORAGE_CELL_SIZE; i++)
		{
			for (int j = 0; j < STORAGE_CELL_SIZE; j++)
			{
				chunkPrimer.setBlockState(i, STORAGE_CELL_Y, j, MEBlocks.STORAGE_WALL.getDefaultState());
			}
		}

		for (int i = 0; i < STORAGE_CELL_SIZE; i++)
		{
			for (int j = STORAGE_CELL_Y + 1; j < (STORAGE_CELL_HEIGHT + STORAGE_CELL_Y); j++)
			{
				chunkPrimer.setBlockState(i, j, 0, MEBlocks.STORAGE_WALL.getDefaultState());
			}
		}

		for (int i = 0; i < STORAGE_CELL_SIZE; i++)
		{
			for (int j = STORAGE_CELL_Y + 1; j < (STORAGE_CELL_HEIGHT + STORAGE_CELL_Y); j++)
			{
				chunkPrimer.setBlockState(0, j, i, MEBlocks.STORAGE_WALL.getDefaultState());
			}
		}

		for (int i = 0; i < STORAGE_CELL_SIZE; i++)
		{
			for (int j = 0; j < STORAGE_CELL_SIZE; j++)
			{
				chunkPrimer.setBlockState(i, STORAGE_CELL_HEIGHT + STORAGE_CELL_Y, j, MEBlocks.STORAGE_WALL.getDefaultState());
			}
		}

		Chunk chunk = new Chunk(world, chunkPrimer, x, z);
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int x, int z)
	{

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return null;
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
	{
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
	{

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
	{
		return false;
	}*/


}
