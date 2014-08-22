package blood.ai.impl;

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
	
	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			SKILL_ULTIMATE_SHARE,
			SKILL_AVENGING_CUBIC,
			SKILL_TPAIN
		}))
			return true;
		
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
	protected int getMaxSummon()
	{
		return 2;
	}
	
	protected boolean fightTaskByClass(Creature target)
	{
		wynnFightTask(target);
		return true;
	}
	
	protected boolean wynnFightTask(Creature target)
	{
		Player actor = getActor();
		
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
//		double targetHp = target.getCurrentHpPercents();
//		double actorHp = actor.getCurrentHpPercents();
		
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
		
		return false;
	}
	
}

