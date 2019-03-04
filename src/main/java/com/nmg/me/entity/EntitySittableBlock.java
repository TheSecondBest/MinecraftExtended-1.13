package com.nmg.me.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySittableBlock extends Entity
{

	public int blockPosX;
	public int blockPosY;
	public int blockPosZ;

	public EntitySittableBlock(World worldIn)
	{
		super(null, worldIn); // TODO: Create entity type
		this.noClip = true;
		this.width = 0.01f;
		this.height = 0.01f;
	}

	public EntitySittableBlock(World world, double x, double y, double z, double yOffset)
	{
		this(world);
		this.blockPosX = (int) x;
		this.blockPosY = (int) y;
		this.blockPosZ = (int) z;
		this.setPosition(x + 0.5D, y + yOffset, z + 0.5D);
	}

	public EntitySittableBlock(World world, double x, double y, double z, double yOffset, int rotation, double rotationOffset)
	{
		this(world);
		this.blockPosX = (int) x;
		this.blockPosY = (int) y;
		this.blockPosZ = (int) z;
		this.setPositionConsideringRotation(x + 0.5D, y + yOffset, z + 0.5D, rotation, rotationOffset);
	}

	private void setPositionConsideringRotation(double x, double y, double z, int rotation, double rotationOffset)
	{
		switch (rotation)
		{
			case 2:
				z += rotationOffset;
				break;
			case 0:
				z -= rotationOffset;
				break;
			case 3:
				x -= rotationOffset;
				break;
			case 1:
				x += rotationOffset;
				break;
		}

		this.setPosition(x, y, z);
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.0D;
	}

	@Override
	protected void registerData() {}

	@Override
	protected boolean shouldSetPosAfterLoading()
	{
		return false;
	}

	@Override
	protected void readAdditional(NBTTagCompound compound) {}

	@Override
	protected void writeAdditional(NBTTagCompound compound) {}

	@Override
	public void baseTick()
	{
		if (!this.world.isRemote)
		{
			if (!this.isBeingRidden() || this.world.isAirBlock(new BlockPos(this.blockPosX, this.blockPosY, this.blockPosZ)))
			{
				this.remove();
				this.world.updateComparatorOutputLevel(this.getPosition(), this.world.getBlockState(this.getPosition()).getBlock());
			}
		}
	}

}
