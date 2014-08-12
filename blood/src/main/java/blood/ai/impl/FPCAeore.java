package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.commons.math.random.RndSelector;
import l2s.commons.util.Rnd;
import l2s.gameserver.ai.CtrlIntention;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Servitor;
import l2s.gameserver.model.Skill;

public class FPCAeore extends HealerPC
{
	public final int SKILL_RESISTANCE_OF_SAHA 	= 11824;
	public final int SKILL_CLARITY_OF_SAHA		= 11825;
	public final int SKILL_DIVINE_PRAYER		= 11851;
	
	public final int SKILL_SUMMON_LUMI			= 11776;
	
	public final int SKILL_DARK_BACKFIRE		= 11769;
	public final int SKILL_MARK_LUMI			= 11777;
	public final int SKILL_FATAL_SLEEP			= 11778;
	public final int SKILL_MASS_FATAL_SLEEP		= 11832;
	
	public final int SKILL_SUSTAIN				= 11752;
	public final int SKILL_FAIRY_OF_LIFE		= 11754;
	
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
	
	@Override
	protected boolean thinkSummon()
	{
		if(thinkSummon(SKILL_SUMMON_LUMI))
			return true;
		
		return false;
	}
	
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
	
	protected boolean defaultFightTask()
	{
		clearTasks();
		
		Player actor = getActor();
		if (actor.isDead() || actor.isAMuted())
		{
			return false;
		}
		
		Creature target;
		if ((target = prepareTarget()) == null)
		{
			debug("dont have target, try to think active again");
			setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
			return false;
		}
		
		double distance = actor.getDistance(target);
		double targetHp = target.getCurrentHpPercents();
		double actorHp = actor.getCurrentHpPercents();
		
		Skill[] dam = Rnd.chance(getRateDAM()) ? selectUsableSkills(target, distance, _damSkills) : null;
		Skill[] dot = Rnd.chance(getRateDOT()) ? selectUsableSkills(target, distance, _dotSkills) : null;
		Skill[] debuff = targetHp > 10 ? Rnd.chance(getRateDEBUFF()) ? selectUsableSkills(target, distance, _debuffSkills) : null : null;
		Skill[] stun = Rnd.chance(getRateSTUN()) ? selectUsableSkills(target, distance, _stunSkills) : null;
		Skill[] heal = actorHp < 50 ? Rnd.chance(getRateHEAL()) ? selectUsableSkills(actor, 0, _healSkills) : null : null;
		Skill[] buff = Rnd.chance(getRateBUFF()) ? selectUsableSkills(actor, 0, _buffSkills) : null;
		
		RndSelector<Skill[]> rnd = new RndSelector<Skill[]>();
		if (!actor.isAMuted())
		{
			rnd.add(null, getRatePHYS());
		}
		
		if(dam != null && dam.length > 0)
			rnd.add(dam, getRateDAM());
		
		if(dot != null && dot.length > 0)
		rnd.add(dot, getRateDOT());
		
		if(debuff != null && debuff.length > 0)
		rnd.add(debuff, getRateDEBUFF());
		
		if(heal != null && heal.length > 0)
		rnd.add(heal, getRateHEAL());
		
		if(buff != null && buff.length > 0)
		rnd.add(buff, getRateBUFF());
		
		if(stun != null && stun.length > 0)
		rnd.add(stun, getRateSTUN());
		
		Skill[] selected = rnd.select();
		if (selected != null)
		{
			if ((selected == dam) || (selected == dot))
			{
				return chooseTaskAndTargets(selectTopSkillByDamage(actor, target, distance, selected), target, distance);
			}
			
			if ((selected == debuff) || (selected == stun))
			{
				return chooseTaskAndTargets(selectTopSkillByDebuff(actor, target, distance, selected), target, distance);
			}
			
			if (selected == buff)
			{
				return chooseTaskAndTargets(selectTopSkillByBuff(actor, selected), actor, distance);
			}
			
			if (selected == heal)
			{
				return chooseTaskAndTargets(selectTopSkillByHeal(actor, selected), actor, distance);
			}
		}
		
		// TODO make treatment and buff friendly targets
		
		return chooseTaskAndTargets(null, target, distance);
	}
	
	
	
}

