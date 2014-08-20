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
	
	public final int SKILL_CRYSTAL_REGENERATION = 11765;
	
	
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
	
//	@Override
//	protected void makeNpcBuffs()
//	{
//		npcBuff( 15649, 1 ); // Warrior Harmony
//		super.makeNpcBuffs();
//	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		// skill 4th
		SkillList.add(SKILL_SUMMON_LUMI);
		
		return SkillList;
	}
	
	public boolean hasEffect(Creature target, int skillId)
	{
		return target.getEffectList().containsEffects(skillId);
	}
	
	public void partyBuff(Creature target)
	{
		if(canUseSkill(SKILL_RESISTANCE_OF_SAHA, target) && !hasEffect(target, SKILL_RESISTANCE_OF_SAHA))
			tryCastSkill(SKILL_RESISTANCE_OF_SAHA, target);
		if(canUseSkill(SKILL_CLARITY_OF_SAHA, target) && !hasEffect(target, SKILL_CLARITY_OF_SAHA))
			tryCastSkill(SKILL_CLARITY_OF_SAHA, target);
		if(canUseSkill(SKILL_DIVINE_PRAYER, target) && !hasEffect(target, SKILL_DIVINE_PRAYER))
			tryCastSkill(SKILL_DIVINE_PRAYER, target);
	}
	
	public void normalHealTarget(Creature target)
	{
		if(canUseSkill(SKILL_CRYSTAL_REGENERATION, target) && !hasEffect(target, SKILL_CRYSTAL_REGENERATION))
			tryCastSkill(SKILL_SUSTAIN, target);
		if(canUseSkill(SKILL_FAIRY_OF_LIFE, target) && !hasEffect(target, SKILL_FAIRY_OF_LIFE))
			tryCastSkill(SKILL_FAIRY_OF_LIFE, target);
	}
	
	public void criticalHealTarget(Creature target)
	{
		if(canUseSkill(SKILL_CRYSTAL_REGENERATION, target) && !hasEffect(target, SKILL_CRYSTAL_REGENERATION))
			tryCastSkill(SKILL_SUSTAIN, target);
		if(canUseSkill(SKILL_RADIANT_HEAL, target) && !hasEffect(target, SKILL_RADIANT_HEAL))
			tryCastSkill(SKILL_RADIANT_HEAL, target);
	}
	
	public void normalRechargeTarget(Creature target)
	{
		if(canUseSkill(SKILL_RADIANT_RECHARGE, target) && !hasEffect(target, SKILL_RADIANT_RECHARGE))
			tryCastSkill(SKILL_RADIANT_RECHARGE, target);
	}
	
	public void selfRegenMp()
	{
		Player actor = getActor(); 
		
		if(actor.getCurrentMpPercents() < 80 && canUseSkill(SKILL_CRYSTAL_REGENERATION, actor))
		{
			tryCastSkill(SKILL_CRYSTAL_REGENERATION, actor);
		}
	}
	
	@Override
	public void healerThinkActive()
	{
		Player actor = getActor();
		
		selfRegenMp();
		
		Party party = actor.getParty();
		
		double lowestHpPercent = 100d;
		Player lowestHpMember = null;
		double lowestMpPercent = 100d;
		Player lowestMpMember = null;
		int normalLostHpMember = 0;
		int normalLostMpMember = 0;
		int criticalLostHpMember = 0;
		int criticalLostMpMember = 0;
		
		if(party != null)
		{
			for(Player member: party.getPartyMembers())
			{
				double currentMemberHpPercent = member.getCurrentHpPercents(); 
				double currentMemberMpPercent = member.getCurrentMpPercents();
				
				if(currentMemberHpPercent < 50)
					criticalLostHpMember++;
				
				if(currentMemberHpPercent < 80)
					normalLostHpMember++;
				
				if(currentMemberMpPercent < 50)
					criticalLostMpMember++;
				
				if(currentMemberMpPercent < 80)
					normalLostMpMember++;
				
				if(member == actor)
					continue;
				
				partyBuff(member);
				if(currentMemberHpPercent < lowestHpPercent)
				{
					lowestHpPercent = currentMemberHpPercent;
					lowestHpMember = member;
				}
				
				if(currentMemberMpPercent < lowestMpPercent)
				{
					lowestMpPercent = currentMemberMpPercent;
					lowestMpMember = member;
				}
			}
			
			if(canUseSkill(SKILL_REBIRTH, actor) && (criticalLostHpMember + criticalLostHpMember) > 5 || (normalLostHpMember + normalLostMpMember) > 10)
				tryCastSkill(SKILL_REBIRTH, actor);
			else if(lowestHpMember != null && lowestHpPercent < 50)
				criticalHealTarget(lowestHpMember);
			else if(lowestHpMember != null && lowestHpPercent < 80)
				normalHealTarget(lowestHpMember);
			else if(lowestMpMember != null && lowestMpPercent < 80)
				normalRechargeTarget(lowestHpMember);
				
		}
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		
		//check target critical level, base on HP level
		double hpLevel = actor.getCurrentHpPercents();
		//take action
		if(hpLevel < 50)
		{
			criticalHealTarget(actor);
		}
		else if(hpLevel < 80)
		{
			normalHealTarget(actor);
		}
	}
	
	@Override
	protected void onEvtClanAttacked(Creature attacked, Creature attacker, int damage)
	{
		Player actor = getActor();
		//check if target is in 1000 range
		if(!attacked.isInRange(actor.getLoc(), 1500)) 
			return; 
		
		//check target critical level, base on HP level
		double hpLevel 					= attacked.getCurrentHpPercents();
		//take action
		if(hpLevel < 50)
		{
			criticalHealTarget(attacked);
		}
		else if(hpLevel < 80)
		{
			normalHealTarget(attacked);
		}
	}
	
	
	
}

