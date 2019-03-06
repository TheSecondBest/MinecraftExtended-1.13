package com.nmg.me.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.nmg.me.MinecraftExtended;
import com.nmg.me.init.MEWorldProviders;
import com.nmg.me.utils.MEUtils;
import com.nmg.me.world.METeleporter;
import com.nmg.me.world.storage.WorldStorageSavedData;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.DimensionArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DimensionTeleportCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> literalArgumentBuilder = Commands.literal("tpd").requires((commandSource -> {
			return commandSource.hasPermissionLevel(2);
		}));

		for (DimensionType dimensionType : DimensionType.func_212681_b())
		{
			literalArgumentBuilder.then(Commands.literal(dimensionType.toString()).executes((command -> {
				return doTeleport(command, Collections.singleton(command.getSource().asPlayer()), dimensionType);
			})).then(Commands.argument("target", EntityArgument.multiplePlayers())).executes((command) -> {
				return doTeleport(command, EntityArgument.getPlayers(command, "target"), dimensionType);
			}));
		}

		dispatcher.register(literalArgumentBuilder);
	}

	private static void sendDimensionTeleportFeedback(CommandSource source, EntityPlayerMP player, DimensionType dimensionType)
	{
		if (source.getEntity() == player)
		{
			source.sendFeedback(new TextComponentTranslation("commands.tpd.success.self", dimensionType.getRegistryName().toString()), true);
		}
		else
		{
			if (source.getWorld().getGameRules().getBoolean("sendCommandFeedback"))
			{
				player.sendMessage(new TextComponentTranslation("dimension.changed", dimensionType.getRegistryName().toString()));
			}

			source.sendFeedback(new TextComponentTranslation("commands.tpd.success.other", player.getDisplayName(), dimensionType.getRegistryName().toString()), true);
		}
	}

	private static int doTeleport(CommandContext<CommandSource> source, Collection<EntityPlayerMP> players, DimensionType dimensionType)
	{
		int i = 0;

		for (EntityPlayerMP player : players)
		{
			if (player.getServerWorld().getDimension().getType() != dimensionType)
			{
				BlockPos pos = player.getPosition();
				METeleporter.teleportToDimension(player, dimensionType, pos);
				sendDimensionTeleportFeedback(source.getSource(), player, dimensionType);
				++i;
			}
		}

		return i;
	}

	/*private final List<String> aliases;

	public DimensionTeleportCommand()
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
