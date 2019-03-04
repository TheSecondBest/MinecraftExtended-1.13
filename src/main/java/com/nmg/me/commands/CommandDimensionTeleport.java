package com.nmg.me.commands;

import com.nmg.me.init.MEWorldProviders;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.world.METeleporter;
import com.nmg.me.world.storage.WorldStorageSavedData;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandDimensionTeleport// extends CommandBase
{
	/*private final List<String> aliases;

	public CommandDimensionTeleport()
	{
		 this.aliases = new ArrayList<>();
		 this.aliases.add("tpd");
	}

	@Override
	public String getName()
	{
		return "tpdim";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "commands.tpdim.usage";
	}

	@Override
	public List<String> getAliases()
	{
		return this.aliases;
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (args.length < 1)
			throw new WrongUsageException("commands.tpdim.usage");

		String s = args[0];
		int dimId;

		if (MEUtils.isInteger(s))
		{
			try
			{
				dimId = Integer.parseInt(s);
			} catch (NumberFormatException e)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid dimension id"));
				return;
			}
		}
		else
		{
			dimId = DimensionType.byName(s).getId(); // Try to get dimension by name
		}

		if (sender instanceof EntityPlayer)
		{
			BlockPos pos = sender.getPosition();
			if (dimId == MEWorldProviders.dimIdStorage)
			{
				WorldStorageSavedData worldStorageSavedData = WorldStorageSavedData.get(server.getEntityWorld());
				worldStorageSavedData.addDimensionPos((EntityPlayer) sender, 0);
				pos = worldStorageSavedData.getDimensionPos((EntityPlayer) sender, MEWorldProviders.dimIdStorage);
			}

			METeleporter.teleportToDimension((EntityPlayer)sender, dimId, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
	{
		if (args.length == 1)
		{
			List<String> dimensionNames = new ArrayList<>();

			for (DimensionType dim : DimensionType.values())
			{
				dimensionNames.add(dim.getName());
			}

			return getListOfStringsMatchingLastWord(args, dimensionNames);
		}

		return Collections.emptyList();
	}*/
}
