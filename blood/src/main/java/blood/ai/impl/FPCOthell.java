package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;

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
	
	protected boolean defaultSubFightTask(Creature target)
	{
		othellFightTask(target);
		return true;
	}
	
	protected boolean othellFightTask(Creature target)
	{
		Player actor = getActor();
		
		double distance = actor.getDistance(target);
//		double targetHp = target.getCurrentHpPercents();
//		double actorHp = actor.getCurrentHpPercents();
		double actorMp = actor.getCurrentMpPercents();
		
		if(!hasEffect(target, SKILL_SHADOW_CHASE) 
				&& !hasEffect(target, SKILL_POWER_BLUFF) 
				&& !hasEffect(target, SKILL_KICK)
				&& !hasEffect(target, SKILL_DARK_PARALYSIS))
		{
			if(actorMp > 50 && canUseSkill(SKILL_SHADOW_CHASE, target, distance))
				tryCastSkill(SKILL_SHADOW_CHASE, target, distance);
			else if(actorMp > 50 && canUseSkill(SKILL_POWER_BLUFF, target, distance))
				tryCastSkill(SKILL_POWER_BLUFF, target, distance);
			else if(actorMp > 50 && canUseSkill(SKILL_KICK, target, distance))
				tryCastSkill(SKILL_KICK, target, distance);
			else if(actorMp > 50 && canUseSkill(SKILL_DARK_PARALYSIS, target, distance))
				tryCastSkill(SKILL_DARK_PARALYSIS, target, distance);
		}
		
		if(hasEffect(target, SKILL_KICK) && canUseSkill(SKILL_HEART_BREAKER, target, distance))
			tryCastSkill(SKILL_HEART_BREAKER, target, distance);
		
		if(hasEffect(target, SKILL_BLOOD_STAB) && canUseSkill(SKILL_CHAIN_BLOW, target, distance))
			tryCastSkill(SKILL_CHAIN_BLOW, target, distance);
		
		if(canUseSkill(SKILL_BLOOD_STAB, target, distance))
			tryCastSkill(SKILL_BLOOD_STAB, target, distance);
		
		if(canUseSkill(SKILL_REVERSE, target, distance))
			tryCastSkill(SKILL_REVERSE, target, distance);
			
		chooseTaskAndTargets(null, target, distance);
		
		return false;
	}
	
}

