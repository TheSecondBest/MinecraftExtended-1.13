package com.nmg.me.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

import java.util.Properties;

public class BlockStorageWall extends Block
{
	public BlockStorageWall()
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F));
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}
}
