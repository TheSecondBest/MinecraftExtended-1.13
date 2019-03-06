package com.nmg.me.proxy;

import com.nmg.me.client.renderer.entity.*;
import com.nmg.me.client.renderer.tileentity.TileEntityBookshelfRenderer;
import com.nmg.me.client.renderer.tileentity.TileEntityDisplayCabinetRenderer;
import com.nmg.me.client.renderer.tileentity.TileEntityStorageShelfRenderer;
import com.nmg.me.entity.projectile.*;
import com.nmg.me.handlers.RegistryHandler;
import com.nmg.me.init.MEBlocks;
import com.nmg.me.init.MEItems;
import com.nmg.me.tileentity.TileEntityBookshelf;
import com.nmg.me.tileentity.TileEntityDisplayCabinet;
import com.nmg.me.tileentity.TileEntityStorageShelf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy extends ServerProxy
{

	@Override
	public void preInit()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, RenderExplodingArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDiamondArrow.class, RenderDiamondArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianArrow.class, RenderObsidianArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityEmeraldArrow.class, RenderEmeraldArrow::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGoldArrow.class, RenderGoldArrow::new);
	}

	@SubscribeEvent
	public void onBlockColorsRegister(ColorHandlerEvent.Block event)
	{
		event.getBlockColors().register((p_210226_0_, p_210226_1_, p_210226_2_, p_210226_3_) -> {
			return p_210226_1_ != null && p_210226_2_ != null ? BiomeColors.getWaterColor(p_210226_1_, p_210226_2_) : -1;
		}, MEBlocks.BARREL);
	}

	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBookshelf.Shelf.class, new TileEntityBookshelfRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDisplayCabinet.class, new TileEntityDisplayCabinetRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStorageShelf.class, new TileEntityStorageShelfRenderer());
	}

	@Override
	public boolean isSinglePlayer()
	{
		return Minecraft.getInstance().isSingleplayer();
	}

	@Override
	public boolean isDedicatedServer()
	{
		return false;
	}

}
