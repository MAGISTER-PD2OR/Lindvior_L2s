package blood.ai.impl;

import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class FighterPC extends EventFPC
{
	public FighterPC(Player actor)
	{
		super(actor);
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