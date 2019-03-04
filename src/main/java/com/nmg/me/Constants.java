package com.nmg.me;

public class Constants
{

	public static final String MODNAME = "Minecraft Extended";
	public static final String MODID = "pme";
	public static final String UPDATE_URL = "https://raw.githubusercontent.com/peter1745/MinecraftExtended/master/latest.json";

	public static class BlockStateFlags
	{

		public static final int BLOCK_UPDATE = 1;
		public static final int NOTIFY_CLIENTS = 2;
		public static final int NOTIFY_AND_UPDATE = BLOCK_UPDATE | NOTIFY_CLIENTS;
		public static final int DONT_RE_RENDER_IF_CLIENT = 4;
		public static final int FORCE_RE_RENDER_ON_MAIN_THREAD = 8;
		public static final int FORCE_RE_RENDER_MT_NOTIFY = NOTIFY_CLIENTS | FORCE_RE_RENDER_ON_MAIN_THREAD;
		public static final int PREVENT_OBSERVER_VISUAL_CHANGE = 16;

	}

}
