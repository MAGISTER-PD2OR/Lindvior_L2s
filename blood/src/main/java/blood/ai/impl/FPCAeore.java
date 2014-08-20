package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Party;
import l2s.gameserver.model.Player;

public class FPCAeore extends HealerPC
{
	public final int SKILL_RESISTANCE_OF_SAHA 	= 11824;
	public final int SKILL_CLARITY_OF_SAHA		= 11825;
	public final int SKILL_DIVINE_PRAYER		= 11851;
	
	public final int SKILL_SUMMON_LUMI			= 11776;
	public final int SKILL_SUMMON_TREE			= 11774;
	
	public final int SKILL_DARK_BACKFIRE		= 11769;
	public final int SKILL_MARK_LUMI			= 11777;
	public final int SKILL_FATAL_SLEEP			= 11778;
	public final int SKILL_MASS_FATAL_SLEEP		= 11832;
	
	public final int SKILL_SUSTAIN				= 11752;
	public final int SKILL_FAIRY_OF_LIFE		= 11754;
	public final int SKILL_RADIANT_HEAL			= 11755;
	public final int SKILL_PANIC_HEAL			= 11756;
	public final int SKILL_BRILLIANT_HEAL		= 11757; // party
	public final int SKILL_RADIANT_RECHARGE		= 11760; // mp
	public final int SKILL_REBIRTH				= 11768; // party full recover - 10mins
	public final int SKILL_GIANT_FLAVOR			= 11772; // buff ud - 10 mins
	public final int SKILL_BLESS_RES			= 11784;
	
	
	public FPCAeore(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			SKILL_RESISTANCE_OF_SAHA,
			SKILL_CLARITY_OF_SAHA,
			SKILL_DIVINE_PRAYER
		}))
			return true;
		
		return super.thinkBuff();
	}
	
//	@Override
//	protected boolean thinkSummon()
//	{
//		if(thinkSummon(SKILL_SUMMON_LUMI))
//			return true;
//		
//		return false;
//	}
	
	@Override
	protected void makeNpcBuffs()
	{
		npcBuff( 15649, 1 ); // Warrior Harmony
		super.makeNpcBuffs();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		// skill 4th
		SkillList.add(SKILL_SUMMON_LUMI);
		
		return SkillList;
	}
	
	@Override
	public void thinkActive()
	{
		Player actor = getActor();
		
		Party party = actor.getParty();
		
		if(party != null)
		{
			for(Player member: party.getPartyMembers())
			{
				if(member.isDead())
				{
					if(actor.getDistance(member) > 1000)
						tryMoveToTarget(member, 600);
					tryCastSkill(SKILL_BLESS_RES, member);
				}
				else if((int) member.getCurrentHpPercents() < 60 && member.isInRange(actor.getLoc(), 1000))
				{
					tryCastSkill(SKILL_SUSTAIN, member);
					tryCastSkill(SKILL_RADIANT_HEAL, member);
				}
			}
		}
		super.thinkActive();
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		
		//check target critical level, base on HP level
		int hpLevel 					= (int) actor.getCurrentHpPercents();
		//take action
		if(hpLevel < 50)
		{
			tryCastSkill(SKILL_SUSTAIN, actor);
			tryCastSkill(SKILL_RADIANT_HEAL, actor);
		}
		else if(hpLevel < 80)
		{
			tryCastSkill(SKILL_SUSTAIN, actor);
			tryCastSkill(SKILL_FAIRY_OF_LIFE, actor);
		}
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
		//take action
		if(hpLevel < 50)
		{
			tryCastSkill(SKILL_SUSTAIN, actor);
			tryCastSkill(SKILL_RADIANT_HEAL, actor);
		}
		else if(hpLevel < 80)
		{
			tryCastSkill(SKILL_SUSTAIN, actor);
			tryCastSkill(SKILL_FAIRY_OF_LIFE, actor);
		}
	}
	
	
	
}

