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
	// nec
	SKILL_SILENCE = 1064,
	SKILL_SUMMON_REANIMATED_MAN = 1129,
	SKILL_DEATH_SPIKE = 1148,
	SKILL_SUMMON_CORRUPTED_MAN = 1154,
	SKILL_CORPSE_BURST = 1155,
	SKILL_FORGET = 1156,
	SKILL_CURSE_DEATH_LINK = 1159,
	SKILL_CURSE_DISCORD = 1163,
	SKILL_ANCHOR = 1170,
	SKILL_VAMPIRIC_CLAW = 1234,
	SKILL_TRANSFER_PAIN = 1262,
	SKILL_CURSE_GLOOM = 1263,
	SKILL_CURSE_DISEASE = 1269,
	SKILL_MASS_SLOW = 1298,
	SKILL_SUMMON_CURSED_MAN = 1334,
	SKILL_CURSE_OF_DOOM = 1336,
	SKILL_CURSE_OF_ABYSS = 1337,
	SKILL_DARK_VORTEX = 1343,
	SKILL_MASS_WARRIOR_BANE = 1344,
	SKILL_MASS_MAGE_BANE = 1345,
	SKILL_MASS_FEAR = 1381,
	SKILL_MASS_GLOOM = 1382,
	SKILL_DAY_OF_DOOM = 1422,
	SKILL_GEHENNA = 1423,
	SKILL_VAMPIRIC_MIST = 1495,
	SKILL_SERVITOR_SHARE = 1557,
	// sps
	SKILL_MANA_REGENERATION = 1047,
	SKILL_SURRENDER_TO_WATER = 1071,
	SKILL_BRIGHT_SERVITOR = 1145,
	SKILL_FROST_WALL = 1174,
	SKILL_RESIST_AQUA = 1182,
	SKILL_FREEZING_SHACKLE = 1183,
	SKILL_WIND_SHACKLE = 1206,
	SKILL_SURRENDER_TO_EARTH = 1223,
	SKILL_SUMMON_BOXER_THE_UNICORN = 1226,
	SKILL_SUMMON_MIRAGE_THE_UNICORN = 1227,
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
	// sh
	SKILL_MIGHTY_SERVITOR = 1146,
	SKILL_TEMPEST = 1176,
	SKILL_SURRENDER_TO_POISON = 1224,
	SKILL_SUMMON_SILHOUETTE = 1228,
	SKILL_HURRICANE = 1239,
	SKILL_SHADOW_FLARE = 1267,
	SKILL_SEED_OF_WIND = 1287,
	SKILL_DEMON_WIND = 1291,
	SKILL_ELEMENTAL_STORM = 1294,
	SKILL_WIND_VORTEX = 1341,
	SKILL_CYCLONE = 1420,
	SKILL_WIND_VORTEX_SLUG = 1456,
	SKILL_EMPOWERING_ECHO = 1457,
	SKILL_THRONE_OF_WIND = 1458,
	SKILL_HURRICANE_ARMOR = 1494,
	
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
		_allowSelfBuffSkills.add(SKILL_BLAZING_SKIN);
		_allowSelfBuffSkills.add(SKILL_FLAME_ARMOR);
		_allowSelfBuffSkills.add(SKILL_FROST_ARMOR);
		_allowSelfBuffSkills.add(SKILL_HURRICANE_ARMOR);
		_allowSelfBuffSkills.add(SKILL_SEED_OF_FIRE);
		_allowSelfBuffSkills.add(SKILL_SEED_OF_WATER);
		_allowSelfBuffSkills.add(SKILL_SEED_OF_WIND);
		_allowSelfBuffSkills.add(SKILL_MANA_REGENERATION);
		_allowSelfBuffSkills.add(SKILL_RESIST_AQUA);
		_allowSelfBuffSkills.add(SKILL_EMPOWERING_ECHO);
		_allowSkills.add(SKILL_SUMMON_CORRUPTED_MAN);
	}
	
	protected boolean isAllowClass()
	{
		Player player = getActor();
		switch(player.getClassId()){
		case HUMAN_MAGE:
		case WIZARD:
		case SORCERER:
		case ARCHMAGE:
		case NECROMANCER:
		case SOULTAKER:
		case ELVEN_MAGE:
		case ELVEN_WIZARD:
		case SPELLSINGER:
		case MYSTIC_MUSE:
		case DARK_MAGE:
		case DARK_WIZARD:
		case SPELLHOWLER:
		case STORM_SCREAMER:
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
		double playerHP = player.getCurrentHpPercents();
		
		for(Servitor summon: player.getServitors())
		{
			summon.getAI().Attack(target, true, false);
		}
		
		if(playerHP < 80)
		{
			if(canUseSkill(SKILL_DARK_VORTEX, target, distance))
				return tryCastSkill(SKILL_DARK_VORTEX, target, distance);
			
			if(canUseSkill(SKILL_VAMPIRIC_CLAW, target, distance))
				return tryCastSkill(SKILL_VAMPIRIC_CLAW, target, distance);
		}
		
		if(playerHP > 50 && canUseSkill(SKILL_BODY_TO_MIND, player))
			return tryCastSkill(SKILL_BODY_TO_MIND, player);
		
		if(canUseSkill(SKILL_CURSE_GLOOM, target, distance))
			return tryCastSkill(SKILL_CURSE_GLOOM, target, distance);
		
		if(canUseSkill(SKILL_SURRENDER_TO_FIRE, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_FIRE, target, distance);
		
		if(canUseSkill(SKILL_SURRENDER_TO_WATER, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_WATER, target, distance);
		
		if(canUseSkill(SKILL_SURRENDER_TO_WIND, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_WIND, target, distance);
		
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
		
		if(canUseSkill(SKILL_DEATH_SPIKE, target, distance))
			return tryCastSkill(SKILL_DEATH_SPIKE, target, distance);
		
		if(canUseSkill(SKILL_PROMINENCE, target, distance))
			return tryCastSkill(SKILL_PROMINENCE, target, distance);
		
		if(canUseSkill(SKILL_HYDRO_BLAST, target, distance))
			return tryCastSkill(SKILL_HYDRO_BLAST, target, distance);
		
		if(canUseSkill(SKILL_HURRICANE, target, distance))
			return tryCastSkill(SKILL_HURRICANE, target, distance);
		
		tryMoveToTarget(target, 600);
		return false;
	}
	
	protected boolean defaultSubFightTask(Creature target)
	{
		wizzardFightTask(target);
		return true;
	}
	
}

