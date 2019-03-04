package com.nmg.me.proxy;

public interface IProxy
{

	default void preInit() {}
	default void init() {}

	boolean isSinglePlayer();
	boolean isDedicatedServer();

}
