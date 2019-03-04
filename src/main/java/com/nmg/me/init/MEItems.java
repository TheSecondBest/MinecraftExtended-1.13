package com.nmg.me.init;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.handlers.RegistryHandler;
import com.nmg.me.item.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class MEItems
{

	public static final Item STORAGE_KEY;
	public static final Item HEART_OF_THE_GUARDIAN;
	public static final Item EXPLODING_ARROW, GOLD_ARROW, DIAMOND_ARROW, EMERALD_ARROW, OBSIDIAN_ARROW;
	public static final Item EMERALD_BOW, OBSIDIAN_BOW, DIAMOND_BOW, IRON_BOW, GOLD_BOW;
	public static final Item OAK_PLANK;
	public static final Item BIG_OAK_LOG_PLANK;
	public static final Item IRON_SAW;
	public static final Item OBSIDIAN_SWORD, OBSIDIAN_PICKAXE, OBSIDIAN_AXE, OBSIDIAN_SHOVEL;
	public static final Item OBSIDIAN_SHARD;
	public static final Item EMERALD_SWORD, EMERALD_PICKAXE, EMERALD_AXE, EMERALD_SHOVEL;
	public static final Item OBSIDIAN_HELMET, OBSIDIAN_CHESTPLATE, OBSIDIAN_LEGGINGS, OBSIDIAN_BOOTS;
	public static final Item EMERALD_HELMET, EMERALD_CHESTPLATE, EMERALD_LEGGINGS, EMERALD_BOOTS;
	public static final Item OBSIDIAN_SHIELD, EMERALD_SHIELD, DIAMOND_SHIELD, IRON_SHIELD;
	public static final Item FIERY_PICKAXE, FIERY_SHOVEL;
	public static final Item PIER_DOOR;

	static
	{
		STORAGE_KEY = new ItemStorageKey();
		HEART_OF_THE_GUARDIAN = new ItemHeartOfTheGuardian();
		EMERALD_BOW = new ItemEmeraldBow();
		OBSIDIAN_BOW = new ItemObsidianBow();
		DIAMOND_BOW = new ItemDiamondBow();
		IRON_BOW = new ItemIronBow();
		GOLD_BOW = new ItemGoldBow();
		OAK_PLANK = new Item((new Item.Properties()).group(MinecraftExtended.ITEMS));
		BIG_OAK_LOG_PLANK = new Item((new Item.Properties()).group(MinecraftExtended.ITEMS));
		IRON_SAW = new Item((new Item.Properties()).group(MinecraftExtended.ITEMS));
		OBSIDIAN_SWORD = new ItemSword(ItemTier.OBSIDIAN, 3, -2.4f, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_PICKAXE = new MEItemPickaxe(ItemTier.OBSIDIAN, 1, -2.8f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		OBSIDIAN_AXE = new MEItemAxe(ItemTier.OBSIDIAN, 8.5f, -2.8f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		OBSIDIAN_SHOVEL = new ItemSpade(ItemTier.OBSIDIAN, 1.5f, -3.0f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		OBSIDIAN_SHARD = new Item((new Item.Properties()).group(MinecraftExtended.ITEMS));
		EMERALD_SWORD = new ItemSword(ItemTier.EMERALD, 3, -2.4f, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_PICKAXE = new MEItemPickaxe(ItemTier.EMERALD, 1, -2.8f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		EMERALD_AXE = new MEItemAxe(ItemTier.EMERALD, 7.5f, -2.7f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		EMERALD_SHOVEL = new ItemSpade(ItemTier.EMERALD, 1.5f, -3.0f, (new Item.Properties()).group(MinecraftExtended.TOOLS));
		EXPLODING_ARROW = new ItemExplodingArrow((new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_ARROW = new ItemEmeraldArrow((new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_ARROW = new ItemObsidianArrow((new Item.Properties()).group(MinecraftExtended.COMBAT));
		DIAMOND_ARROW = new ItemDiamondArrow((new Item.Properties()).group(MinecraftExtended.COMBAT));
		GOLD_ARROW = new ItemGoldArrow((new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_HELMET = new ItemArmor(ArmorMaterial.OBSIDIAN, EntityEquipmentSlot.HEAD, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_CHESTPLATE = new ItemArmor(ArmorMaterial.OBSIDIAN, EntityEquipmentSlot.CHEST, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_LEGGINGS = new ItemArmor(ArmorMaterial.OBSIDIAN, EntityEquipmentSlot.LEGS, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_BOOTS = new ItemArmor(ArmorMaterial.OBSIDIAN, EntityEquipmentSlot.FEET, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_HELMET = new ItemArmor(ArmorMaterial.EMERALD, EntityEquipmentSlot.HEAD, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_CHESTPLATE = new ItemArmor(ArmorMaterial.EMERALD, EntityEquipmentSlot.CHEST, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_LEGGINGS = new ItemArmor(ArmorMaterial.EMERALD, EntityEquipmentSlot.LEGS, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		EMERALD_BOOTS = new ItemArmor(ArmorMaterial.EMERALD, EntityEquipmentSlot.FEET, (new Item.Properties()).group(MinecraftExtended.COMBAT));
		OBSIDIAN_SHIELD = new MEItemShield("obsidian_shield", (new Item.Properties()).defaultMaxDamage(336 * 3)).setRepairItem(Blocks.OBSIDIAN.asItem());
		EMERALD_SHIELD = new MEItemShield("emerald_shield", (new Item.Properties()).defaultMaxDamage(336 * 5)).setRepairItem(Items.EMERALD);
		DIAMOND_SHIELD = new MEItemShield("diamond_shield", (new Item.Properties()).defaultMaxDamage(336 * 2)).setRepairItem(Items.DIAMOND);
		IRON_SHIELD = new MEItemShield("iron_shield", (new Item.Properties()).defaultMaxDamage((int) (336 * 1.5D))).setRepairItem(Items.IRON_INGOT);
		FIERY_PICKAXE = new ItemFieryPickaxe();
		FIERY_SHOVEL = new ItemFieryShovel();
		PIER_DOOR = new ItemBlockTall(MEBlocks.PIER_DOOR, (new Item.Properties()).group(MinecraftExtended.BLOCKS));
	}

	public static void register()
	{
		registerItem("storage_key", STORAGE_KEY);
		registerItem("heart_of_the_guardian", HEART_OF_THE_GUARDIAN);
		registerItem("emerald_bow", EMERALD_BOW);
		registerItem("obsidian_bow", OBSIDIAN_BOW);
		registerItem("diamond_bow", DIAMOND_BOW);
		registerItem("iron_bow", IRON_BOW);
		registerItem("gold_bow", GOLD_BOW);
		registerItem("oak_plank", OAK_PLANK);
		registerItem("big_oak_log_plank", BIG_OAK_LOG_PLANK);
		registerItem("iron_saw", IRON_SAW);
		registerItem("obsidian_sword", OBSIDIAN_SWORD);
		registerItem("obsidian_pickaxe", OBSIDIAN_PICKAXE);
		registerItem("obsidian_axe", OBSIDIAN_AXE);
		registerItem("obsidian_shovel", OBSIDIAN_SHOVEL);
		registerItem("obsidian_shard", OBSIDIAN_SHARD);
		registerItem("emerald_sword", EMERALD_SWORD);
		registerItem("emerald_pickaxe", EMERALD_PICKAXE);
		registerItem("emerald_axe", EMERALD_AXE);
		registerItem("emerald_shovel", EMERALD_SHOVEL);
		registerItem("gold_arrow", GOLD_ARROW);
		registerItem("diamond_arrow", DIAMOND_ARROW);
		registerItem("emerald_arrow", EMERALD_ARROW);
		registerItem("obsidian_arrow", OBSIDIAN_ARROW);
		registerItem("exploding_arrow", EXPLODING_ARROW);
		registerItem("obsidian_helmet", OBSIDIAN_HELMET);
		registerItem("obsidian_chestplate", OBSIDIAN_CHESTPLATE);
		registerItem("obsidian_leggings", OBSIDIAN_LEGGINGS);
		registerItem("obsidian_boots", OBSIDIAN_BOOTS);
		registerItem("emerald_helmet", EMERALD_HELMET);
		registerItem("emerald_chestplate", EMERALD_CHESTPLATE);
		registerItem("emerald_leggings", EMERALD_LEGGINGS);
		registerItem("emerald_boots", EMERALD_BOOTS);
		registerItem("obsidian_shield", OBSIDIAN_SHIELD);
		registerItem("emerald_shield", EMERALD_SHIELD);
		registerItem("diamond_shield", DIAMOND_SHIELD);
		registerItem("iron_shield", IRON_SHIELD);
		registerItem("fiery_pickaxe", FIERY_PICKAXE);
		registerItem("fiery_shovel", FIERY_SHOVEL);
		registerItem("pier_door_item", PIER_DOOR);
	}

	private static void registerItem(String id, Item itemIn)
	{
		itemIn.setRegistryName(new ResourceLocation(Constants.MODID, id));
		RegistryHandler.Items.add(itemIn);
	}

	public enum ItemTier implements IItemTier
	{
		OBSIDIAN(4, 2311, 12, 4, 14, () -> {
			return Ingredient.fromItems(MEItems.OBSIDIAN_SHARD);
		}),

		EMERALD(3, 1743, 9, 6, 10, () -> {
			return Ingredient.fromItems(Items.EMERALD);
		});

		/**
		 * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = WOOD/GOLD)
		 */
		private final int harvestLevel;
		/**
		 * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
		 */
		private final int maxUses;
		/**
		 * The strength of this tool material against blocks which it is effective against.
		 */
		private final float efficiency;
		/**
		 * Damage versus entities.
		 */
		private final float attackDamage;
		/**
		 * Defines the natural enchantability factor of the material.
		 */
		private final int enchantability;
		private final LazyLoadBase<Ingredient> repairMaterial;

		ItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn)
		{
			this.harvestLevel = harvestLevelIn;
			this.maxUses = maxUsesIn;
			this.efficiency = efficiencyIn;
			this.attackDamage = attackDamageIn;
			this.enchantability = enchantabilityIn;
			this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
		}

		@Override
		public int getMaxUses()
		{
			return this.maxUses;
		}

		@Override
		public float getEfficiency()
		{
			return this.efficiency;
		}

		@Override
		public float getAttackDamage()
		{
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel()
		{
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability()
		{
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial()
		{
			return this.repairMaterial.getValue();
		}

	}

	public enum ArmorMaterial implements IArmorMaterial
	{
		OBSIDIAN("obsidian", 40, new int[] { 6, 12, 14, 6 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5f, () -> {
			return Ingredient.fromItems(MEItems.OBSIDIAN_SHARD);
		}),
		EMERALD("emerald", 62, new int[] { 9, 17, 18, 9 }, 36, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.5f, () -> {
			return Ingredient.fromItems(Items.EMERALD);
		})
		;

		/**
		 * Holds the 'base' maxDamage that each armorType have.
		 */
		private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
		private final String name;
		/**
		 * Holds the maximum damage factor (each piece multiply this by it's own value) of the material, this is the item
		 * damage (how much can absorb before breaks)
		 */
		private final int maxDamageFactor;
		/**
		 * Holds the damage reduction (each 1 points is half a shield on gui) of each piece of armor (helmet, plate, legs and
		 * boots)
		 */
		private final int[] damageReductionAmountArray;
		/**
		 * Return the enchantability factor of the material
		 */
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final float toughness;
		private final LazyLoadBase<Ingredient> repairMaterial;

		ArmorMaterial(String nameIn, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial)
		{
			this.name = nameIn;
			this.maxDamageFactor = maxDamageFactor;
			this.damageReductionAmountArray = damageReductionAmountArray;
			this.enchantability = enchantability;
			this.soundEvent = soundEvent;
			this.toughness = toughness;
			this.repairMaterial = new LazyLoadBase<>(repairMaterial);
		}

		public int getDurability(EntityEquipmentSlot slotIn)
		{
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		}

		public int getDamageReductionAmount(EntityEquipmentSlot slotIn)
		{
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		public int getEnchantability()
		{
			return this.enchantability;
		}

		public SoundEvent getSoundEvent()
		{
			return this.soundEvent;
		}

		public Ingredient getRepairMaterial()
		{
			return this.repairMaterial.getValue();
		}

		@OnlyIn(Dist.CLIENT)
		public String getName()
		{
			return this.name;
		}

		public float getToughness()
		{
			return this.toughness;
		}


	}

}
