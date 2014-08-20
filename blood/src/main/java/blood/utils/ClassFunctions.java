package blood.utils;

import java.util.ArrayList;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.base.ClassId;
import l2s.gameserver.model.base.ClassLevel;

public class ClassFunctions {

	public static boolean tryUpClass(Player player)
	{
		if(player == null)
			return false;
		
		ArrayList<ClassId> classList = new ArrayList<ClassId>();
		
		ClassLevel nextClassLevel = null;
		ClassId currentClassId = player.getClassId();
		ClassId nextClassId = null;
		String name = player.getName();
		
		if (player.getLevel() >= 20 && player.getClassId().isOfLevel(ClassLevel.NONE))
			nextClassLevel = ClassLevel.FIRST;
		
		if (player.getLevel() >= 40 && player.getClassId().isOfLevel(ClassLevel.FIRST))
			nextClassLevel = ClassLevel.SECOND;
		
		if (player.getLevel() >= 76 && player.getClassId().isOfLevel(ClassLevel.SECOND))
			nextClassLevel = ClassLevel.THIRD;
			
		if (player.getLevel() >= 85 && player.getClassId().isOfLevel(ClassLevel.THIRD))
			nextClassLevel = ClassLevel.AWAKED;
		
		if(nextClassLevel == null)
			return false;
		
		for(ClassId classId: ClassId.VALUES)
		{
			if(!classId.isOfLevel(nextClassLevel))
				continue;
			
			if(!classId.childOf(currentClassId))
				continue;
			
			if(classId.getId() > 138 && classId.getId() < 147) // remove old GOD 4th class
				continue;
			
			classList.add(classId);
		}
		
		if(classList.size() <= 0)
			return false;
		
		if(classList.size() == 1) // apply for 3rd and 4th
		{
			// look like we have no choice
			nextClassId = classList.get(0);
		}
		else
		{
			switch (currentClassId) {
			case HUMAN_FIGHTER:
				if(NameFunctions.isTankerName(name))
					nextClassId = ClassId.KNIGHT;
				else if(NameFunctions.isWarriorName(name))
					nextClassId = ClassId.WARRIOR;
				else if(NameFunctions.isDaggerName(name) || NameFunctions.isRangerName(name))
					nextClassId = ClassId.ROGUE;
				break;
			case HUMAN_MAGE:
				if(NameFunctions.isNukerName(name) || NameFunctions.isSummonerName(name))
					nextClassId = ClassId.WIZARD;
				else if(NameFunctions.isHealerName(name) || NameFunctions.isBufferName(name))
					nextClassId = ClassId.CLERIC;
				break;
			case ELVEN_FIGHTER:
				if(NameFunctions.isTankerName(name) || NameFunctions.isBufferName(name))
					nextClassId = ClassId.ELVEN_KNIGHT;
				else if(NameFunctions.isDaggerName(name) || NameFunctions.isRangerName(name))
					nextClassId = ClassId.ELVEN_SCOUT;
				break;
			case ELVEN_MAGE:
				if(NameFunctions.isNukerName(name) || NameFunctions.isSummonerName(name))
					nextClassId = ClassId.ELVEN_WIZARD;
				else if(NameFunctions.isHealerName(name))
					nextClassId = ClassId.ORACLE;
				break;
			case DARK_FIGHTER:
				if(NameFunctions.isTankerName(name) || NameFunctions.isBufferName(name))
					nextClassId = ClassId.PALUS_KNIGHT;
				else if(NameFunctions.isDaggerName(name) || NameFunctions.isRangerName(name))
					nextClassId = ClassId.ASSASIN;
				break;
			case DARK_MAGE:
				if(NameFunctions.isNukerName(name) || NameFunctions.isSummonerName(name))
					nextClassId = ClassId.DARK_WIZARD;
				else if(NameFunctions.isHealerName(name))
					nextClassId = ClassId.SHILLEN_ORACLE;
				break;
			case ORC_FIGHTER:
				if(NameFunctions.isDestroyerName(name))
					nextClassId = ClassId.ORC_RAIDER;
				else if(NameFunctions.isTyrantName(name))
					nextClassId = ClassId.ORC_MONK;
				break;
			case DWARVEN_FIGHTER:
				if(NameFunctions.isWarriorName(name))
					nextClassId = ClassId.ARTISAN;
				else if(NameFunctions.isDaggerName(name))
					nextClassId = ClassId.SCAVENGER;
				break;
			// 2nd
			case WARRIOR:
				if(NameFunctions.isWarlordName(name))
					nextClassId = ClassId.WARLORD;
				else if(NameFunctions.isGladiatorName(name))
					nextClassId = ClassId.GLADIATOR;
				break;
			case KNIGHT:
				if(NameFunctions.isPaladinName(name))
					nextClassId = ClassId.PALADIN;
				else if(NameFunctions.isDarkAvengerName(name))
					nextClassId = ClassId.DARK_AVENGER;
				break;
			case ROGUE:
				if(NameFunctions.isTreasureHunterName(name))
					nextClassId = ClassId.TREASURE_HUNTER;
				else if(NameFunctions.isHawkeyeName(name))
					nextClassId = ClassId.HAWKEYE;
				break;
			case WIZARD:
				if(NameFunctions.isSummonerName(name))
					nextClassId = ClassId.WARLOCK;
				else if(NameFunctions.isNecromancerName(name))
					nextClassId = ClassId.NECROMANCER;
				else if(NameFunctions.isSorcererName(name))
					nextClassId = ClassId.SORCERER;
				break;
			case CLERIC:
				if(NameFunctions.isHealerName(name))
					nextClassId = ClassId.BISHOP;
				else if(NameFunctions.isBufferName(name))
					nextClassId = ClassId.PROPHET;
				break;
			case ELVEN_KNIGHT:
				if(NameFunctions.isTankerName(name))
					nextClassId = ClassId.TEMPLE_KNIGHT;
				else if(NameFunctions.isBufferName(name))
					nextClassId = ClassId.SWORDSINGER;
				break;
			case ELVEN_SCOUT:
				if(NameFunctions.isRangerName(name))
					nextClassId = ClassId.SILVER_RANGER;
				else if(NameFunctions.isDaggerName(name))
					nextClassId = ClassId.PLAIN_WALKER;
				break;
			case ELVEN_WIZARD:
				if(NameFunctions.isSummonerName(name))
					nextClassId = ClassId.ELEMENTAL_SUMMONER;
				else if(NameFunctions.isNukerName(name))
					nextClassId = ClassId.SPELLSINGER;
				break;
			case PALUS_KNIGHT:
				if(NameFunctions.isTankerName(name))
					nextClassId = ClassId.SHILLEN_KNIGHT;
				else if(NameFunctions.isBufferName(name))
					nextClassId = ClassId.BLADEDANCER;
				break;
			case ASSASIN:
				if(NameFunctions.isRangerName(name))
					nextClassId = ClassId.PHANTOM_RANGER;
				else if(NameFunctions.isDaggerName(name))
					nextClassId = ClassId.ABYSS_WALKER;
				break;
			case DARK_WIZARD:
				if(NameFunctions.isSummonerName(name))
					nextClassId = ClassId.PHANTOM_SUMMONER;
				else if(NameFunctions.isNukerName(name))
					nextClassId = ClassId.SPELLHOWLER;
				break;
			case ORC_SHAMAN:
				if(NameFunctions.isOverlordName(name))
					nextClassId = ClassId.OVERLORD;
				else if(NameFunctions.isWarcryerName(name))
					nextClassId = ClassId.WARCRYER;
				break;
			case TROOPER:
				if(NameFunctions.isNukerName(name))
					nextClassId = ClassId.M_SOUL_BREAKER;
				else if(NameFunctions.isWarriorName(name))
					nextClassId = ClassId.BERSERKER;
				break;
			case WARDER:
				if(NameFunctions.isNukerName(name))
					nextClassId = ClassId.F_SOUL_BREAKER;
				else if(NameFunctions.isRangerName(name))
					nextClassId = ClassId.ARBALESTER;
				break;
	
			default:
				break;
			}
			
			if(nextClassId == null || !classList.contains(nextClassId))
				nextClassId = classList.get(Rnd.get(classList.size()));
		}
		
		if(nextClassId == null)
			return false;
		
		player.setClassId(nextClassId.getId(), true);
		return true;
	}

	public static void upClass(Player player)
	{
		if (player == null)
			return;
		
		boolean tryUpClass = true;
		while(tryUpClass)
		{
			tryUpClass = tryUpClass(player);
		}
		
		// give all skills
		player.rewardSkills(true, true, true, false);
	}

	public static boolean canPveSolo(Player player)
		{
			if(player == null)
				return false;
			
			switch(player.getClassId())
			{
				case FEOH_ARCHMAGE:
				case FEOH_MYSTIC_MUSE:
				case FEOH_SOUL_HOUND:
				case FEOH_SOULTAKER:
				case FEOH_STORM_SCREAMER:
				case FEOH_WIZARD:
				case WYNN_ARCANA_LORD:
				case WYNN_ELEMENTAL_MASTER:
				case WYNN_SPECTRAL_MASTER:
				case WYNN_SUMMONER:
	//			case ISS_DOMINATOR:
	//			case ISS_DOOMCRYER:
	//			case ISS_ENCHANTER:
	//			case ISS_HIEROPHANT:
	//			case ISS_SPECTRAL_DANCER:
	//			case ISS_SWORD_MUSE:
					return player.getLevel() < 91 && Rnd.chance(30);
					
				default:
					return false;
			}
		}

	public static boolean isTanker(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case SIGEL_PHOENIX_KNIGHT:
			case SIGEL_HELL_KNIGHT:
			case SIGEL_EVAS_TEMPLAR:
			case SIGEL_SHILLIEN_TEMPLAR:
			case SIGEL_KNIGHT:
				return true;
			
			default:
				return false;
		}
	}

	public static boolean isIss(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case ISS_HIEROPHANT:
			case ISS_SWORD_MUSE:
			case ISS_SPECTRAL_DANCER:
			case ISS_DOOMCRYER:
			case ISS_DOMINATOR:
			case ISS_ENCHANTER:
				return true;
			
			default:
				return false;
		}
	}

	public static boolean isHealer(Player player){
		if(player == null)
			return false;
		
		switch(player.getClassId())
		{
			case AEORE_CARDINAL:
			case AEORE_EVAS_SAINT:
			case AEORE_SHILLIEN_SAINT:
				return true;
			
			default:
				return false;
		}
	}

	public static boolean isDamageDealer(Player player){
		if(player == null)
			return false;
		
		return player.getClassId().isOfLevel(ClassLevel.AWAKED) && !isTanker(player) && !isIss(player) && !isHealer(player);
	}

}
