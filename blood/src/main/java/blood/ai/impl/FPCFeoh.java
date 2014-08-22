package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;

/**
 * 
 * @author mylove1412
 *
 * Logic inside Feoh AI
 * 
 * Select stance in situation ? -> not buff -> active by situation
 *
 */

public class FPCFeoh extends MysticPC
{
	public final int SKILL_ULTIMATE_BTM 	= 11064;
	public final int SKILL_MAGIC_EVASION 	= 11057;
	public final int SKILL_MAGIC_CHARGE 	= 11094;
	public final int SKILL_DEVIL_CURSE 		= 11047;
	public final int SKILL_DARK_CURSE		= 11150;
	
	public final int SKILL_ELEMENT_SPIKE 	= 11011;
	public final int SKILL_ELEMENT_CRASH 	= 11017;
	public final int SKILL_ELEMENT_DESTRUCTION = 11023;
	public final int SKILL_ELEMENT_BLAST	= 11034;
	public final int SKILL_ELEMENT_STORM	= 11040;
	
	public final int SKILL_DEATH_LORE		= 11030;
	public final int SKILL_DEATH_FEAR		= 11055;
	
	public final int SKILL_ELEMENT_BURST_HU	= 11106;
	public final int SKILL_ELEMENT_BURST_EL	= 11112;
	public final int SKILL_ELEMENT_BURST_DE	= 11118;
	
	public final int SKILL_FIRE_STANCE 	= 11007;
	public final int SKILL_WATER_STANCE	= 11008;
	public final int SKILL_WIND_STANCE 	= 11009;
	public final int SKILL_EARTH_STANCE	= 11010;
	public final int SKILL_ARCANE_POWER = 337;
	
	protected long _darkcureTS = 0;
	protected int _darkcureReuse = 30000;
	
	// should add auto learn skill
	
	public FPCFeoh(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup() {
		_allowSelfBuffSkills.add(SKILL_ARCANE_POWER);
		_allowSelfBuffSkills.add(SKILL_EARTH_STANCE);
	}

//	@Override
//	protected boolean thinkBuff()
//	{
//		if(thinkBuff(new int[] {
//			SKILL_ARCANE_POWER,
//			SKILL_EARTH_STANCE
//		}))
//			return true;
//		return super.thinkBuff();
//	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		Player actor = getActor();
		
		double distance = actor.getDistance(attacker);
		debug("get acttacked by: "+attacker+" in range:" + distance);
		if(distance < 200)
		{
			Skill skillMagicEvasion = actor.getKnownSkill(SKILL_MAGIC_EVASION);
			Skill skillMagicCharge = actor.getKnownSkill(SKILL_MAGIC_CHARGE);
			Skill skillDeathFear = actor.getKnownSkill(SKILL_DEATH_FEAR);
			if(canUseSkill(skillMagicEvasion, attacker))
				chooseTaskAndTargets(skillMagicEvasion, attacker, distance);
			else if(canUseSkill(skillMagicCharge, attacker))
				chooseTaskAndTargets(skillMagicCharge, attacker, distance);
			else if(canUseSkill(skillDeathFear, attacker))
				chooseTaskAndTargets(skillDeathFear, attacker, distance);
		}
		
		super.onEvtAttacked(attacker, damage);
	}
	
	protected int _last_target_id = 0;
	protected boolean _last_target_dark_curse = false;
	
	protected boolean feohFightTask(Creature target)
	{
		Player player = getActor();
		double distance = player.getDistance(target);
		
		Skill skillDeathCurse = player.getKnownSkill(SKILL_DEVIL_CURSE);
		Skill skillElementBurst = player.getKnownSkill(SKILL_ELEMENT_BURST_DE);
		if (skillElementBurst == null) skillElementBurst = player.getKnownSkill(SKILL_ELEMENT_BURST_EL);
		if (skillElementBurst == null) skillElementBurst = player.getKnownSkill(SKILL_ELEMENT_BURST_HU);
		Skill skillElementDestruction = player.getKnownSkill(SKILL_ELEMENT_DESTRUCTION);
		Skill skillElementSpike = player.getKnownSkill(SKILL_ELEMENT_SPIKE);
		Skill skillElementCrash = player.getKnownSkill(SKILL_ELEMENT_CRASH);
		Skill skillDeathFear = player.getKnownSkill(SKILL_DEATH_FEAR);
		Skill skillDarkCurse = player.getKnownSkill(SKILL_DARK_CURSE);
		Skill skillUltimateBtm = getActor().getKnownSkill(SKILL_ULTIMATE_BTM);
		
		if(canUseSkill(skillUltimateBtm, player, 0) && player.getCurrentHpPercents() > 40)
			chooseTaskAndTargets(skillUltimateBtm, player, 0);
		
		if(distance < 400 && canUseSkill(skillDeathFear, target))
			chooseTaskAndTargets(skillDeathFear, target, distance);
		
		if(_last_target_id != target.getObjectId())
		{
			_last_target_dark_curse = false;
		}
		
		// if we are feoh soul taker we should debuf darkcurse first
		if(!_last_target_dark_curse && canUseSkill(skillDarkCurse, target, distance) && target.getEffectList().getEffectsCount(skillDarkCurse) == 0){
			_last_target_dark_curse = true;
			return chooseTaskAndTargets(skillDarkCurse, target, distance);
		}
			
		// 1st use death curse
		if(canUseSkill(skillDeathCurse, target, distance) && target.getEffectList().getEffectsCount(skillDeathCurse) == 0)
			return chooseTaskAndTargets(skillDeathCurse, target, distance);
		
		if(player.getCurrentHpPercents() < 50)
		{
			// 1st if we success on destruction
			if(skillElementBurst != null && canUseSkill(skillElementBurst, target, distance))
				return chooseTaskAndTargets(skillElementBurst, target, distance);
			
			// use destruction for more DPS
			if(canUseSkill(skillElementDestruction, target, distance))
				return chooseTaskAndTargets(skillElementDestruction, target, distance);
		}
		
		// save mp skill
		if(canUseSkill(skillElementSpike, target, distance))
			return chooseTaskAndTargets(skillElementSpike, target, distance);
		
		// save mp skill
		if(canUseSkill(skillElementCrash, target, distance))
			return chooseTaskAndTargets(skillElementCrash, target, distance);
		
		// 1st if we success on destruction
		if(skillElementBurst != null && canUseSkill(skillElementBurst, target, distance))
			return chooseTaskAndTargets(skillElementBurst, target, distance);
		
		// use destruction
		if(canUseSkill(skillElementDestruction, target, distance))
			return chooseTaskAndTargets(skillElementDestruction, target, distance);
		
		debug("try move to target");
		tryMoveToTarget(target, 600);
		return false;
	}
	
	protected boolean defaultSubFightTask(Creature target)
	{
		feohFightTask(target);
		return true;
	}
	
}

