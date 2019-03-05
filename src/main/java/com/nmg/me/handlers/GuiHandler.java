package com.nmg.me.handlers;

import com.nmg.me.Constants;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.client.gui.GuiBookshelf;
import com.nmg.me.client.gui.GuiCarpentersTable;
import com.nmg.me.client.gui.GuiCrate;
import com.nmg.me.client.gui.GuiLargeChest;
import com.nmg.me.client.gui.container.ContainerBookshelfCabinet;
import com.nmg.me.client.gui.container.ContainerCarpentersTable;
import com.nmg.me.client.gui.container.ContainerCrate;
import com.nmg.me.client.gui.container.ContainerLargeChest;
import com.nmg.me.tileentity.TileEntityBookshelf;
import com.nmg.me.tileentity.TileEntityCrate;
import com.nmg.me.tileentity.TileEntityLargeChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.network.FMLPlayMessages;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class GuiHandler
{

	private static final Map<ResourceLocation, Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>>> GUISUPPLIERS = new HashMap<>();

	public static void registerGuiScreens()
	{
		registerGui("crate_gui", () -> (playerInv, pos) -> new GuiCrate(new ContainerCrate(playerInv, (TileEntityCrate)playerInv.player.world.getTileEntity(pos)), playerInv));
		registerGui("large_chest_gui", () -> (playerInv, pos) -> new GuiLargeChest(new ContainerLargeChest(playerInv, (TileEntityLargeChest)playerInv.player.world.getTileEntity(pos)), playerInv));
		registerGui("carpenters_table_gui", () -> (playerInv, pos) -> new GuiCarpentersTable(playerInv, playerInv.player.world, pos));
		registerGui("bookshelf_cabinet_gui", () -> (playerInv, pos) -> new GuiBookshelf(new ContainerBookshelfCabinet(playerInv, (TileEntityBookshelf.Cabinet)playerInv.player.world.getTileEntity(pos)), playerInv));
	}

	private static void registerGui(String id, Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>> returnSupplier)
	{
		ResourceLocation resourceId = new ResourceLocation(Constants.MODID, id);

		if (!GUISUPPLIERS.containsKey(resourceId))
		{
			GUISUPPLIERS.put(resourceId, returnSupplier);
		}
		else
		{
			MinecraftExtended.LOGGER.error("Tried to register GuiScreen Supplier multiple times.");
		}
	}

	public static GuiScreen handleGuiRequest(FMLPlayMessages.OpenContainer openContainer)
	{
		ResourceLocation id = openContainer.getId();

		if (GUISUPPLIERS.containsKey(id))
		{
			Supplier<BiFunction<InventoryPlayer, BlockPos, GuiScreen>> returnSupplier = GUISUPPLIERS.get(id);
			EntityPlayerSP player = Minecraft.getInstance().player;
			BlockPos pos = openContainer.getAdditionalData().readBlockPos();

			if (returnSupplier != null && returnSupplier.get() != null)
			{
				return returnSupplier.get().apply(player.inventory, pos);
			}
		}

		return null;
	}

}
