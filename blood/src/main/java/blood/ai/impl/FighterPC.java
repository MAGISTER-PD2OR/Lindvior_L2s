package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import blood.ai.EventFPC;
import l2s.gameserver.model.Player;

public class FighterPC extends EventFPC
{
	public FighterPC(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return 30;
	}

	@Override
	public int getRateDOT()
	{
		return 20;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 20;
	}

	@Override
	public int getRateDAM()
	{
		return 15;
	}

	@Override
	public int getRateSTUN()
	{
		return 30;
	}

	@Override
	public int getRateBUFF()
	{
		return 10;
	}

	@Override
	public int getRateHEAL()
	{
		return 20;
	}
	
}