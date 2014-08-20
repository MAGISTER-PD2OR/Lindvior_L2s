package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class WarriorPC extends EventFPC
{
	public WarriorPC(Player actor)
	{
		super(actor);
	}
	
	@Override
	protected void makeNpcBuffs()
	{
		npcBuff( 15649, 1 ); // Warriors Harmony
		basicNpcBuffs();
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return 50;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 20;
	}

	@Override
	public int getRateDAM()
	{
		return 90;
	}

	@Override
	public int getRateSTUN()
	{
		return 50;
	}

	@Override
	public int getRateBUFF()
	{
		return 80;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}