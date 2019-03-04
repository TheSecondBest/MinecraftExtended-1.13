package com.nmg.me.entity.projectile;

import com.nmg.me.init.MEItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityExplodingArrow extends EntityArrow
{

	private int landFuse = 50;
	private boolean shouldPlaySound = true;
	private boolean hasExploded = false;

	public EntityExplodingArrow(World worldIn)
	{
		super(EntityType.ARROW, worldIn);
	}

	public EntityExplodingArrow(World worldIn, double x, double y, double z)
	{
		super(EntityType.ARROW, x, y, z, worldIn);
	}

	public EntityExplodingArrow(World worldIn, EntityLivingBase shooter)
	{
		super(EntityType.ARROW, shooter, worldIn);
	}

	@Override
	public void tick()
	{
		super.tick();

		if (this.world.isRemote)
		{
			if (!this.inGround)
			{
				this.world.spawnParticle(Particles.LAVA, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
		else
		{
			if (this.inGround)
			{
				if (!this.isWet())
				{
					if (this.shouldPlaySound)
					{
						this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.NEUTRAL, 1.0f, 1.0f);
						this.shouldPlaySound = false;
					}

					if (this.timeInGround >= landFuse)
					{
						this.explode();
					}
				}
			}
			else
			{
				if (this.isBurning())
				{
					this.explode();
				}

			}
		}
	}

	private void explode()
	{
		if (!this.hasExploded)
		{
			this.world.createExplosion(null, this.posX, this.posY, this.posZ, (float)this.getDamage(),true);
			this.hasExploded = true;
			this.remove();
		}
	}

	/**
	 * Sets the time in ticks that it takes for the arrow to explode after it hits the ground
	 * @param landFuse
	 */
	public void setLandFuse(int landFuse)
	{
		this.landFuse = landFuse;
	}

	@Override
	protected ItemStack getArrowStack()
	{
		return new ItemStack(MEItems.EXPLODING_ARROW);
	}
}
