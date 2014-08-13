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

public class FPCOthell extends WarriorPC
{
	public final int SKILL_ANGEL_OF_DEATH 	= 10531;
	public final int SKILL_FINAL_UE			= 10520;
	public final int SKILL_MAXIMUM_BLOW		= 10560; // human
	public final int SKILL_ELUSIVE_MIRAGE	= 10558; // human
	public final int SKILL_MORTAL_STRIKE	= 410; // ELF, DE
	public final int SKILL_SHADOW_DODGE		= 10606; // ELF
	public final int SKILL_MELEE_REFLECT	= 10653; // DE
	public final int SKILL_CRITICAL_ADVEN	= 10562; // valliance, not done
	public final int SKILL_SHADOW_DASH		= 10525;
	public final int SKILL_SHADOW_HIDE		= 10517;
	public final int SKILL_EVASION_COUNTER	= 10524;
	
	public final int SKILL_DAGGER_EXPLOSION	= 10512;
	public final int SKILL_BLOOD_STAB		= 10508;
	public final int SKILL_HEART_BREAKER	= 10509;
	public final int SKILL_REVERSE			= 10511;
	public final int SKILL_CHAIN_BLOW		= 10510;
	public final int SKILL_RAZOR_RAIN		= 10513; // 
	public final int SKILL_CLONE_ATTACT		= 10532;
	public final int SKILL_MUG 				= 10700;
	public final int SKILL_PLUNDER			= 10702;

	public final int SKILL_CRITICAL_WOUND	= 531;
	public final int SKILL_THROW_DAGGER		= 10539; // damage, -spd
	public final int SKILL_POWER_BLUFF		= 10554;
	public final int SKILL_POISON_ZONE		= 10522;
	public final int SKILL_THROW_SAND		= 10540;
	public final int SKILL_SHADOW_CHASE		= 10516;
	public final int SKILL_DARK_PARALYSIS	= 10514;
	public final int SKILL_KICK				= 10549;
	public final int SKILL_UPPERCUT			= 10546; // lv 89

	public FPCOthell(Player actor)
	{
		super(actor);
	}

	@Override
	protected boolean thinkBuff()
	{
		if(thinkBuff(new int[] {
			SKILL_MORTAL_STRIKE
		}))
			return true;
		
		return super.thinkBuff();
	}
	
	@Override public List<Integer> getAllowSkill()
	{
		List<Integer> SkillList = new ArrayList<Integer>();
		
		
		// skill 4th
		SkillList.add(SKILL_BLOOD_STAB);
		
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
		
		Skill shadowChaseSkill = actor.getKnownSkill(SKILL_SHADOW_CHASE);
		Skill powerBluffSkill = actor.getKnownSkill(SKILL_POWER_BLUFF);
		Skill kickSkill = actor.getKnownSkill(SKILL_KICK);
		Skill darkParalysisSkill = actor.getKnownSkill(SKILL_DARK_PARALYSIS);
		Skill criticalWoundSkill = actor.getKnownSkill(SKILL_CRITICAL_WOUND);
		
		Skill heartBreakerSkill = actor.getKnownSkill(SKILL_HEART_BREAKER);
		Skill bloodStabSkill = actor.getKnownSkill(SKILL_BLOOD_STAB);
		Skill chainBlowSkill = actor.getKnownSkill(SKILL_CHAIN_BLOW);
		Skill reverseSkill = actor.getKnownSkill(SKILL_REVERSE);
		
		boolean alreadyDisable = false;
		
		// debuff
		if(canUseSkill(criticalWoundSkill, target, distance) && target.getEffectList().getEffectsCount(criticalWoundSkill) == 0)
			return chooseTaskAndTargets(criticalWoundSkill, target, distance);
		
		// disable
		if(target.getEffectList().getEffectsCount(shadowChaseSkill) > 0){
			debug("get shadowChaseSkill");
			alreadyDisable = true;
		}
		
		if(target.getEffectList().getEffectsCount(powerBluffSkill) > 0){
			debug("get powerBluffSkill");
			alreadyDisable = true;
		}
		
		if(target.getEffectList().getEffectsCount(kickSkill) > 0){
			debug("get kickSkill");
			alreadyDisable = true;
		}
		
		if(target.getEffectList().getEffectsCount(darkParalysisSkill) > 0){
			debug("get darkParalysisSkill");
			alreadyDisable = true;
		}
		
		if(!alreadyDisable){
			if (canUseSkill(shadowChaseSkill, target, distance))
				return chooseTaskAndTargets(shadowChaseSkill, target, distance);
			if (canUseSkill(powerBluffSkill, target, distance))
				return chooseTaskAndTargets(powerBluffSkill, target, distance);
			if (canUseSkill(kickSkill, target, distance))
				return chooseTaskAndTargets(kickSkill, target, distance);
			if (canUseSkill(darkParalysisSkill, target, distance))
				return chooseTaskAndTargets(darkParalysisSkill, target, distance);
		}
		
		// combo kick -> heartbreaker
		if(target.getEffectList().getEffectsCount(kickSkill) > 0 && canUseSkill(heartBreakerSkill, target, distance))
			return chooseTaskAndTargets(heartBreakerSkill, target, distance);
		
		// combo bloodstab -> chainblow
		if(target.getEffectList().getEffectsCount(bloodStabSkill) > 0 && canUseSkill(chainBlowSkill, target, distance))
			return chooseTaskAndTargets(chainBlowSkill, target, distance);
		
		if(canUseSkill(bloodStabSkill, target, distance))
			return chooseTaskAndTargets(bloodStabSkill, target, distance);
		
		if(canUseSkill(reverseSkill, target, distance))
			return chooseTaskAndTargets(reverseSkill, target, distance);
		
//		if(target.isMonster())
//			return false;
		
		// TODO make treatment and buff friendly targets
		
		return chooseTaskAndTargets(null, target, distance);
	}
	
}

