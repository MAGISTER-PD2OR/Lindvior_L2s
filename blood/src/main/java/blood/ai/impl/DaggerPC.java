package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class DaggerPC extends EventFPC
{
	public DaggerPC(Player actor)
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
		return 90;
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
	

	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		Player actor = getActor();
		if(actor.isDead() || attacker == null || actor.getDistance(attacker) > 700)
			return;

		if(actor.isMoving)
			return;
		
		int hpLevel	= (int) actor.getCurrentHpPercents();
		
		if(hpLevel < 60)
		{
			try
			{
				//cast Ultimate Evasion
				thinkBuff(111);
				
				//cast Switch
				tryCastSkill(12, attacker);
				
				//cast Trick
				tryCastSkill(11, attacker);
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
}