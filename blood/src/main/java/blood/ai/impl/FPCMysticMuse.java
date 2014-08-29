package blood.ai.impl;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.utils.Location;

public class FPCMysticMuse extends MysticPC
{
	public final int
	SKILL_ARCANE_POWER 		= 337,
	SKILL_MANA_REGENERATION = 1047,
	SKILL_SURRENDER_TO_WATER = 1071,
	SKILL_RESIST_AQUA = 1182,
	SKILL_ICE_BOLT = 1184,
	SKILL_SURRENDER_TO_EARTH = 1223,
	SKILL_AURA_FLARE = 1231,
	SKILL_HYDRO_BLAST = 1235,
	SKILL_FROST_BOLT = 1236,
	SKILL_ICE_DAGGER = 1237,
	SKILL_FREEZING_SKIN = 1238,
	SKILL_SOLAR_FLARE = 1265,
	SKILL_ENERGY_BOLT = 1274,
	SKILL_AURA_BOLT = 1275,
	SKILL_SEED_OF_WATER = 1286,
	SKILL_AURA_SYMPHONY = 1288,
	SKILL_BLIZZARD = 1290,
	SKILL_ELEMENTAL_SYMPHONY = 1293,
	SKILL_AQUA_SPLASH = 1295,
	SKILL_ARCANE_CHAOS = 1338,
	SKILL_ICE_VORTEX = 1340,
	SKILL_LIGHT_VORTEX = 1342,
	SKILL_AURA_FLASH = 1417,
	SKILL_RAGING_WAVES = 1421,
	SKILL_ICE_VORTEX_CRUSHER = 1453,
	SKILL_DIAMOND_DUST = 1454,
	SKILL_THRONE_OF_ICE = 1455,
	SKILL_STAR_FALL = 1468,
	SKILL_FROST_ARMOR = 1493,
	SKILL_AURA_BLAST = 1554,
	SKILL_AURA_CANNON = 1555,
	SKILL_ARCANE_SHIELD = 1556;
	
	public FPCMysticMuse(Player actor)
	{
		super(actor);
	}
	
	public void prepareSkillsSetup() {
		_allowSelfBuffSkills.add(SKILL_ARCANE_POWER);
		_allowSelfBuffSkills.add(SKILL_FREEZING_SKIN);
		_allowSelfBuffSkills.add(SKILL_FROST_ARMOR);
		_allowSelfBuffSkills.add(SKILL_SEED_OF_WATER);
		_allowSelfBuffSkills.add(SKILL_MANA_REGENERATION);
		_allowSelfBuffSkills.add(SKILL_RESIST_AQUA);
	}

	protected boolean feohFightTask(Creature target)
	{
		Player player = getActor();
		double distance = player.getDistance(target);
		
		if(canUseSkill(SKILL_SURRENDER_TO_WATER, target, distance))
			return tryCastSkill(SKILL_SURRENDER_TO_WATER, target, distance);
		
		if(canUseSkill(SKILL_HYDRO_BLAST, target, distance))
			return tryCastSkill(SKILL_HYDRO_BLAST, target, distance);
		
		addTaskMove(Location.findAroundPosition(target, 600), true);
		return false;
	}
	
	protected boolean defaultSubFightTask(Creature target)
	{
		feohFightTask(target);
		return true;
	}
	
}

