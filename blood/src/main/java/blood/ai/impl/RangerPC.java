package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.stats.Stats;
import blood.ai.EventFPC;

public class RangerPC extends EventFPC
{
	public RangerPC(Player actor)
	{
		super(actor);
	}
	
	protected Skill getNpcSuperiorBuff()
	{
//		return getSkill(15648, 1); //tank
		return getSkill(15649, 1); //warrior
//		return getSkill(15650, 1); //wizzard
	}


	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
		
		runAwayFromTarget(attacker);
		
		Player actor = getActor();
		int hpLevel	= (int) actor.getCurrentHpPercents();
		
		if(hpLevel < 60)
		{
			try
			{
				//cast Ultimate Evasion
				tryCastSkill(111, actor);
			}
			catch(Exception e){}
		}
	}
	
	protected int getReuseDelay(Creature target)
	{
		Player actor = getActor();
		try{
			return (int) (actor.getActiveWeaponTemplate().getAttackReuseDelay() * actor.getReuseModifier(target) * 666 * actor.calcStat(Stats.POWER_ATTACK_SPEED, 0, target, null) / 293. / actor.getPAtkSpd());
		}catch(Exception e){
			return 0;
		}
		//return 0;
	}

	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
	}

	@Override
	public int getRatePHYS()
	{
			
		return 95;
	}

	@Override
	public int getRateDOT()
	{
		return 0;
	}

	@Override
	public int getRateDEBUFF()
	{
		return 0;
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
		return 0;
	}

	@Override
	public int getRateHEAL()
	{
		return 0;
	}
	
}