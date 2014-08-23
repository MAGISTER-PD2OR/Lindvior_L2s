package blood.ai.impl;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import blood.ai.EventFPC;

public class SummonerPC extends EventFPC
{
	public SummonerPC(Player actor)
	{
		super(actor);
		_allowSkills.add(1281);
	}
	
	protected Skill getNpcSuperiorBuff()
	{
		return getSkill(15649, 1); //warrior
	}

	@Override
	protected boolean createFightTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
		return 0;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 50;
	}

	@Override
	public int getRateDAM()
	{
		return 100;
	}

	@Override
	public int getRateSTUN()
	{
		return 0;
	}

	@Override
	public int getRateBUFF()
	{
		return 0;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}