package com.nmg.me;

import com.nmg.me.event.LootEventHandler;
import com.nmg.me.handlers.GuiHandler;
import com.nmg.me.init.*;
import com.nmg.me.proxy.ClientProxy;
import com.nmg.me.proxy.IProxy;
import com.nmg.me.proxy.ServerProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constants.MODID)
public class MinecraftExtended
{
	public static final Logger LOGGER = LogManager.getLogger();

	public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static final MEItemGroup BLOCKS = new MEItemGroup("meBlocksGroup");
	public static final MEItemGroup ITEMS = new MEItemGroup("meItemsGroup");
	public static final MEItemGroup COMBAT = new MEItemGroup("meCombatGroup");
	public static final MEItemGroup TOOLS = new MEItemGroup("meToolsGroup");

	public MinecraftExtended()
	{
		GuiHandler.registerGuiScreens();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::handleGuiRequest);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		MinecraftForge.EVENT_BUS.register(new LootEventHandler());
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		proxy.preInit();
	}

	private void doClientStuff(final FMLClientSetupEvent event)
	{
		BLOCKS.setIcon(MEBlocks.PIER_FENCE);
		ITEMS.setIcon(MEItems.STORAGE_KEY);
		COMBAT.setIcon(MEItems.OBSIDIAN_BOW);
		TOOLS.setIcon(MEItems.OBSIDIAN_PICKAXE);

		proxy.init();
	}

	private void serverStarting(final FMLServerStartingEvent event)
	{
		MECommands.register(event.getCommandDispatcher());
	}

}
