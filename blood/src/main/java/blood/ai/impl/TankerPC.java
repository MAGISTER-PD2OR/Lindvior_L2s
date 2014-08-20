package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

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
	protected void makeNpcBuffs()
	{
		npcBuff( 15648, 1 ); // Tanker Harmony
		basicNpcBuffs();
	}
	
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {82, 112})) // Majesty, Deflect Arrow
			return true;
		
		return super.thinkBuff();
	}

	@Override
	protected void onEvtClanAttacked(Creature attacked, Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		if(!attacked.isInRange(actor.getLoc(), 1000)) 
			return; 
		
		//check target critical level, base on HP level
		int hpLevel 					= (int) attacked.getCurrentHpPercents();
		ArrayList<Integer>	SkillList;
		//take action
		if(hpLevel < 80)
		{
			SkillList = getDrawTargetSkill();
			if(Rnd.chance(10) && SkillList != null)
				tryCastSkill(SkillList.get(Rnd.get(SkillList.size())), attacked);
		}
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
		
		if(hpLevel < 40)
		{
			try
			{
				//cast Ultimate Defense
				actor.doCast(SkillTable.getInstance().getInfo(110, 1), actor, false);
			}
			catch(Exception e){}
		}
	}
	
	protected ArrayList<Integer> getDrawTargetSkill()
	{
		return null;
	}
	
	@Override
	protected boolean createNewTask()
	{
		return defaultFightTask();
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