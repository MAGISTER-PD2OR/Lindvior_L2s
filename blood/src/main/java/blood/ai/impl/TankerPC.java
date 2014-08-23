package blood.ai.impl;

import java.util.ArrayList;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.tables.SkillTable;
import blood.ai.EventFPC;

public class TankerPC extends EventFPC
{
	public TankerPC(Player actor)
	{
		super(actor);
	}

	@Override
	public int getRatePHYS()
	{
		return 100;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 30;
	}

	@Override
	public int getRateDAM()
	{
		return 30;
	}

	@Override
	public int getRateSTUN()
	{
		return 100;
	}

	@Override
	public int getRateBUFF()
	{
		return 30;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}