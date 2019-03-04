package com.nmg.me.block;

import com.nmg.me.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtils;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockBarrel extends Block
{

	private static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);

	public BlockBarrel()
	{
		super(Properties.create(Material.WOOD).hardnessAndResistance(2.0f));
		this.setDefaultState(this.getStateContainer().getBaseState().with(LEVEL, 0));
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.isEmpty())
		{
			return true;
		}
		else
		{
			int i = state.get(LEVEL);
			Item item = itemstack.getItem();

			if (item == Items.WATER_BUCKET)
			{
				if (i < 3 && !worldIn.isRemote)
				{
					if (!player.abilities.isCreativeMode)
					{
						player.setHeldItem(hand, new ItemStack(Items.BUCKET));
					}

					this.setWaterLevel(worldIn, pos, state, 3);
					worldIn.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return true;
			}
			else if (item == Items.BUCKET)
			{
				if (i == 3 && !worldIn.isRemote)
				{
					if (!player.abilities.isCreativeMode)
					{
						itemstack.shrink(1);

						if (itemstack.isEmpty())
						{
							player.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
						}
						else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET)))
						{
							player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
						}
					}

					this.setWaterLevel(worldIn, pos, state, 0);
					worldIn.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return true;
			}
			else if (item == Items.GLASS_BOTTLE)
			{
				if (i > 0 && !worldIn.isRemote)
				{
					if (!player.abilities.isCreativeMode)
					{
						ItemStack itemstack3 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.WATER);
						itemstack.shrink(1);

						if (itemstack.isEmpty())
						{
							player.setHeldItem(hand, itemstack3);
						}
						else if (!player.inventory.addItemStackToInventory(itemstack3))
						{
							player.dropItem(itemstack3, false);
						}
						else if (player instanceof EntityPlayerMP)
						{
							((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
						}
					}

					worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					this.setWaterLevel(worldIn, pos, state, i - 1);
				}

				return true;
			}
			else if (item == Items.POTION && PotionUtils.getPotionFromItem(itemstack) == PotionTypes.WATER)
			{
				if (i < 3 && !worldIn.isRemote)
				{
					if (!player.abilities.isCreativeMode)
					{
						ItemStack itemstack2 = new ItemStack(Items.GLASS_BOTTLE);
						player.setHeldItem(hand, itemstack2);

						if (player instanceof EntityPlayerMP)
						{
							((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
						}
					}

					worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					this.setWaterLevel(worldIn, pos, state, i + 1);
				}

				return true;
			}
			else
			{
				if (i > 0 && item instanceof ItemArmorDyeable)
				{
					ItemArmorDyeable itemarmor = (ItemArmorDyeable) item;

					if (itemarmor.getArmorMaterial() == ArmorMaterial.LEATHER && itemarmor.hasColor(itemstack) && !worldIn.isRemote)
					{
						itemarmor.removeColor(itemstack);
						this.setWaterLevel(worldIn, pos, state, i - 1);
						return true;
					}
				}

				if (i > 0 && item instanceof ItemBanner)
				{
					if (TileEntityBanner.getPatterns(itemstack) > 0 && !worldIn.isRemote)
					{
						ItemStack itemstack1 = itemstack.copy();
						itemstack1.setCount(1);
						TileEntityBanner.removeBannerData(itemstack1);

						if (!player.abilities.isCreativeMode)
						{
							itemstack.shrink(1);
							this.setWaterLevel(worldIn, pos, state, i - 1);
						}

						if (itemstack.isEmpty())
						{
							player.setHeldItem(hand, itemstack1);
						}
						else if (!player.inventory.addItemStackToInventory(itemstack1))
						{
							player.dropItem(itemstack1, false);
						}
						else if (player instanceof EntityPlayerMP)
						{
							((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
	}

	private void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level)
	{
		worldIn.setBlockState(pos, state.with(LEVEL, MathHelper.clamp(level, 0, 3)), Constants.BlockStateFlags.NOTIFY_CLIENTS);
		worldIn.updateComparatorOutputLevel(pos, this);
	}

	@Override
	public void fillWithRain(World worldIn, BlockPos pos)
	{
		if (worldIn.rand.nextInt(20) == 0)
		{
			float temperature = worldIn.getBiome(pos).getTemperature(pos);

			if (!(temperature < 0.15f))
			{
				IBlockState state = worldIn.getBlockState(pos);

				if (state.get(LEVEL) < 3)
				{
					worldIn.setBlockState(pos, state.cycle(LEVEL), Constants.BlockStateFlags.NOTIFY_CLIENTS);
				}
			}
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
	{
		builder.add(LEVEL);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
}
