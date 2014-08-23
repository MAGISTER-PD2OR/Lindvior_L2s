package blood.ai.impl;

import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import blood.ai.EventFPC;

public class HealerPC extends EventFPC
{
	public HealerPC(Player actor)
	{
		super(actor);
		
	}
	
	protected Skill getNpcSuperiorBuff()
	{
//		return getSkill(15648, 1); //tank
//		return getSkill(15649, 1); //warrior
		return getSkill(15650, 1); //wizzard
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
		return 60;
	}

	@Override
	public int getRateDAM()
	{
		return 50;
	}

	@Override
	public int getRateSTUN()
	{
		return 0;
	}

	@Override
	public int getRateBUFF()
	{
		return 25;
	}

	@Override
	public int getRateHEAL()
	{
		return 90;
	}
	
}

