package com.nmg.me.init;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.entity.*;
import com.nmg.me.entity.projectile.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

public class MEEntityTypes
{

	public static final EntityType<EntitySittableBlock> SITTABLE_BLOCk;
	public static final EntityType<EntityExplodingArrow> EXPLODING_ARROW;
	public static final EntityType<EntityDiamondArrow> DIAMOND_ARROW;
	public static final EntityType<EntityEmeraldArrow> EMERALD_ARROW;
	public static final EntityType<EntityObsidianArrow> OBSIDIAN_ARROW;
	public static final EntityType<EntityGoldArrow> GOLD_ARROW;

	static
	{
		SITTABLE_BLOCk = EntityType.register("pme:sittable_block", EntityType.Builder.create(EntitySittableBlock.class, EntitySittableBlock::new));
		EXPLODING_ARROW = EntityType.register("pme:exploding_arrow", EntityType.Builder.create(EntityExplodingArrow.class, EntityExplodingArrow::new).tracker(64, 1, true));
		DIAMOND_ARROW = EntityType.register("pme:diamond_arrow", EntityType.Builder.create(EntityDiamondArrow.class, EntityDiamondArrow::new).tracker(64, 1, true));
		EMERALD_ARROW = EntityType.register("pme:emerald_arrow", EntityType.Builder.create(EntityEmeraldArrow.class, EntityEmeraldArrow::new).tracker(64, 1, true));
		OBSIDIAN_ARROW = EntityType.register("pme:obsidian_arrow", EntityType.Builder.create(EntityObsidianArrow.class, EntityObsidianArrow::new).tracker(64, 1, true));
		GOLD_ARROW = EntityType.register("pme:gold_arrow", EntityType.Builder.create(EntityGoldArrow.class, EntityGoldArrow::new).tracker(64, 1, true));
	}

	/*public static void registerEntities()
	{
		registerEntity("sittable_block", EntitySittableBlock.class, 0);
		registerEntity("exploding_arrow", EntityExplodingArrow.class, 1, 64, true);
		registerEntity("diamond_arrow", EntityDiamondArrow.class, 2, 64, true);
		registerEntity("emerald_arrow", EntityEmeraldArrow.class, 3, 64, true);
		registerEntity("obsidian_arrow", EntityObsidianArrow.class, 4, 64, true);
		registerEntity("gold_arrow", EntityGoldArrow.class, 5, 64, true);
	}

	private static void registerEntity(String name, Class<? extends Entity> entityClass, int id, int trackingRange, boolean sendsVelocityUpdates, int color1, int color2)
	{
		ResourceLocation registryName = new ResourceLocation(Constants.MODID + ":" + name);
		EntityRegistry.registerModEntity(registryName, entityClass, name, id, MinecraftExtended.instance, trackingRange, 1, sendsVelocityUpdates, color1, color2);
	}

	private static void registerEntity(String name, Class<? extends Entity> entityClass, int id, int trackingRange, boolean sendsVelocityUpdates)
	{
		ResourceLocation registryName = new ResourceLocation(Constants.MODID + ":" + name);
		EntityRegistry.registerModEntity(registryName, entityClass, name, id, MinecraftExtended.instance, trackingRange, 1, sendsVelocityUpdates);
	}

	private static void registerEntity(String name, Class<? extends Entity> entityClass, int id)
	{
		registerEntity(name, entityClass, id, 64, false);
	}*/

}
