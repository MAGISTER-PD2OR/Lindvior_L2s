package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class SummonerPC extends EventFPC
{
	public SummonerPC(Player actor)
	{
		super(actor);
	}
	
	@Override
	protected void makeNpcBuffs()
	{
		npcBuff( 15649, 1 ); // Warrior Harmony
		basicNpcBuffs();
	}

	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		
		runAwayFromTarget(attacker);
	
	}

	@Override
	protected boolean createNewTask()
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