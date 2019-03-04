package com.nmg.me.world.storage;

import com.nmg.me.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.WorldSavedDataStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorldStorageSavedData extends WorldSavedData
{

	private static final String MAP_NAME = Constants.MODID + "_WorldProviderStorageData";

	private Map<Integer, Map<String, BlockPos>> dimensionPositions = new HashMap<>();

	private final Random random = new Random();


	public WorldStorageSavedData(String name)
	{
		super(name);
	}

	@Override
	public void read(NBTTagCompound nbt)
	{
		if (nbt.hasKey("NumDimensions"))
		{
			int numDims = nbt.getInt("NumDimensions");

			for (int dim = 0; dim <= numDims; dim++)
			{
				NBTTagCompound dimTag = (NBTTagCompound) nbt.getTag("Dim_" + dim);

				if (dimTag != null)
				{
					if (dimTag.hasKey("NumPlayers"))
					{
						int numPlayers = dimTag.getInt("NumPlayers");

						for (int i = 0; i < numPlayers; i++)
						{
							String playerName = dimTag.getString("player_" + i);
							int[] blockPos = dimTag.getIntArray("player_" + i + "_pos");
							BlockPos pos = new BlockPos(blockPos[0], blockPos[1], blockPos[2]);

							if (this.dimensionPositions.get(dim) != null)
							{
								this.dimensionPositions.get(dim).put(playerName, pos);
							}
							else
							{
								Map<String, BlockPos> map = new HashMap<>();
								map.put(playerName, pos);
								this.dimensionPositions.put(dim, map);
							}

						}
					}
				}
			}
		}
	}

	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		compound.setInt("NumDimensions", this.dimensionPositions.size());

		for (int dim : this.dimensionPositions.keySet())
		{
			NBTTagCompound dimTag = new NBTTagCompound();
			dimTag.setInt("Dimension", dim);
			dimTag.setInt("NumPlayers", this.dimensionPositions.get(dim).size());
			int i = 0;
			for (String player : this.dimensionPositions.get(dim).keySet())
			{
				BlockPos pos = this.dimensionPositions.get(dim).get(player);
				System.out.println(player + ", " + pos.getX() + ", " + pos.getZ());
				dimTag.setString("player_" + i, player);
				dimTag.setIntArray("player_" + i + "_pos", new int[]{pos.getX(), pos.getY(), pos.getZ()});
				i++;
			}

			compound.setTag("Dim_" + dim, dimTag);
		}

		return compound;
	}

	public void addDimensionPos(EntityPlayer player, int dimId)
	{
		if (!this.dimensionPositions.containsKey(dimId))
		{
			Map<String, BlockPos> map = new HashMap<>();
			map.put(player.getUniqueID().toString(), player.getPosition());
			this.dimensionPositions.put(dimId, map);
		}
		else
		{
			Map<String, BlockPos> map = this.dimensionPositions.get(dimId);
			map.replace(player.getUniqueID().toString(), player.getPosition());
			this.dimensionPositions.replace(dimId, map);
		}

		super.markDirty();
	}

	public BlockPos getDimensionPos(EntityPlayer player, int dimId)
	{
		if (hasDimensionPos(player, dimId))
		{
			return this.dimensionPositions.get(dimId).get(player.getUniqueID().toString());
		}

		return new BlockPos(random.nextInt(10000), 65, random.nextInt(10000));
	}

	public boolean hasDimensionPos(EntityPlayer player, int dimId)
	{
		if (this.dimensionPositions.containsKey(dimId))
		{
			if (this.dimensionPositions.get(dimId).containsKey(player.getUniqueID().toString()))
				return true;
		}

		return false;
	}

	public static WorldStorageSavedData get(World world)
	{
		/*WorldSavedDataStorage storage = world.getMapStorage();
		WorldStorageSavedData dimensionStorageData = (WorldStorageSavedData) storage.getOrLoadData(WorldStorageSavedData.class, MAP_NAME);

		if (dimensionStorageData == null)
		{
			dimensionStorageData = new WorldStorageSavedData(MAP_NAME);
			storage.setData(MAP_NAME, dimensionStorageData);
		}

		return dimensionStorageData;*/
		return null;
	}

}
