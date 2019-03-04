package com.nmg.me.proxy;

public class ServerProxy implements IProxy
{
	@Override
	public boolean isSinglePlayer()
	{
		return false;
	}

	@Override
	public boolean isDedicatedServer()
	{
		return true;
	}
}
