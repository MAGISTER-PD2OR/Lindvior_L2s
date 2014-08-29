package blood.ai.impl;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Servitor;

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
	SKILL_SUMMON_BOXER_UNICORN	= 1226,
	SKILL_SUMMON_SHADOW			= 1128,
	SKILL_AQUA_SWIRL			= 1175,
	SKILL_SOLAR_SPARK			= 1264,
	SKILL_TWISTER				= 1178,
	SKILL_SHADOW_SPARK			= 1266,
	// sorc
	SKILL_HEAL = 1011,
	SKILL_CURE_POISON = 1012,
	SKILL_BATTLE_HEAL = 1015,
	SKILL_GROUP_HEAL = 1027,
	SKILL_SHIELD = 1040,
	SKILL_CANCELLATION = 1056,
	SKILL_MIGHT = 1068,
	SKILL_SLEEP = 1069,
	SKILL_SLEEPING_CLOUD = 1072,
	SKILL_SURRENDER_TO_WIND = 1074,
	SKILL_CONCENTRATION = 1078,
	SKILL_SURRENDER_TO_FIRE = 1083,
	SKILL_SUMMON_KAT_THE_CAT = 1111,
	SKILL_SERVITOR_RECHARGE = 1126,
	SKILL_SERVITOR_HEAL = 1127,
	SKILL_SERVITOR_WIND_WALK = 1144,
	SKILL_VAMPIRIC_TOUCH = 1147,
	SKILL_CORPSE_LIFE_DRAIN = 1151,
	SKILL_BODY_TO_MIND = 1157,
	SKILL_SLOW = 1160,
	SKILL_CURSE_WEAKNESS = 1164,
	SKILL_POISONOUS_CLOUD = 1167,
	SKILL_CURSE_POISON = 1168,
	SKILL_CURSE_FEAR = 1169,
	SKILL_BLAZING_CIRCLE = 1171,
	SKILL_AURA_BURN = 1172,
	SKILL_WIND_STRIKE = 1177,
	SKILL_FLAME_STRIKE = 1181,
	SKILL_ICE_BOLT = 1184,
	SKILL_SELF_HEAL = 1216,
	SKILL_BLAZE = 1220,
	SKILL_CURSE_CHAOS = 1222,
	SKILL_SUMMON_MEW_THE_CAT = 1225,
	SKILL_PROMINENCE = 1230,
	SKILL_AURA_FLARE = 1231,
	SKILL_BLAZING_SKIN = 1232,
	SKILL_DECAY = 1233,
	SKILL_ENERGY_BOLT = 1274,
	SKILL_AURA_BOLT = 1275,
	SKILL_SEED_OF_FIRE = 1285,
	SKILL_AURA_SYMPHONY = 1288,
	SKILL_INFERNO = 1289,
	SKILL_ELEMENTAL_ASSAULT = 1292,
	SKILL_RAIN_OF_FIRE = 1296,
	SKILL_COMMON_CRAFT = 1322,
	SKILL_ARCANE_CHAOS = 1338,
	SKILL_FIRE_VORTEX = 1339,
	SKILL_AURA_FLASH = 1417,
	SKILL_VOLCANO = 1419,
	SKILL_FIRE_VORTEX_BUSTER = 1451,
	SKILL_COUNT_OF_FIRE = 1452,
	SKILL_METEOR = 1467,
	SKILL_FLAME_ARMOR = 1492,
	SKILL_AURA_BLAST = 1554,
	SKILL_AURA_CANNON = 1555,
	SKILL_ARCANE_SHIELD = 1556,
	// sps
	SKILL_MANA_REGENERATION = 1047,
	SKILL_SURRENDER_TO_WATER = 1071,
	SKILL_RESIST_AQUA = 1182,
	SKILL_SURRENDER_TO_EARTH = 1223,
	SKILL_HYDRO_BLAST = 1235,
	SKILL_FROST_BOLT = 1236,
	SKILL_ICE_DAGGER = 1237,
	SKILL_FREEZING_SKIN = 1238,
	SKILL_SOLAR_FLARE = 1265,
	SKILL_SEED_OF_WATER = 1286,
	SKILL_BLIZZARD = 1290,
	SKILL_ELEMENTAL_SYMPHONY = 1293,
	SKILL_AQUA_SPLASH = 1295,
	SKILL_ICE_VORTEX = 1340,
	SKILL_LIGHT_VORTEX = 1342,
	SKILL_RAGING_WAVES = 1421,
	SKILL_ICE_VORTEX_CRUSHER = 1453,
	SKILL_DIAMOND_DUST = 1454,
	SKILL_THRONE_OF_ICE = 1455,
	SKILL_STAR_FALL = 1468,
	SKILL_FROST_ARMOR = 1493,
	SKILL_ARCANE_POWER = 337;
	
	// should add auto learn skill
	
	public FPCWizzard(Player actor)
	{
		super(actor);
		System.out.println("init new FPCWizzard");
	}
	
	public void prepareSkillsSetup() {
		_allowSelfBuffSkills.add(SKILL_ARCANE_POWER);
		_allowSelfBuffSkills.add(SKILL_FREEZING_SKIN);
		_allowSelfBuffSkills.add(SKILL_FROST_ARMOR);
		_allowSelfBuffSkills.add(SKILL_SEED_OF_WATER);
		_allowSelfBuffSkills.add(SKILL_MANA_REGENERATION);
		_allowSelfBuffSkills.add(SKILL_RESIST_AQUA);
	}
	
	protected boolean isAllowClass()
	{
		Player player = getActor();
		switch(player.getClassId()){
		case HUMAN_MAGE:
		case WIZARD:
		case ELVEN_MAGE:
		case ELVEN_WIZARD:
		case DARK_MAGE:
		case DARK_WIZARD:
			return true;
		default:
			return false;
		}
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
		
		for(Servitor summon: player.getServitors())
		{
			summon.getAI().Attack(target, true, false);
		}
		
		if(canUseSkill(SKILL_BODY_TO_MIND, player))
			return tryCastSkill(SKILL_BODY_TO_MIND, player);
		
		if(canUseSkill(SKILL_SURRENDER_TO_WATER, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_WATER, target, distance);
		
		if(canUseSkill(SKILL_HYDRO_BLAST, target, distance))
			return tryCastSkill(SKILL_HYDRO_BLAST, target, distance);
		
		if(canUseSkill(SKILL_SURRENDER_TO_FIRE, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_FIRE, target, distance);
		
		if(player.getLevel() < 40)
		{
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
		}
		
		tryMoveToTarget(target, 600);
		return false;
	}
	
	protected boolean defaultSubFightTask(Creature target)
	{
		wizzardFightTask(target);
		return true;
	}
	
}

