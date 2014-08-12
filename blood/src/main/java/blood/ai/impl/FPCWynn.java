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
import l2s.gameserver.model.Skill.SkillType;

public class FPCWynn extends SummonerPC
{
	public final int SKILL_MARK_OF_VOID 	= 11260;
	public final int SKILL_MARK_OF_WEAKNESS = 11259;
	public final int SKILL_MARK_OF_TRICK	= 11262;
	public final int SKILL_MARK_OF_PLAGUE	= 11261;
	public final int SKILL_RETRIVE_MARK		= 11271;
	
	public final int SKILL_EXILE			= 11273;
	
	public final int SKILL_SERVITOR_GHASTE	= 11347;
	public final int SKILL_SERVITOR_GDW		= 11348;
	public final int SKILL_SERVITOR_GMIGHT	= 11349;
	
	public final int SKILL_SERVITOR_GIANTS_BLESSING = 11297;
	public final int SKILL_SERVITOR_UD		= 11310;
	public final int SKILL_SERVITOR_BLESSING= 11309;
	public final int SKILL_SERVITOR_EMPOWER	= 11306;
	public final int SKILL_SERVITOR_MIGHT	= 11307;
	public final int SKILL_SERVITOR_WINDWALK= 11308;
	public final int SKILL_SERVITOR_HASTE	= 11304;
	public final int SKILL_SERVITOR_BARRIER = 11303;
	public final int SKILL_SERVITOR_SHIELD	= 11305;
	public final int SKILL_ULTIMATE_TPAIN	= 11270;
	public final int SKILL_TPAIN			= 1262;
	public final int SKILL_ULTIMATE_SHARE	= 11288;
	
	public final int SKILL_SUMMON_KAI		= 11320;
	public final int SKILL_SUMMON_KING		= 11321;
	public final int SKILL_SUMMON_QUEEN		= 11322;
	public final int SKILL_SUMMON_MERROW	= 11329;
	public final int SKILL_SUMMON_NIGHTSHADE= 11338;
	
	public final int SKILL_SERVITOR_HEAL	= 11302;
	public final int SKILL_SERVITOR_MASS_HEAL	= 11269;
	
	public final int SKILL_AVENGING_CUBIC 	= 11268;
	public final int[] SKILL_SMART_CUBIC = new int[] {779, 780, 781, 782, 783};
	
	public FPCWynn(Player actor)
	{
		super(actor);
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		// skill 4th
		SkillList.add(SKILL_SUMMON_KAI);
		SkillList.add(SKILL_SUMMON_MERROW);
		SkillList.add(SKILL_SUMMON_NIGHTSHADE);
		
		SkillList.add(SKILL_MARK_OF_VOID);
		SkillList.add(SKILL_MARK_OF_PLAGUE);
		SkillList.add(SKILL_MARK_OF_TRICK);
		SkillList.add(SKILL_MARK_OF_WEAKNESS);
		
		return SkillList;
	}
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			SKILL_ULTIMATE_SHARE,
			SKILL_AVENGING_CUBIC,
			SKILL_TPAIN
		}))
			return true;
		
//		if(thinkBuff(getUniqueSkill(SKILL_SMART_CUBIC)))
//			return true;
		
//		if(thinkBuff(SKILL_AVENGING_CUBIC))
//			return true;

		Player actor = getActor();
		Skill skillUniqueBuff = getUniqueSkill(new int[]{
			SKILL_SERVITOR_GHASTE,
			SKILL_SERVITOR_GDW,
			SKILL_SERVITOR_GMIGHT
		});
		
		for(Servitor servitor: actor.getServitors())
		{
			if(servitor.getEffectList().getEffectsCount(skillUniqueBuff) == 0 
					&& chooseTaskAndTargets(skillUniqueBuff, servitor, 0))
			{
				return true;
			}
		}
		
		return super.thinkBuff();
	}

	@Override
	protected boolean thinkSummon()
	{
		Skill bestSummon = getUniqueSkill(new int[]{
				SKILL_SUMMON_QUEEN,
				SKILL_SUMMON_MERROW,
				SKILL_SUMMON_NIGHTSHADE
		});
		
		if(thinkSummon(bestSummon))
			return true;
		
		return false;
	}
	
	@Override
	protected boolean thinkSummon(Skill skill)
	{
		if(skill == null || skill.getSkillType() != SkillType.SUMMON)
			return false;
		
		Player actor 	= getActor();
		
		if(actor.getServitors().length < 2  && actor.getCurrentMp() > 300) /* FIXME */
		{
			chooseTaskAndTargets(skill, actor, 0);
			return true;
		}
		
		return false;
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
		
		boolean doHeal = false;
		boolean doUD = false;
		
		// TODO force servitor use skill
		if (actor.getServitors().length > 0){
			for (Servitor summon: actor.getServitors())
			{
				summon.getAI().Attack(target, true, false);
				if(summon.getCurrentHpPercents() < 80){
					debug("doHeal please");
					doHeal = true;
					tryCastSkill(SKILL_SERVITOR_HEAL, summon);
				}
					
				if(summon.getCurrentHpPercents() < 40){
					debug("doUD please");
					doUD = true;
				}
					
			}
		}
		
		double distance = actor.getDistance(target);
		double targetHp = target.getCurrentHpPercents();
		double actorHp = actor.getCurrentHpPercents();
		
		
		
		Skill skillMarkOfVoid = actor.getKnownSkill(SKILL_MARK_OF_VOID);
		Skill skillMarkOfWeakness = actor.getKnownSkill(SKILL_MARK_OF_WEAKNESS);
		Skill markOfTrickSkill = actor.getKnownSkill(SKILL_MARK_OF_TRICK);
		Skill markOfPlagueSkill = actor.getKnownSkill(SKILL_MARK_OF_PLAGUE);
		Skill retriveMarkSkill = actor.getKnownSkill(SKILL_RETRIVE_MARK);
		Skill skillUD = actor.getKnownSkill(SKILL_SERVITOR_UD);
		Skill healSkill = actor.getKnownSkill(SKILL_SERVITOR_MASS_HEAL);
		Skill exileSkill = actor.getKnownSkill(SKILL_EXILE);
		
		int markCount = 0;
		
		if(doUD && canUseSkill(exileSkill, target, distance))
			return chooseTaskAndTargets(exileSkill, target, distance);
		
		if(doUD && canUseSkill(skillUD, target))
			return chooseTaskAndTargets(skillUD, target, distance);
		
		if(doHeal && canUseSkill(healSkill, target))
			return chooseTaskAndTargets(healSkill, target, distance);
		
		if(distance > 1200 && tryMoveToTarget(target, 1000))
		{
			debug("so far, should move:"+distance);
			return false;
		}
		
		if(canUseSkill(skillMarkOfVoid, target, distance) && target.getEffectList().getEffectsCount(skillMarkOfVoid) == 0)
			return chooseTaskAndTargets(skillMarkOfVoid, target, distance);
		
		if(canUseSkill(skillMarkOfWeakness, target, distance) && target.getEffectList().getEffectsCount(skillMarkOfWeakness) == 0)
			return chooseTaskAndTargets(skillMarkOfWeakness, target, distance);
		
		if(canUseSkill(markOfPlagueSkill, target, distance) && target.getEffectList().getEffectsCount(markOfPlagueSkill) == 0)
			return chooseTaskAndTargets(markOfPlagueSkill, target, distance);
		
		if(canUseSkill(markOfTrickSkill, target, distance) && target.getEffectList().getEffectsCount(markOfTrickSkill) == 0)
			return chooseTaskAndTargets(markOfTrickSkill, target, distance);
		
		if(target.getEffectList().getEffectsCount(markOfPlagueSkill) > 0)
			markCount++;
		if(target.getEffectList().getEffectsCount(markOfTrickSkill) > 0)
			markCount++;
		if(target.getEffectList().getEffectsCount(skillMarkOfWeakness) > 0)
			markCount++;
		
		if(markCount > 1 && canUseSkill(retriveMarkSkill, target, distance))
			return chooseTaskAndTargets(retriveMarkSkill, target, distance);
		
		if(target.isMonster())
			return false;
		
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

