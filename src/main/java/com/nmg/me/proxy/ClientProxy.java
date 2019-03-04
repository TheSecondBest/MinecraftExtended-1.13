package com.nmg.me.proxy;

import com.nmg.me.client.renderer.entity.*;
import com.nmg.me.client.renderer.tileentity.TileEntityBookshelfRenderer;
import com.nmg.me.client.renderer.tileentity.TileEntityDisplayCabinetRenderer;
import com.nmg.me.client.renderer.tileentity.TileEntityStorageShelfRenderer;
import com.nmg.me.entity.projectile.*;
import com.nmg.me.init.MEItems;
import com.nmg.me.tileentity.TileEntityBookshelf;
import com.nmg.me.tileentity.TileEntityDisplayCabinet;
import com.nmg.me.tileentity.TileEntityStorageShelf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

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
