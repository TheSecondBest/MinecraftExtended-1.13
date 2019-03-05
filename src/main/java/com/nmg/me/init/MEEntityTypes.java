package com.nmg.me.init;

import com.nmg.me.Constants;
import com.nmg.me.entity.*;
import com.nmg.me.entity.projectile.*;
import com.nmg.me.handlers.RegistryHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.function.Function;

public class MEEntityTypes
{

	public static final EntityType<EntitySittableBlock> SITTABLE_BLOCK;
	public static final EntityType<EntityExplodingArrow> EXPLODING_ARROW;
	public static final EntityType<EntityDiamondArrow> DIAMOND_ARROW;
	public static final EntityType<EntityEmeraldArrow> EMERALD_ARROW;
	public static final EntityType<EntityObsidianArrow> OBSIDIAN_ARROW;
	public static final EntityType<EntityGoldArrow> GOLD_ARROW;

	static
	{
		SITTABLE_BLOCK = createEntityType("sittable_block", EntitySittableBlock.class, EntitySittableBlock::new, 64, 1, false);
		EXPLODING_ARROW = createEntityType("exploding_arrow", EntityExplodingArrow.class, EntityExplodingArrow::new, 64, 20, false);
		DIAMOND_ARROW = createEntityType("diamond_arrow", EntityDiamondArrow.class, EntityDiamondArrow::new, 64, 20, false);
		EMERALD_ARROW = createEntityType("emerald_arrow", EntityEmeraldArrow.class, EntityEmeraldArrow::new, 64, 20, false);
		OBSIDIAN_ARROW = createEntityType("obsidian_arrow", EntityObsidianArrow.class, EntityObsidianArrow::new, 64, 20, false);
		GOLD_ARROW = createEntityType("gold_arrow", EntityGoldArrow.class, EntityGoldArrow::new, 64, 20, false);
	}

	private static <T extends Entity> EntityType<T> createEntityType(String id, Class<? extends T> entityClass, Function<? super World, ? extends T> factory, int range, int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityType<T> type = EntityType.Builder.create(entityClass, factory).tracker(range, updateFrequency, sendsVelocityUpdates).build(Constants.MODID + ":" + id);
		type.setRegistryName(new ResourceLocation(Constants.MODID, id));
		return type;
	}

	public static void register()
	{
		RegistryHandler.EntityTypes.add(SITTABLE_BLOCK);
		RegistryHandler.EntityTypes.add(EXPLODING_ARROW);
		RegistryHandler.EntityTypes.add(DIAMOND_ARROW);
		RegistryHandler.EntityTypes.add(EMERALD_ARROW);
		RegistryHandler.EntityTypes.add(OBSIDIAN_ARROW);
		RegistryHandler.EntityTypes.add(GOLD_ARROW);
	}

}
