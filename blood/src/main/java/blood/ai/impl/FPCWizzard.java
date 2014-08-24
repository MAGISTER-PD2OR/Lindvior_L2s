package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Skill;
import l2s.gameserver.utils.Location;

/**
 * 
 * @author mylove1412
 *
 * Logic inside Feoh AI
 * 
 * Select stance in situation ? -> not buff -> active by situation
 *
 */

public class FPCWizzard extends MysticPC
{
	public static final int
	SKILL_SUMMON_KAT_THE_CAT 	= 1111,
	SKILL_SUMMON_BOXER_UNICORN	= 1226,
	SKILL_SUMMON_SHADOW			= 1128,
	SKILL_BLAZE					= 1220,
	SKILL_AQUA_SWIRL			= 1175,
	SKILL_BODY_TO_MIND			= 1157,
	SKILL_SOLAR_SPARK			= 1264,
	SKILL_TWISTER				= 1178,
	SKILL_SHADOW_SPARK			= 1266,
	SKILL_SURRENDER_TO_FIRE		= 1083;
	
	// should add auto learn skill
	
	public FPCWizzard(Player actor)
	{
		super(actor);
		System.out.println("init new FPCWizzard");
	}
	
	public void prepareSkillsSetup() {
		_allowSkills.add(SKILL_SUMMON_KAT_THE_CAT);
		_allowSkills.add(SKILL_SUMMON_BOXER_UNICORN);
		_allowSkills.add(SKILL_SUMMON_SHADOW);
	}
	
	@Override
	protected void onEvtAttacked(Creature attacker, int damage)
	{
		super.onEvtAttacked(attacker, damage);
	}
	
	protected boolean wizzardFightTask(Creature target)
	{
		Player player = getActor();
		double distance = player.getDistance(target);
		
		if(canUseSkill(SKILL_BODY_TO_MIND, player))
			return tryCastSkill(SKILL_BODY_TO_MIND, player);
		
		if(canUseSkill(SKILL_SURRENDER_TO_FIRE, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_FIRE, target, distance);
		
		if(canUseSkill(SKILL_BLAZE, target, distance))
			return tryCastSkill(SKILL_BLAZE, target, distance);
		
		if(canUseSkill(SKILL_AQUA_SWIRL, target, distance))
			return tryCastSkill(SKILL_AQUA_SWIRL, target, distance);
		
		if(canUseSkill(SKILL_SOLAR_SPARK, target, distance))
			return tryCastSkill(SKILL_SOLAR_SPARK, target, distance);
		
		if(canUseSkill(SKILL_TWISTER, target, distance))
			return tryCastSkill(SKILL_TWISTER, target, distance);
		
		if(canUseSkill(SKILL_SHADOW_SPARK, target, distance))
			return tryCastSkill(SKILL_SHADOW_SPARK, target, distance);
		
		addTaskMove(Location.findAroundPosition(target, 600), true);
		return false;
	}
	
	protected boolean defaultSubFightTask(Creature target)
	{
		wizzardFightTask(target);
		return true;
	}
	
}

