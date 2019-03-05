package com.nmg.me.init;

import com.mojang.brigadier.CommandDispatcher;
import com.nmg.me.commands.DimensionTeleportCommand;
import net.minecraft.command.CommandSource;

public class MECommands
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		DimensionTeleportCommand.register(dispatcher);
	}

}
