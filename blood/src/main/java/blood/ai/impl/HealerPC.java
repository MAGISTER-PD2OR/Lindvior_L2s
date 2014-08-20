package blood.ai.impl;

import java.util.ArrayList;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import blood.ai.EventFPC;

public class HealerPC extends EventFPC
{
	public HealerPC(Player actor)
	{
		super(actor);
		
	}
	
	@Override
	protected void makeNpcBuffs()
	{
		npcBuff( 15650, 1 ); // Wizard Harmony
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
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		
		//check target critical level, base on HP level
		int hpLevel 					= (int) actor.getCurrentHpPercents();
		ArrayList<Integer>	SkillList;
		//take action
		if(hpLevel < 50)
		{
			SkillList = getCriticalHealSkill();
			if(SkillList != null)
				tryCastSkill(SkillList.get(Rnd.get(SkillList.size())), actor);
		}
		else if(hpLevel < 80)
		{
			SkillList = getNormalHealSkill();
			if(SkillList != null)
				tryCastSkill(SkillList.get(Rnd.get(SkillList.size())), actor);
		}
	}
	
	@Override
	protected void onEvtClanAttacked(Creature attacked, Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		if(!attacked.isInRange(actor.getLoc(), 600)) 
			return; 
		
		//check target critical level, base on HP level
		int hpLevel 					= (int) attacked.getCurrentHpPercents();
		ArrayList<Integer>	SkillList;
		//take action
		if(hpLevel < 30)
		{
			SkillList = getCriticalHealSkill();
			if(SkillList != null)
				tryCastSkill(SkillList.get(Rnd.get(SkillList.size())), attacked);
		}
		else if(hpLevel < 60)
		{
			SkillList = getNormalHealSkill();
			if(SkillList != null)
				tryCastSkill(SkillList.get(Rnd.get(SkillList.size())), attacked);
		}
	}
	
	protected ArrayList<Integer> getCriticalHealSkill()
	{
		return null;
		
	}
	
	protected ArrayList<Integer> getNormalHealSkill()
	{
		return null;
	}
}

