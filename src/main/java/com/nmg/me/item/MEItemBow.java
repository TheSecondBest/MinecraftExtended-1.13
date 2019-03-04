package com.nmg.me.item;

import com.nmg.me.MinecraftExtended;
import javafx.geometry.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MEItemBow extends ItemBow
{

	private float arrowDamageMultiplier;

	public MEItemBow(float arrowDamageMultiplier)
	{
		super((new Item.Properties()).maxStackSize(1).group(MinecraftExtended.COMBAT));
		this.arrowDamageMultiplier = arrowDamageMultiplier;

		this.addPropertyOverride(new ResourceLocation("pme:pull"), (ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) -> {
			if (entityIn == null)
			{
				return 0.0F;
			}
			else
			{
				return entityIn.getActiveItemStack().getItem() != Items.BOW ? 0.0F : (float)(stack.getUseDuration() - entityIn.getItemInUseCount()) / 20.0f;
			}
		});

		this.addPropertyOverride(new ResourceLocation("pme:pulling"), (ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) -> {
			return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
		});
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			boolean flag = entityplayer.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = this.findAmmo(entityplayer);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			if (!itemstack.isEmpty() || flag)
			{
				if (itemstack.isEmpty())
				{
					itemstack = new ItemStack(Items.ARROW);
				}

				float f = getArrowVelocity(i);

				if ((double)f >= 0.1D)
				{
					boolean flag1 = entityplayer.abilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow && ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

					if (!worldIn.isRemote)
					{
						ItemArrow itemarrow = (ItemArrow)(itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW);
						EntityArrow entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0f, 1.0F);

						if (f == 1.0F)
						{
							entityarrow.setIsCritical(true);
						}

						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

						if (j > 0)
						{
							entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

						if (k > 0)
						{
							entityarrow.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
						{
							entityarrow.setFire(100);
						}

						entityarrow.setDamage(entityarrow.getDamage() * arrowDamageMultiplier);

						this.applyArrowModifications(entityarrow);

						stack.damageItem(1, entityplayer);

						if (flag1 || entityplayer.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
						{
							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.spawnEntity(entityarrow);
					}

					worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag1 && !entityplayer.abilities.isCreativeMode)
					{
						itemstack.shrink(1);

						if (itemstack.isEmpty())
						{
							entityplayer.inventory.deleteStack(itemstack);
						}
					}

					entityplayer.addStat(StatList.ITEM_USED.get(this));
				}
			}
		}
	}

	protected void applyArrowModifications(EntityArrow arrow)
	{

	}

	private ItemStack findAmmo(EntityPlayer player)
	{
		if (this.isArrow(player.getHeldItemMainhand()))
		{
			return player.getHeldItemMainhand();
		}
		else if (this.isArrow(player.getHeldItemOffhand()))
		{
			return player.getHeldItemOffhand();
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				ItemStack stack = player.inventory.getStackInSlot(i);
				if (this.isArrow(stack))
				{
					return stack;
				}
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		if (GuiScreen.isShiftKeyDown())
		{
			tooltip.add(new TextComponentTranslation("description.arrow_damage_multiplier", String.valueOf(this.arrowDamageMultiplier)));
		}
		else
		{
			tooltip.add(new TextComponentTranslation("description.shift_info").applyTextStyle(TextFormatting.YELLOW));
		}
	}
}
